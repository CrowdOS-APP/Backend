package com.crowdos.backend.controller;


import com.crowdos.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Boolean register(@RequestBody Map<String, String> Register) {
        return userService.register(Register);
    }
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> Login) {
        return userService.login(Login);
    }
    @GetMapping ("/getUserInfo")
    public Map<String, Object> getUserInfo(@RequestParam String token) {
        var response = userService.getUserInfo(token);
        if(response!=null) return response;
        return new HashMap<>();
    }
    @PostMapping ("/updateUserInfo")
    public Map<String, Object> updateUserInfo(@RequestParam String token,
                                  @RequestBody Map<String, String> params) {
        var response = userService.updateUserInfo(token,params);
        if(response!=null) return response;
        return new HashMap<>();
    }
    @PostMapping ("/updatePasswd")
    public Map<String, Object> updatePasswd(@RequestParam String token,
                                              @RequestBody Map<String, String> passwd) {
        var response = userService.updatePasswd(token,passwd);
        if(response!=null) return response;
        return new HashMap<>();
    }
}
