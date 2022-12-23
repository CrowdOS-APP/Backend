package com.crowdos.backend.controller;

import com.crowdos.backend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}