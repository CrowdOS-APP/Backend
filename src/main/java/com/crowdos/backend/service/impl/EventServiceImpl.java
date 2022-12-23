package com.crowdos.backend.service.impl;

import com.crowdos.backend.dao.EventDao;
import com.crowdos.backend.model.comment;
import com.crowdos.backend.model.event;
import com.crowdos.backend.model.user;
import com.crowdos.backend.service.CommentService;
import com.crowdos.backend.service.EventService;
import com.crowdos.backend.service.TokenService;
import com.crowdos.backend.service.UserService;
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

    public Map<String, Object> getEvenInfo(long eventId) {
        Map<String, Object> map = new HashMap<>(5);
        event aEvent = findEventByEid(eventId);
        map.put("longitude", aEvent.getLongitude());
        map.put("latitude", aEvent.getLatitude());
        map.put("startTime", aEvent.getStarttime().toString());
        map.put("endTime", aEvent.getEndtime().toString());
        map.put("content", aEvent.getContent());
        return map;
    }
    public Map<String, Object> uploadEvenInfo(String token, Map<String, String> info){
        Map<String, Object> map = new HashMap<>(1);
        if(tokenService.findUidByToken(token)==null){
            map.put("isSucceed",false);
        }else{
            Long uid = tokenService.findUidByToken(token).getUid();
            event aEvent = new event();
            aEvent.setContent(info.get("content"));
            aEvent.setStarttime(Timestamp.valueOf(info.get("startTime")));
            aEvent.setEndtime(Timestamp.valueOf(info.get("endTime")));
            aEvent.setLongitude(Double.parseDouble(info.get("longitude")));
            aEvent.setLatitude(Double.parseDouble(info.get("latitude")));
            aEvent.setEventname(info.get("eventName"));
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
        for(var entity:list0){
            if(entity.canAssignTo(aUser)){
                list.add(entity);
            }
        }
        return list;
    }
    public List getComment(String token, Long eventId) {
        if(tokenService.findUidByToken(token)==null){
            return null;
        }else{
            return commentService.getAllCommentInList(eventId);
        }
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

    public List getEmergencyList(String token, Map<String, Double> info){
        if(tokenService.findUidByToken(token)==null){
            return null;
        }else{
            Long uid = tokenService.findUidByToken(token).getUid();
            user aUser = userService.findUserById(uid);
            aUser.setLongitude(info.get("longitude"));
            aUser.setLatitude(info.get("latitude"));
            return getEmergencyEvent(aUser);
        }
    }
}
