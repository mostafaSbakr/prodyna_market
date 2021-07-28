package com.prodyna.prodynamarket.controllers;

import com.prodyna.prodynamarket.models.Product;
import com.prodyna.prodynamarket.models.User;
import com.prodyna.prodynamarket.services.ProductService;
import com.prodyna.prodynamarket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @PostMapping(path = "/admin-page")
    @ResponseBody
    public boolean loginAdmin(@RequestBody User admin) {
        return admin.getUserName().equals("admin") && admin.getPassword().equals("admin");
    }

    @PostMapping(path = "/save-product")
    @ResponseBody
    public void saveProduct(@RequestBody Product product) {
        productService.createNewProduct(product);
    }

    @PostMapping(path = "/delete-product")
    @ResponseBody
    public void deleteProduct(@RequestParam String productName) {
        productService.deleteProductByName(productName);
    }

    @GetMapping(path = "/get-all-products")
    @ResponseBody
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(path = "/get-all-users")
    @ResponseBody
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
