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
    Page<event> getAllEventInPage(int pagenum, int pagesize, long id);
    List<event> getUserEventByUid(long id);

    Map<String, Object> getEvenInfo(long eventId);
    List<event> getEmergencyEvent(user aUser);
    List<event> getUserEventInList();

    Map<String, Object> uploadEvenInfo(String token, Map<String, String> info);

    List getEventList();
}
