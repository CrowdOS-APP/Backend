package com.crowdos.backend.controller;

import com.crowdos.backend.service.CommentService;
import com.crowdos.backend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;


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
    public List getComment(@RequestParam String token,@RequestParam Long eventId){
        return eventService.getComment(token,eventId);
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
}
