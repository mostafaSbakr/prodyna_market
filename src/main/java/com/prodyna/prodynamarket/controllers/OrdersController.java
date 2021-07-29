package com.prodyna.prodynamarket.controllers;

import com.prodyna.prodynamarket.dtos.OrderDto;
import com.prodyna.prodynamarket.models.Order;
import com.prodyna.prodynamarket.models.Product;
import com.prodyna.prodynamarket.models.User;
import com.prodyna.prodynamarket.services.OrderService;
import com.prodyna.prodynamarket.services.ProductService;
import com.prodyna.prodynamarket.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OrdersController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @PostMapping(path = "/new-order")
    @ResponseBody
    public ResponseEntity<Void> addNewOrder(@RequestParam String name, @RequestParam String quantity, @RequestParam String userName) {
        try {
            // get product by name
            Product product = productService.getProductByName(name);
            int updatedQuantity = product.getQuantity() - Integer.parseInt(quantity);

            product.setQuantity(updatedQuantity);
            productService.updateProduct(product);

            double orderPrice = product.getPrice() * Double.parseDouble(quantity);
            // get user
            User user = userService.getUserByName(userName);

            Order newOrder = new Order(user, Calendar.getInstance().getTime(), product, orderPrice);
            orderService.createNewOrder(newOrder);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping(path = "/get-user-orders")
    @ResponseBody
    public ResponseEntity<List<OrderDto>> getUserOrders(@RequestParam String userName) {
        try {
            User user = userService.getUserByName(userName);
            List<OrderDto> orders = orderService.getOrdersByUserId(user.getUserId()).stream()
                    .map(order -> modelMapper.map(order, OrderDto.class))
                    .collect(Collectors.toList());
               return ResponseEntity.status(HttpStatus.OK).body(orders);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
