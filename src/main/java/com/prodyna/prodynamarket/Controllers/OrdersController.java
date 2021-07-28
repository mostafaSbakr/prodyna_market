package com.prodyna.prodynamarket.Controllers;

import com.prodyna.prodynamarket.Models.Order;
import com.prodyna.prodynamarket.Models.Product;
import com.prodyna.prodynamarket.Models.User;
import com.prodyna.prodynamarket.Services.OrderService;
import com.prodyna.prodynamarket.Services.ProductService;
import com.prodyna.prodynamarket.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.List;

@Controller
public class OrdersController {

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @PostMapping(path = "/new-order")
    @ResponseBody
    public void addNewOrder(@RequestParam String name, @RequestParam String quantity, @RequestParam String userName) {
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
    }

    @GetMapping(path = "/get-user-orders")
    @ResponseBody
    public List<Order> getUserOrders(@RequestParam String userName) {
        User user = userService.getUserByName(userName);
        return orderService.getOrdersByUserId(user.getUserId());
    }
}
