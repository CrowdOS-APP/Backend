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
}
