package com.crowdos.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;

@Entity
@Table(name="token")
public class token {
    @Id
    @Column(name="uid")
    private int uid;
    @Column(name="token")
    private String token;
    @Column
    private Timestamp expiretime;

    public token() {
    }

    public token(int uid, String token, Timestamp expiretime) {
        this.uid = uid;
        this.token = token;
        this.expiretime = expiretime;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getExpiretime() {
        return expiretime;
    }

    public void setExpiretime(Timestamp expiretime) {
        this.expiretime = expiretime;
    }
}
