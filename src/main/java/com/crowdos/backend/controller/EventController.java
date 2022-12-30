package com.crowdos.backend.controller;

import com.crowdos.backend.model.event;
import com.crowdos.backend.model.user;
import com.crowdos.backend.service.EventService;
import com.crowdos.backend.service.FollowService;
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

    @Autowired
    private FollowService followService;


    @GetMapping("/getEventInfo")
    public  Map<String, Object> getEventInfo(@RequestParam String token,@RequestParam long eventId){
        var response = eventService.getEventInfo(token,eventId);
        if(response!=null) return response;
        return new HashMap<>();
    }

    @PostMapping("/uploadEventInfo")
    public Map<String, Boolean> uploadEventInfo(@RequestParam String token,
                                                @RequestBody Map<String, String> info){
        var response = eventService.uploadEventInfo(token,info);
        if(response!=null) return response;
        return new HashMap<>();
    }

    @GetMapping("/getEventList")
    public List<event> getEventList(){
        var response = eventService.getAllEventInList();
        if(response!=null) return response;
        else return new ArrayList<>();
    }

    @GetMapping("/getComment")
    public List<Map<String,Object>> getComment(@RequestParam String token,@RequestParam Long eventid){
        var Token=tokenService.findUidByToken(token);
        List<Map<String,Object>> response=new ArrayList<>();
        if(Token!=null){
            var comments= eventService.getComment(Token,eventid);
            if(comments!=null){
                for(var entity:comments){
                    Map<String,Object> responseItem=new HashMap<>();
                    responseItem.put("commentid",entity.getCommentid());
                    responseItem.put("content",entity.getContent());
                    responseItem.put("username",userService.findUserById(entity.getUid()).getName());
                    response.add(responseItem);
                }
            }
        }
    return response;
    }

    @PostMapping("/postComment")
    public Map<String, Object> postComment(@RequestParam String token,
                            @RequestParam Long eventId,
                            @RequestBody Map<String, String> comment){
        var response = eventService.postComment(token,eventId,comment);
        if(response!=null) return response;
        return new HashMap<>();
    }

    @GetMapping("/getEmergencyList")
    public  List<Map<String,Object>> getEmergencyList(@RequestParam String token,
                                  @RequestParam double longitude,
                                  @RequestParam double latitude){
        var Token=tokenService.findUidByToken(token);
        List<Map<String,Object>> response=new ArrayList<>();
        if(Token!=null){
            long uid = Token.getUid();
            user aUser = userService.findUserById(uid);
            aUser.setLongitude(longitude);
            aUser.setLatitude(latitude);
            var eventlist=eventService.getEmergencyEvent(aUser);
            if(eventlist!=null){
                for(var evententity:eventlist){
                    Map<String,Object> responseItem=new HashMap<>();
                    responseItem.put("longitude",evententity.getLongitude());
                    responseItem.put("latitude",evententity.getLatitude());
                    responseItem.put("eventname",evententity.getEventname());
                    responseItem.put("eventid",evententity.getEventid());
                    responseItem.put("isFollowed",followService.checkIfFollowed(uid,evententity.getEventid()));
                    responseItem.put("content",evententity.getContent());
                    responseItem.put("starttime",evententity.getStarttime().getTime());
                    response.add(responseItem);
                }
            }
        }
        return response;
    }

    @GetMapping("/getEventsNearby")
    public List<event> getEventsNearby(@RequestParam String token,@RequestParam double longitude,@RequestParam double latitude){
        var response = eventService.getNearByEventList(token,longitude,latitude);
        if(response!=null) return response;
        return new ArrayList<>();
    }

    @GetMapping("/myEventList")
    public  List<Map<String,Object>> myEventList(@RequestParam String token){
        List<Map<String,Object>> eventList=eventService.myEventList(token);
        if(eventList!=null){
            return eventList;
        }
        return new ArrayList<>();
    }
}
