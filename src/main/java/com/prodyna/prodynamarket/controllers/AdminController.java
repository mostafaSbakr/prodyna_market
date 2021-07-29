package com.prodyna.prodynamarket.controllers;

import com.prodyna.prodynamarket.dtos.ProductDto;
import com.prodyna.prodynamarket.dtos.UserDto;
import com.prodyna.prodynamarket.models.Product;
import com.prodyna.prodynamarket.models.User;
import com.prodyna.prodynamarket.services.ProductService;
import com.prodyna.prodynamarket.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AdminController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @PostMapping(path = "/admin-page")
    @ResponseBody
    public boolean loginAdmin(@RequestBody UserDto admin) {
        return admin.getUserName().equals("admin") && admin.getPassword().equals("admin");
    }

    @PostMapping(path = "/save-product")
    @ResponseBody
    public void saveProduct(@RequestBody ProductDto product) {
        productService.createNewProduct(modelMapper.map(product, Product.class));
    }

    @PostMapping(path = "/delete-product")
    @ResponseBody
    public void deleteProduct(@RequestParam String productName) {
        productService.deleteProductByName(productName);
    }

    @GetMapping(path = "/get-all-products")
    @ResponseBody
    public List<ProductDto> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/get-all-users")
    @ResponseBody
    public List<UserDto> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }
}
