package com.crowdos.backend.controller;

import com.crowdos.backend.service.CommentService;
import com.crowdos.backend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


public class CommentController {

    @Autowired
    private CommentService commentService;

    public  Map<String, Object> postComment(@RequestParam String token,
                                            @RequestParam String eventId,
                                               @RequestBody Map<String, String> comment){
        return commentService.postComment(token,eventId,comment);
    }

}
