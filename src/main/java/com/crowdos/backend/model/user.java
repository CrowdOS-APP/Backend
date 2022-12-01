package com.crowdos.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class user {
    @Id
    @Column(name="uid")
    @GeneratedValue(strategy = GenerationType.UUID)
    private long uid;
    @Id
    @Column(name="email")
    private String email;
    @Column
    private String passwd;
    private String name;
    private int privilege;
    private String avartarUrl;
    private String signature;
}