package com.crowdos.backend.service.impl;

import com.crowdos.backend.dao.EventDao;
import com.crowdos.backend.model.comment;
import com.crowdos.backend.model.event;
import com.crowdos.backend.model.token;
import com.crowdos.backend.model.user;
import com.crowdos.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    TokenService tokenService;

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    @Autowired
    FollowService followService;

    @Autowired
    private EventDao eventDao;


    public event findEventByEid(Long eventid) {
        return eventDao.findById(eventid).orElse(null);
    }

    public event createEvent(event newEvent) {
        return eventDao.save(newEvent);
    }

    public event updateEvent(event newEvent) {
        return eventDao.save(newEvent);
    }

    public void deleteEventByEid(Long eventid) {
        eventDao.deleteById(eventid);
    }

    public Page<event> getUsereventInPage(int pagenum, int pagesize, long id) {
        PageRequest pageRequest = PageRequest.of(pagenum, pagesize, Sort.Direction.DESC);
        return eventDao.findAll((Specification<event>) (root, query, builder) -> query.where(builder.equal(root.get("uid"), id)).getRestriction(), pageRequest);
    }

    public List<event> getUserEventByUid(long id) {
        return eventDao.findAll((Specification<event>) (root, query, builder) -> query.where(builder.equal(root.get("uid"), id)).getRestriction());
    }

    public List<event> getAllEventInList(){
        return eventDao.findAll();
    }

    public Map<String, Object> getEventInfo(String token, long eventId) {
        Map<String, Object> map = new HashMap<>(5);
        var Token=tokenService.findUidByToken(token);
        if(Token!=null){
            event aEvent = findEventByEid(eventId);
            if(aEvent!=null){
                map.put("longitude", aEvent.getLongitude());
                map.put("latitude", aEvent.getLatitude());
                map.put("startTime", aEvent.getStarttime().getTime());
                map.put("endTime", aEvent.getEndtime().getTime());
                map.put("content", aEvent.getContent());
                map.put("eventName",aEvent.getEventname());
                map.put("isFollowed",followService.checkIfFollowed(Token.getUid(),aEvent.getEventid()));
            }
        }
        return map;
    }
    public Map<String, Boolean> uploadEventInfo(String token, Map<String, String> info){
        Map<String, Boolean> map = new HashMap<>(1);
        var Token =tokenService.findUidByToken(token);
        if(Token==null){
            map.put("isSucceed",false);
        }else{
            long uid = Token.getUid();
            event aEvent = new event();
            aEvent.setContent(info.get("content"));
            aEvent.setStarttime(new Timestamp(Long.parseLong(info.get("startTime"))));
            aEvent.setEndtime(new Timestamp(Long.parseLong(info.get("endTime"))));
            aEvent.setLongitude(Double.parseDouble(info.get("longitude")));
            aEvent.setLatitude(Double.parseDouble(info.get("latitude")));
            aEvent.setUid(uid);
            aEvent.setEventname(info.get("title"));
            aEvent.setEmergency(Boolean.parseBoolean(info.get("isUrgent")));
            createEvent(aEvent);
            map.put("isSucceed",true);
        }
        return map;
    }

    public List<event> getEmergencyEvent(user aUser) {
        var list0 = eventDao.findAll((Specification<event>) (root, query, criteriaBuilder) -> query.where(criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("emergency"), true)),
                criteriaBuilder.lessThanOrEqualTo(root.get("longitude"), aUser.getLongitude() + 0.02),
                criteriaBuilder.greaterThanOrEqualTo(root.get("longitude"), aUser.getLongitude() - 0.02),
                criteriaBuilder.lessThanOrEqualTo(root.get("latitude"), aUser.getLatitude() + 0.02),
                criteriaBuilder.greaterThanOrEqualTo(root.get("latitude"), aUser.getLatitude() - 0.02)
        ).getRestriction());
        List<event> list = new ArrayList<>();
        if(!list0.isEmpty()){
            for(var entity:list0){
                if(entity.canAssignTo(aUser)){
                    list.add(entity);
                }
            }
        }
        return list;
    }

    public List<event> getEventsNearBy(user aUser) {
        var list0 = eventDao.findAll((Specification<event>) (root, query, criteriaBuilder) -> query.where(criteriaBuilder.and(
                criteriaBuilder.lessThanOrEqualTo(root.get("longitude"), aUser.getLongitude() + 0.02),
                criteriaBuilder.greaterThanOrEqualTo(root.get("longitude"), aUser.getLongitude() - 0.02),
                criteriaBuilder.lessThanOrEqualTo(root.get("latitude"), aUser.getLatitude() + 0.02),
                criteriaBuilder.greaterThanOrEqualTo(root.get("latitude"), aUser.getLatitude() - 0.02))).getRestriction());
        List<event> list = new ArrayList<>();
        if(!list0.isEmpty()){
            for(var entity:list0){
                if(entity.canAssignTo(aUser)){
                    list.add(entity);
                }
            }
        }
        return list;
    }
    public List<comment> getComment(token Token, Long eventId) {
        if(Token==null) return null;
        else return commentService.getAllCommentInList(eventId);
    }
    public Map<String, Object> postComment(String token, Long eventId, Map<String, String> comment){
        Map<String, Object> map = new HashMap<>(1);
        if(tokenService.findUidByToken(token)==null){
            map.put("isSucceed",false);
        }else{
            comment aComment = new comment();
            aComment.setContent(comment.get("comment"));
            aComment.setUid(tokenService.findUidByToken(token).getUid());
            aComment.setEventid(eventId);
            commentService.createComment(aComment);
            map.put("isSucceed",true);
        }
        return map;
    }

    public List<event> getEmergencyList(String token, Map<String, Double> info){
        var Token=tokenService.findUidByToken(token);
        if(Token==null){
            return null;
        }else{
            Long uid = Token.getUid();
            user aUser = userService.findUserById(uid);
            aUser.setLongitude(info.get("longitude"));
            aUser.setLatitude(info.get("latitude"));
            return getEmergencyEvent(aUser);
        }
    }
    public List<event> getNearByEventList(String token,double longitude,double latitude){
        var Token=tokenService.findUidByToken(token);
        if(Token==null){
            return null;
        }else{
            return getAllEventInList();
        }
    }

    public List<Map<String,Object>> myEventList(String token){
        var Token=tokenService.findUidByToken(token);
        List<Map<String,Object>> response=new ArrayList<>();
        if(Token!=null){
            var events = getUserEventByUid(Token.getUid());
            if(events!=null){
                for(var Event:events){
                    Map<String,Object> responseItem=new HashMap<>();
                    responseItem.put("eventname",Event.getEventname());
                    responseItem.put("eventid",Event.getEventid());
                    responseItem.put("content",Event.getContent());
                    responseItem.put("uid",Token.getUid());
                    responseItem.put("longitude",Event.getLongitude());
                    responseItem.put("latitude",Event.getLatitude());
                    responseItem.put("emergency",Event.isEmergency());
                    responseItem.put("starttime",Event.getStarttime().getTime());
                    responseItem.put("endtime",Event.getEndtime().getTime());
                    response.add(responseItem);
                }
            }
        }
        return response;
    }
}
