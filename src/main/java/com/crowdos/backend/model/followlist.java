package com.crowdos.backend.model;

import jakarta.persistence.*;

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
}
