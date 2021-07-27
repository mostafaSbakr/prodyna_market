package com.prodyna.prodynamarket.Controllers;

import com.prodyna.prodynamarket.Models.Product;
import com.prodyna.prodynamarket.Models.User;
import com.prodyna.prodynamarket.Services.ProductService;
import com.prodyna.prodynamarket.Services.UserService;
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
        System.out.println("Arrived");
        return admin.getUserName().equals("admin") && admin.getPassword().equals("admin");
    }

    @PostMapping(path = "/save-product")
    @ResponseBody
    public void saveProduct(@RequestBody Product product) {
        System.out.println("Arrived Product");
        productService.createNewProduct(product);
    }

    @PostMapping(path = "/delete-product")
    @ResponseBody
    public void deleteProduct(@RequestParam String productName) {
        System.out.println("Deleted Product");
        productService.deleteProductByName(productName);
    }

    @GetMapping(path = "/get-all-products")
    @ResponseBody
    public List<Product> getAllProducts() {
        List<Product> productsList = productService.getAllProducts();
        return productsList;
    }

    @GetMapping(path = "/get-all-users")
    @ResponseBody
    public List<User> getAllUsers() {
        System.out.println("Arrived to get all users");
        return userService.getAllUsers();
    }
}
