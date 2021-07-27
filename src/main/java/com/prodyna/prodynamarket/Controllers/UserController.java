package com.prodyna.prodynamarket.Controllers;

import com.prodyna.prodynamarket.Models.User;
import com.prodyna.prodynamarket.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(path = "/new-user")
    @ResponseBody
    public void addNewUser(@RequestBody User user) {
        userService.createNewUser(user);
        System.out.println("Added new user");
    }

    @PostMapping(path = "/user-page")
    @ResponseBody
    public boolean loginUser(@RequestParam String userName, @RequestParam String password) {
        return userService.authenticateUser(userName, password);
    }

    @DeleteMapping(path = "/delete-user")
    @ResponseBody
    public void deleteUser(@RequestParam String userId) {
        userService.deleteUser(Integer.parseInt(userId));
    }

    @GetMapping(path = "/get-user")
    @ResponseBody
    public User getUser(@RequestParam String userName) {
        return userService.getUserByName(userName);
    }
}