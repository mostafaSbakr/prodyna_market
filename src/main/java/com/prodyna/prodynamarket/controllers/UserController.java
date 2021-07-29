package com.prodyna.prodynamarket.controllers;

import com.prodyna.prodynamarket.dtos.UserDto;
import com.prodyna.prodynamarket.models.User;
import com.prodyna.prodynamarket.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Void> addNewUser(@RequestBody UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        userService.createNewUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping(path = "/user-page")
    @ResponseBody
    public ResponseEntity<Boolean> loginUser(@RequestParam String userName, @RequestParam String password) {
        return userService.authenticateUser(userName, password) ?
                new ResponseEntity<>(true, HttpStatus.OK) :
                new ResponseEntity<>(false, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete-user")
    @ResponseBody
    public ResponseEntity<Boolean> deleteUser(@RequestParam String userId) {
        try {
            userService.deleteUser(Integer.parseInt(userId));
            return ResponseEntity.status(HttpStatus.OK).body(true);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
    }

    @GetMapping(path = "/get-user")
    @ResponseBody
    public ResponseEntity<UserDto> getUser(@RequestParam String userName) {
        try {
            User user = userService.getUserByName(userName);
            return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(user, UserDto.class));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}


