package com.crowdos.backend.service;

import com.crowdos.backend.model.comment;
import com.crowdos.backend.model.event;
import com.crowdos.backend.model.token;
import com.crowdos.backend.model.user;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface EventService {
    event findEventByEid(Long eventid);
    event createEvent(event newEvent);
    event updateEvent(event newEvent);
    void deleteEventByEid(Long eventid);
    Page<event> getUsereventInPage(int pagenum, int pagesize, long id);
    List<event> getUserEventByUid(long id);

    Map<String, Object> getEventInfo(String token, long eventId);
    List<event> getEmergencyEvent(user aUser);
    List<event> getAllEventInList();

    Map<String,Boolean> uploadEventInfo(String token, Map<String, String> info);

    List<comment> getComment(token Token, Long eventId);

    Map<String, Object> postComment(String token, Long eventId, Map<String, String> comment);

    List<event> getEmergencyList(String token, Map<String, Double> info);

    List<event> getNearByEventList(String token,double longitude,double latitude);

    List<Map<String,Object>> myEventList(String token);
}
