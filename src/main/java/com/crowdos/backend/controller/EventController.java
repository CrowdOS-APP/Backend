package com.crowdos.backend.controller;

import com.crowdos.backend.model.event;
import com.crowdos.backend.service.EventService;
import com.crowdos.backend.service.TokenService;
import com.crowdos.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;


    @GetMapping("/getEvenInfo")
    public  Map<String, Object> getEvenInfo(long eventId){
        return eventService.getEvenInfo(eventId);
    }

    @PostMapping("/uploadEvenInfo")
    public  Map<String, Object> uploadEvenInfo(@RequestParam String token,
            @RequestBody Map<String, String> info){
        return eventService.uploadEvenInfo(token,info);
    }

    @GetMapping("/getEventList")
    public List getEventList(){
        return eventService.getAllEventInList();
    }

    @GetMapping("/getComment")
    public List getComment(@RequestParam String token,@RequestParam Long eventid){
        var Token=tokenService.findUidByToken(token);
        List<Map<String,Object>> response=new ArrayList<>();
        if(Token!=null){
            var comments= eventService.getComment(Token,eventid);
            var uid =Token.getUid();
            for(var entity:comments){
                Map<String,Object> responseItem=new HashMap<>();
                responseItem.put("commentid",entity.getCommentid());
                responseItem.put("content",entity.getContent());
                responseItem.put("username",userService.findUserById(Token.getUid()).getName());
                response.add(responseItem);
            }
        }
    return response;
    }

    @PostMapping("/postComment")
    public Map<String, Object> postComment(@RequestParam String token,
                            @RequestParam Long eventId,
                            @RequestBody Map<String, String> comment){
        return eventService.postComment(token,eventId,comment);
    }

    @GetMapping("/getEmergencyList")
    public  List getEmergencyList(@RequestParam String token,
                                  @RequestBody Map<String, Double> info){
        return eventService.getEmergencyList(token,info);
    }

    @GetMapping("/getEventsNearby")
    public List getEventsNearby(@RequestParam String token,@RequestParam double longitude,@RequestParam double latitude){
        return eventService.getNearByEventList(token,longitude,latitude);
    }

    @GetMapping("/myEventList")
    public  List<event> myEventList(@RequestParam String token){
        List<event> eventList=eventService.myEventList(token);
        if(eventList!=null){
            return eventList;
        }
        return new ArrayList<>();
    }
}
