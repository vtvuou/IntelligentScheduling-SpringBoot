package com.keep.entity;

public class shopFlow {
    private String id ;
    private Integer week;
    private Integer day;
    private Integer startTime;
    private Integer endTime;
    private Double cusFlow;

    public shopFlow() {}
    public shopFlow(String id, Integer week, Integer day, Integer startTime, Integer endTime, Double cusFlow) {
        this.id = id;
        this.week = week;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.cusFlow = cusFlow;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public Double getCusFlow() {
        return cusFlow;
    }

    public void setCusFlow(Double cusFlow) {
        this.cusFlow = cusFlow;
    }

    @Override
    public String toString() {
        return "shopFlow{" +
                "id='" + id + '\'' +
                ", week=" + week +
                ", day=" + day +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", cusFlow=" + cusFlow +
                '}';
    }
}

