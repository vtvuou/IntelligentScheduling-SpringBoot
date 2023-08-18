package com.keep.entity;

public class arrange {
    private int ine;
   private String id;
   private String name;
   private String job;
   private String task;
   private String empShopID;
   private int startTime;
   private int endTime;
   private int week;
   private int day;

    public arrange() {}

    public arrange(int ine, String id, String name, String job, String task, String empShopID, int startTime, int endTime, int week, int day) {
        this.ine = ine;
        this.id = id;
        this.name = name;
        this.job = job;
        this.task = task;
        this.empShopID = empShopID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.week = week;
        this.day = day;
    }

    public int getIne() {
        return ine;
    }

    public void setIne(int ine) {
        this.ine = ine;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getEmpShopID() {
        return empShopID;
    }

    public void setEmpShopID(String empShopID) {
        this.empShopID = empShopID;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "arrange{" +
                "ine=" + ine +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", job='" + job + '\'' +
                ", task='" + task + '\'' +
                ", empShopID='" + empShopID + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", week=" + week +
                ", day=" + day +
                '}';
    }
}
