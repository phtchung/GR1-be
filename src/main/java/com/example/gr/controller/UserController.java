package com.example.gr.controller;

import com.example.gr.entity.User;
import com.example.gr.request.UpdateUserRequest;
import com.example.gr.response.CommonResponse;
import com.example.gr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/user")
    public CommonResponse  updateUser( @RequestBody UpdateUserRequest updateUserRequest) {
        return userService.updateUser( updateUserRequest);

    }

    @GetMapping("users/{userId}")
    public CommonResponse getUser(@PathVariable Long userId){
        return userService.getUser(userId);
    }
}
