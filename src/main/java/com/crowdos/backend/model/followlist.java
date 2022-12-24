package com.crowdos.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "followlist")
public class followlist {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "uid")
    private long uid;
    @Column(name = "eventid")
    private long eventid;

    public followlist() {
    }

    public followlist(long id, long uid, long eventid) {
        this.id = id;
        this.uid = uid;
        this.eventid = eventid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getEventid() {
        return eventid;
    }

    public void setEventid(long follower) {
        this.eventid = follower;
    }
}
