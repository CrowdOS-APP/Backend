package com.crowdos.backend.controller;


import com.crowdos.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {
    @GetMapping("/hello")
    public String HelloWorld(String s) {
        return s;
    }

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public Boolean register(@RequestBody Map<String, String> Register) {
        return userService.register(Register);
    }
}
