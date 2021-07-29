package com.prodyna.prodynamarket.controllers;

import com.prodyna.prodynamarket.dtos.ProductDto;
import com.prodyna.prodynamarket.dtos.UserDto;
import com.prodyna.prodynamarket.models.Product;
import com.prodyna.prodynamarket.services.ProductService;
import com.prodyna.prodynamarket.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Boolean> loginAdmin(@RequestBody UserDto admin) {
        return ResponseEntity.status(HttpStatus.OK).body(admin.getUserName().equals("admin") && admin.getPassword().equals("admin"));
    }

    @PostMapping(path = "/save-product")
    @ResponseBody
    public void saveProduct(@RequestBody ProductDto product) {
        productService.createNewProduct(modelMapper.map(product, Product.class));
    }

    @DeleteMapping(path = "/delete-product")
    @ResponseBody
    public void deleteProduct(@RequestParam String productName) {
        productService.deleteProductByName(productName);
    }

    @GetMapping(path = "/get-all-products")
    @ResponseBody
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> products = productService.getAllProducts()
                .stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping(path = "/get-all-users")
    @ResponseBody
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers().stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }
}
