package com.crowdos.backend.controller;


import com.crowdos.backend.model.event;
import com.crowdos.backend.model.followlist;
import com.crowdos.backend.service.EventService;
import com.crowdos.backend.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class FollowController {

    @Autowired
    private FollowService followService;
    @Autowired
    private EventService eventService;

    @PostMapping("/follow")
    public Map<String, Object> follow(@RequestParam String token,
                                      @RequestParam Long eventID,
                                              @RequestBody Map<String, String> follow){
        return followService.follow(token,eventID,follow);
    }
    @GetMapping("/following")
    public List<event> following(@RequestParam String token){
        List<followlist> followlists=followService.following(token);
        List<event> events=new ArrayList<>();
        for(var follow:followlists){
            events.add(eventService.findEventByEid(follow.getEventid()));
        }
        return events;
    }
}
