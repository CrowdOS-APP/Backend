package com.crowdos.backend.controller;

import com.crowdos.backend.model.comment;
import com.crowdos.backend.model.token;
import com.crowdos.backend.service.CommentService;
import com.crowdos.backend.service.TokenService;
import com.crowdos.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/postComment")
    public  Map<String, Object> postComment(@RequestParam String token,
                                            @RequestParam String eventId,
                                               @RequestBody Map<String, String> comment){
        return commentService.postComment(token,eventId,comment);
    }

    @GetMapping("/myComment")
    public List<Map<String,Object>> myComment(@RequestParam String token){
        token Token=tokenService.findUidByToken(token);
        List<Map<String,Object>> response= new ArrayList<>();
        if(Token!=null){
            List<comment> commentList=commentService.getUserCommentInList(Token.getUid());
            String username=userService.findUserById(Token.getUid()).getName();
            for(var commentItem:commentList){
                Map<String,Object> responseItem= new HashMap<>();
                responseItem.put("commentid",commentItem.getCommentid());
                responseItem.put("username",username);
                responseItem.put("eventid",commentItem.getEventid());
                responseItem.put("content",commentItem.getContent());
                responseItem.put("UID",commentItem.getUid());
                response.add(responseItem);
            }
        }
        return response;
    }
}
