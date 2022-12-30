package com.crowdos.backend.controller;

import com.crowdos.backend.model.token;
import com.crowdos.backend.service.CommentService;
import com.crowdos.backend.service.EventService;
import com.crowdos.backend.service.TokenService;
import com.crowdos.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private EventService eventService;

    @GetMapping("/myComment")
    public List<Map<String,Object>> myComment(@RequestParam String token){
        token Token=tokenService.findUidByToken(token);
        List<Map<String,Object>> response= new ArrayList<>();
        if(Token!=null){
            var eventList=eventService.getUserEventByUid(Token.getUid());
            if(eventList!=null) for (var eventItem : eventList) {
                var commentList = commentService.getAllCommentInList(eventItem.getEventid());
                if (!commentList.isEmpty()) for (var commentItem : commentList) {
                    Map<String, Object> responseItem = new HashMap<>();
                    responseItem.put("commentid", commentItem.getCommentid());
                    responseItem.put("username", userService.findUserById(commentItem.getUid()).getName());
                    responseItem.put("eventid", eventItem.getEventid());
                    responseItem.put("content", commentItem.getContent());
                    responseItem.put("UID", commentItem.getUid());
                    responseItem.put("eventname", eventItem.getEventname());
                    response.add(responseItem);
                }
            }
        }
        return response;
    }
}
