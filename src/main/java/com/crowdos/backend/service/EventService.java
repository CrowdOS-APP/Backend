package com.crowdos.backend.service;

import com.crowdos.backend.model.event;
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
    Page<event> getEventInPage(int pagenum, int pagesize, long id);
    List<event> getEventInList(long id);

    Map<String, Object> getEvenInfo(long eventId);

}
