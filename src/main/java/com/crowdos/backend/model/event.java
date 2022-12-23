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
    private String eventname;
    @Column
    private String content;
    @Column
    private long longitude;
    @Column
    private long latitude;
    @Column
    private Timestamp starttime;
    @Column
    private Timestamp endtime;
    @Column
    private boolean emergency;
    @Column
    private String pictureUrl;

    public event() {
    }

    public event(long eventid, String eventname, String content, long longitude, long latitude, Timestamp starttime, Timestamp endtime, boolean emergency, String pictureUrl) {
        this.eventid = eventid;
        this.eventname = eventname;
        this.content = content;
        this.longitude = longitude;
        this.latitude = latitude;
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
                return (Double.valueOf(((user) participant).getLongitude()).compareTo(Double.valueOf(longitude - 0.02)) > 0)
                        && (Double.valueOf(((user) participant).getLongitude()).compareTo(Double.valueOf(longitude + 0.02)) < 0)
                        && (Double.valueOf(((user) participant).getLatitude()).compareTo(Double.valueOf(latitude - 0.02)) > 0)
                        && (Double.valueOf(((user) participant).getLatitude()).compareTo(Double.valueOf(latitude + 0.02)) < 0)
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
