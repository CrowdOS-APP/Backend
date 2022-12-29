package com.crowdos.backend.controller;


import com.crowdos.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/query")
    public String getUser(long uid){
        if(userService.findUserById(uid)==null)
            return "Not Found";
        else return userService.findUserById(uid).getName();
    }



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
        return userService.getUserInfo(token);
    }
    @PostMapping ("/updateUserInfo")
    public Map<String, Object> updateUserInfo(@RequestParam String token,
                                  @RequestBody Map<String, String> params) {
        return userService.updateUserInfo(token,params);
    }
    @PostMapping ("/updatePasswd")
    public Map<String, Object> updatePasswd(@RequestParam String token,
                                              @RequestBody Map<String, String> passwd) {
        return userService.updatePasswd(token,passwd);
    }
}
