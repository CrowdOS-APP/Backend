package com.crowdos.backend.model;

import cn.crowdos.kernel.constraint.Condition;
import cn.crowdos.kernel.constraint.wrapper.DoubleCondition;
import cn.crowdos.kernel.constraint.wrapper.PrimitiveCondition;
import cn.crowdos.kernel.resource.Participant;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "user")
public class user implements Participant {
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
    private double longitude;
    @Transient
    private double latitude;
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

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    @Override
    public ParticipantStatus getStatus() {
        return ParticipantStatus.AVAILABLE;
    }

    @Override
    public void setStatus(ParticipantStatus participantStatus) {}

    @Override
    public boolean hasAbility(Class<? extends Condition> aClass) {
        return aClass == DoubleCondition.class;
    }

    @Override
    public Condition getAbility(Class<? extends Condition> aClass) {
        return new PrimitiveCondition<>((new Date()).getTime()) {
        };
    }

    @Override
    public boolean available() {
        return getStatus()==ParticipantStatus.AVAILABLE;
    }
}

