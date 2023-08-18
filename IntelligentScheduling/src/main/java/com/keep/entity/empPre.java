package com.keep.entity;

public class empPre {
   private String empId ;
   private String dayPre ;
   private String timePre;
   private Integer groupPre ;
   private String shopId;

    public empPre(String empId, String dayPre, String timePre, Integer groupPre, String shopId) {
        this.empId = empId;
        this.dayPre = dayPre;
        this.timePre = timePre;
        this.groupPre = groupPre;
        this.shopId = shopId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getDayPre() {
        return dayPre;
    }

    public void setDayPre(String dayPre) {
        this.dayPre = dayPre;
    }

    public String getTimePre() {
        return timePre;
    }

    public void setTimePre(String timePre) {
        this.timePre = timePre;
    }

    public Integer getGroupPre() {
        return groupPre;
    }

    public void setGroupPre(Integer groupPre) {
        this.groupPre = groupPre;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    @Override
    public String toString() {
        return "empPre{" +
                "empId='" + empId + '\'' +
                ", dayPre='" + dayPre + '\'' +
                ", timePre='" + timePre + '\'' +
                ", groupPre='" + groupPre + '\'' +
                ", shopId='" + shopId + '\'' +
                '}';
    }
}
