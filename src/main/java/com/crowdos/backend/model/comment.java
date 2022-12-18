package com.crowdos.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "comment")
public class comment {
    @Id
    @Column(name = "commentid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentid;
    @Column
    private long uid;
    private long eventid;
    private String content;
    private int uplevel;

    private boolean root; //If root is true, uplevel refers to eventid, else to commentid

    public comment() {
    }

    public comment(long commentid, long uid, long eventid, String content, int uplevel, boolean root) {
        this.commentid = commentid;
        this.uid = uid;
        this.eventid = eventid;
        this.content = content;
        this.uplevel = uplevel;
        this.root = root;
    }

    public long getCommentid() {
        return commentid;
    }

    public void setCommentid(long commentid) {
        this.commentid = commentid;
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

    public void setEventid(long eventid) {
        this.eventid = eventid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUplevel() {
        return uplevel;
    }

    public void setUplevel(int uplevel) {
        this.uplevel = uplevel;
    }

    public boolean isRoot() {
        return root;
    }

    public void setRoot(boolean root) {
        this.root = root;
    }
}
