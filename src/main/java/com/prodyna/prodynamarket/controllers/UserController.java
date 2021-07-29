package com.prodyna.prodynamarket.controllers;

import com.prodyna.prodynamarket.dtos.UserDto;
import com.prodyna.prodynamarket.models.User;
import com.prodyna.prodynamarket.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserService userService;

    @PostMapping(path = "/new-user")
    @ResponseBody
    public void addNewUser(@RequestBody UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        userService.createNewUser(user);
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
    public UserDto getUser(@RequestParam String userName) {
        User user = userService.getUserByName(userName);
        return modelMapper.map(user, UserDto.class);
    }
}
//    @GetMapping(path = "/get-user")
//    @ResponseBody
//    public ResponseEntity<User> getUser(@RequestParam String userName) {
//        ResponseEntity<User> response = ResponseEntity.badRequest().build();
//        if(!userName.isBlank()){
//            User user = userService.getUserByName(userName);
//            if (user == null) {
//                response = ResponseEntity.notFound().build();
//            } else {
//                response = ResponseEntity.ok(userService.getUserByName(userName));
//            }
//        }
//        return response;
//    }

