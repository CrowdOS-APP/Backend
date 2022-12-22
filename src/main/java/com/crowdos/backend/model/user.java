package com.crowdos.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class user {
    @Id
    @Column(name = "uid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;
    @Column
    private String email;
    private String passwd;
    private String name;
    private int privilege;
    private String signature;

    @Transient
    private long longitude;
    private long latitude;
    public user() {
    }

    public user(long uid, String email, String passwd, String name, int privilege, String signature,long longitude, long latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.uid = uid;
        this.email = email;
        this.passwd = passwd;
        this.name = name;
        this.privilege = privilege;
        this.signature = signature;
    }

    public long getUid() {
        return uid;
    }

//    public void setUid(long uid) {
//        this.uid = uid;
//    }
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

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }
}

