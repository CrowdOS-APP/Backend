package com.crowdos.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class user {
    @Id
    @Column(name="uid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    @Column(name="email")
    private String email;
    @Column
    private String passwd;
    private String name;
    private int privilege;
    private String avartarurl;
    private String signature;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }

    public String getAvartarurl() {
        return avartarurl;
    }

    public void setAvartarurl(String avartarUrl) {
        this.avartarurl = avartarUrl;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}

