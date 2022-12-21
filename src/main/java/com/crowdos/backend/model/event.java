package com.crowdos.backend.model;

import cn.crowdos.kernel.Decomposer;
import cn.crowdos.kernel.constraint.Constraint;
import cn.crowdos.kernel.resource.Participant;
import cn.crowdos.kernel.resource.Task;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "event")
public class event implements Task {
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
    private String pictureUrl;

    public event() {
    }

    public event(long eventid, String eventname, String content, String place, Timestamp starttime, Timestamp endtime, boolean emergency, String pictureUrl) {
        this.eventid = eventid;
        this.eventname = eventname;
        this.content = content;
        this.place = place;
        this.starttime = starttime;
        this.endtime = endtime;
        this.emergency = emergency;
        this.pictureUrl = pictureUrl;
    }

    public long getEventid() {
        return eventid;
    }

    public void setEventid(long eventid) {
        this.eventid = eventid;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Timestamp getStarttime() {
        return starttime;
    }

    public void setStarttime(Timestamp starttime) {
        this.starttime = starttime;
    }

    public Timestamp getEndtime() {
        return endtime;
    }

    public void setEndtime(Timestamp endtime) {
        this.endtime = endtime;
    }

    public boolean isEmergency() {
        return emergency;
    }

    public void setEmergency(boolean emergency) {
        this.emergency = emergency;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @Override
    public TaskDistributionType getTaskDistributionType() {
        return TaskDistributionType.RECOMMENDATION;
    }

    @Override
    public TaskStatus getTaskStatus() {
        long time=new Date().getTime();
        if(time>=starttime.getTime()&&time<=endtime.getTime()){
            return TaskStatus.READY;
        }
        else if(time>=endtime.getTime())
            return TaskStatus.FINISHED;
        else
            return TaskStatus.IN_PROGRESS;
    }

    @Override
    public void setTaskStatus(TaskStatus taskStatus) {
    }

    @Override
    public List<Constraint> constraints() {
    List<Constraint> constraintList=new ArrayList<>();
//    constraintList.add(new SimpleSpatioConstraint());
//    constraintList.add(new SimpleTimeConstraint());
        return constraintList;
    }

    @Override
    public boolean canAssignTo(Participant participant) {
        return false;
    }

    @Override
    public boolean assignable() {
        return false;
    }

    @Override
    public boolean finished() {
        return false;
    }

    @Override
    public Decomposer<Task> decomposer() {
        return null;
    }
}
