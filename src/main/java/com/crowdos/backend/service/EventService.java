package com.crowdos.backend.service;

import com.crowdos.backend.model.event;
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

    Map<String, Object> getEvenInfo(long eventId);
    List<event> getEmergencyEvent(user aUser);
    List<event> getAllEventInList();

    Map<String, Object> uploadEvenInfo(String token, Map<String, String> info);

    List getComment(String token, Long eventId);

    Map<String, Object> postComment(String token, Long eventId, Map<String, String> comment);

    List getEmergencyList(String token, Map<String, Double> info);

    List<event> myEventList(String token);
}
