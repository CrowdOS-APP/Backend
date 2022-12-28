package com.crowdos.backend.controller;


import com.crowdos.backend.model.event;
import com.crowdos.backend.model.followlist;
import com.crowdos.backend.service.EventService;
import com.crowdos.backend.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
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
    public List following(@RequestParam String token){
        List<followlist> followlists=followService.following(token);
        List<Map<String,Object>> response=new ArrayList<>();
        if(followlists!=null){
            for(var follow:followlists){
                event Event=eventService.findEventByEid(follow.getEventid());
                Map<String,Object> responseItem=new HashMap<>();
                responseItem.put("eventname",Event.getEventname());
                responseItem.put("eventid",Event.getEventid());
                responseItem.put("content",Event.getContent());
                responseItem.put("longitude",Event.getLongitude());
                responseItem.put("latitude",Event.getLatitude());
                responseItem.put("emergency",Event.isEmergency());
                responseItem.put("starttime",Event.getStarttime().getTime());
                responseItem.put("endtime",Event.getEndtime().getTime());
                response.add(responseItem);
            }
        }

        return response;
    }
}
