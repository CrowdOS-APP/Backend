package cn.crowdos.demo.controller;

import cn.crowdos.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {
    @GetMapping("hello")
    public String HelloWorld(String s) {
        return s;
    }

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public String register(@RequestBody Map<String, String> Register) {
        return userService.register(Register);
    }
}
