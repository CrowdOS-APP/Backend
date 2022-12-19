package com.crowdos.backend.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "event")
public class event {
    @Id
    @Column(name="eventid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long eventid;
    @Column
    private String eventname;
    private String content;
    private String place;
    private Timestamp starttime;
    private Timestamp endtime;
    private boolean emergency;
    private String pictureUrl;

    public event() {
    }

    public event(long eventid, String eventname, String content, String place, Timestamp starttime, Timestamp endtime, boolean emergency, String pictureUrl) {
        this.eventid = eventid;
        this.eventname = eventname;
        this.content = content;
        this.place = place;
        this.starttime = starttime;
        this.endtime = endtime;
        this.emergency = emergency;
        this.pictureUrl = pictureUrl;
    }

    public long getEventid() {
        return eventid;
    }

    public void setEventid(long eventid) {
        this.eventid = eventid;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Timestamp getStarttime() {
        return starttime;
    }

    public void setStarttime(Timestamp starttime) {
        this.starttime = starttime;
    }

    public Timestamp getEndtime() {
        return endtime;
    }

    public void setEndtime(Timestamp endtime) {
        this.endtime = endtime;
    }

    public boolean isEmergency() {
        return emergency;
    }

    public void setEmergency(boolean emergency) {
        this.emergency = emergency;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
