package com.keep.entity;

public class employee {
    private String ID ;
    private String empName;
    private String email ;
    private String pw;
    private String job;
    private String empShopID;

    public employee(){}
    public employee(String ID, String empName, String email, String pw, String job, String empShopID) {
        this.ID = ID;
        this.empName = empName;
        this.email = email;
        this.pw = pw;
        this.job = job;
        this.empShopID = empShopID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEmpShopID() {
        return empShopID;
    }

    public void setEmpShopID(String empShopID) {
        this.empShopID = empShopID;
    }

    @Override
    public String toString() {
        return "emp{" +
                "ID='" + ID + '\'' +
                ", empName='" + empName + '\'' +
                ", email='" + email + '\'' +
                ", pw='" + pw + '\'' +
                ", job='" + job + '\'' +
                ", empShopID='" + empShopID + '\'' +
                '}';
    }
}
