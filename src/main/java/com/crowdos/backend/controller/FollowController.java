package com.crowdos.backend.controller;


import com.crowdos.backend.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class FollowController {

    @Autowired
    private FollowService followService;

    @PostMapping("/follow")
    public Map<String, Object> follow(@RequestParam String token,
                                      @RequestParam Long UID,
                                              @RequestBody Map<String, String> follow){
        return followService.follow(token,UID,follow);
    }
    @GetMapping("/following")
    public List following(@RequestParam String token){
        return followService.following(token);
    }
}
