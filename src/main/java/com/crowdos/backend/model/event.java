package com.crowdos.backend.model;

import cn.crowdos.kernel.Decomposer;
import cn.crowdos.kernel.constraint.*;
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
    @Column(name = "eventid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long eventid;

    @Column
    private long uid;
    @Column
    private String eventname;
    @Column
    private String content;
    @Column
    private double longitude;
    @Column
    private double latitude;
    @Column
    private Timestamp starttime;
    @Column
    private Timestamp endtime;
    @Column
    private boolean emergency;

    public event() {
    }

    public event(long eventid, long uid, String eventname, String content, double longitude, double latitude, Timestamp starttime, Timestamp endtime, boolean emergency) {
        this.eventid = eventid;
        this.uid = uid;
        this.eventname = eventname;
        this.content = content;
        this.longitude = longitude;
        this.latitude = latitude;
        this.starttime = starttime;
        this.endtime = endtime;
        this.emergency = emergency;
    }

    public long getEventid() {
        return eventid;
    }

    public void setEventid(long eventid) {
        this.eventid = eventid;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
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

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
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

    @Override
    public TaskDistributionType getTaskDistributionType() {
        return TaskDistributionType.RECOMMENDATION;
    }

    @Override
    public TaskStatus getTaskStatus() {
        long time = new Date().getTime();
        if (time >= starttime.getTime() && time <= endtime.getTime()) {
            return TaskStatus.READY;
        } else if (time >= endtime.getTime())
            return TaskStatus.FINISHED;
        else
            return TaskStatus.IN_PROGRESS;
    }

    @Override
    public void setTaskStatus(TaskStatus taskStatus) {
    }

    @Override
    public List<Constraint> constraints() {
        List<Constraint> constraintList = new ArrayList<>();
        try {
            constraintList.add(new SimpleSpatioConstraint(new Coordinate(longitude - 0.02, this.latitude - 0.02), new Coordinate(longitude + 0.02, this.latitude + 0.02)));
        } catch (InvalidConstraintException e) {
            throw new RuntimeException(e);
        }
        try {
            constraintList.add(new SimpleTimeConstraint(starttime, endtime));
        } catch (InvalidConstraintException e) {
            throw new RuntimeException(e);
        }
        return constraintList;
    }

    @Override
    public boolean canAssignTo(Participant participant) {
        if ((participant instanceof user) && participant.available()) {
            {
                return (Double.compare(((user) participant).getLongitude(), longitude - 0.02) > 0)
                        && (Double.compare(((user) participant).getLongitude(), longitude + 0.02) < 0)
                        && (Double.compare(((user) participant).getLatitude(), latitude - 0.02) > 0)
                        && (Double.compare(((user) participant).getLatitude(), latitude + 0.02) < 0)
                        && (new Date().getTime()>=starttime.getTime())
                        && (new Date().getTime()<=endtime.getTime());
            }
        }
        return false;
    }

    @Override
    public boolean assignable() {
        return getTaskStatus() == TaskStatus.READY;
    }

    @Override
    public boolean finished() {
        return getTaskStatus() == TaskStatus.FINISHED;
    }

    @Override
    public Decomposer<Task> decomposer() {
        return null;
    }
}
