package com.crowdos.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {
    @GetMapping("/hello")
    public String HelloWorld(String s) {
        return s;
    }

//    @Autowired
//    private UserService userService;
//
//    @PostMapping("register")
//    public String register(@RequestBody Map<String, String> Register) {
//        return userService.register(Register);
//    }
}
