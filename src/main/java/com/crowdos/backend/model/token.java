package com.crowdos.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.context.annotation.EnableMBeanExport;

@Entity
@Table(name="token")
public class token {
    @Id
    @Column(name="uid")
    private int uid;
    @Id
    @Column(name="token")
    private String token;
}
