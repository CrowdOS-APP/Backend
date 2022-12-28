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
        return eventService.getEventInfo(token,eventId);
    }

    @PostMapping("/uploadEventInfo")
    public Map<String, Boolean> uploadEventInfo(@RequestParam String token,
                                                @RequestBody Map<String, String> info){
        return eventService.uploadEventInfo(token,info);
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
        var Token=tokenService.findUidByToken(token);
        List<Map<String,Object>> response=new ArrayList<>();
        if(Token!=null){
            Long uid = Token.getUid();
            user aUser = userService.findUserById(uid);
            aUser.setLongitude(info.get("longitude"));
            aUser.setLatitude(info.get("latitude"));
            var eventlist=eventService.getEmergencyEvent(aUser);
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
        return response;
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
