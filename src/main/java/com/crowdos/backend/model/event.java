package com.crowdos.backend.model;

import jakarta.persistence.*;

import java.net.URL;
import java.sql.Time;
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
    private String pictureurl;
}
