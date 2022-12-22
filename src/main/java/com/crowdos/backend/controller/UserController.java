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

    @Autowired
    private UserService userService;
    @GetMapping("/hello")
    public String HelloWorld(String s) {
        return s;
    }

    @GetMapping("/query")
    public String getUser(long uid){
        if(userService.findUserById(uid)==null)
            return "Not Found";
        else return userService.findUserById(uid).getName();
    }



    @PostMapping("register")
    public Boolean register(@RequestBody Map<String, String> Register) {
        return userService.register(Register);
    }
    @PostMapping("login")
    public String login(@RequestBody Map<String, String> Login) {
        return userService.login(Login);
    }
}
