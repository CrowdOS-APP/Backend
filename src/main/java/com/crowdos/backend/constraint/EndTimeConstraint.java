package com.crowdos.backend.constraint;

import cn.crowdos.kernel.Decomposer;
import cn.crowdos.kernel.constraint.Condition;
import cn.crowdos.kernel.constraint.Constraint;
import cn.crowdos.kernel.constraint.wrapper.LongCondition;

import java.util.Date;

public class EndTimeConstraint implements Constraint {

    public EndTimeConstraint(long currentTime) {
        this.currentTime = currentTime;
    }

    public EndTimeConstraint() {
        currentTime = (new Date()).getTime();
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    long currentTime;

    @Override
    public boolean satisfy(Condition condition) {
        return (condition instanceof LongCondition) && (((LongCondition) condition).primitive <= currentTime);
    }

    @Override
    public Class<? extends Condition> getConditionClass() {
        return LongCondition.class;
    }

    @Override
    public String description() {
        return "EndTime";
    }

    @Override
    public Decomposer<Constraint> decomposer() {
        return null;
    }
}
