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
    @Column(name = "follower")
    private long follower;

    public followlist() {
    }

    public followlist(long id, long uid, long follower) {
        this.id = id;
        this.uid = uid;
        this.follower = follower;
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

    public long getFollower() {
        return follower;
    }

    public void setFollower(long follower) {
        this.follower = follower;
    }
}
