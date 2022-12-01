package com.crowdos.backend.controller;


import com.crowdos.backend.model.user;
import com.crowdos.backend.repository.UserRepositoryUid;
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
    private UserRepositoryUid userRepositoryUid;
    @GetMapping("/query/id")
    public String getEmail(long uid){
        user user=userRepositoryUid.findByUid(uid);
        if(user!=null){
            return user.getEmail();
        }
        else
            return "Not Found";
    }

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public Boolean register(@RequestBody Map<String, String> Register) {
        return userService.register(Register);
    }
    @PostMapping("login")
    public String login(@RequestBody Map<String, String> Login) {
        return userService.login(Login);
    }
}
