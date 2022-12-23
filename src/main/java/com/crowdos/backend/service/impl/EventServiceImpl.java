package com.crowdos.backend.service.impl;

import com.crowdos.backend.dao.EventDao;
import com.crowdos.backend.model.event;
import com.crowdos.backend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventDao eventDao;

    public event findEventByEid(Long eventid) {
        Optional<event> entity = eventDao.findById(eventid);
        return entity.orElse(null);
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

    public Page<event> getEventInPage(int pagenum, int pagesize, long id) {
        PageRequest pageRequest = PageRequest.of(pagenum, pagesize, Sort.Direction.DESC);
        return eventDao.findAll((Specification<event>) (root, query, builder) -> query.where(builder.equal(root.get("eventid"), id)).getRestriction(), pageRequest);
    }

    public List<event> getEventInList(long id) {
        return eventDao.findAll((Specification<event>) (root, query, builder) -> query.where(builder.equal(root.get("eventid"), id)).getRestriction());
    }
}
