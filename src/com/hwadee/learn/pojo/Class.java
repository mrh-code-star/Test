package com.hwadee.learn.pojo;

public class Class {
    private int claid;
    private String claName;
    private String claMaster;
    private int grade_id;

    public int getClaid() {
        return claid;
    }

    public void setClaid(int claid) {
        this.claid = claid;
    }

    public String getClaName() {
        return claName;
    }

    public void setClaName(String claName) {
        this.claName = claName;
    }

    public String getClaMaster() {
        return claMaster;
    }

    public void setClaMaster(String claMaster) {
        this.claMaster = claMaster;
    }

    public int getGrade_id() {
        return grade_id;
    }

    public void setGrade_id(int grade_id) {
        this.grade_id = grade_id;
    }

    public Class(int claid, String claName, String claMaster, int grade_id) {
        this.claid = claid;
        this.claName = claName;
        this.claMaster = claMaster;
        this.grade_id = grade_id;
    }

    @Override
    public String toString() {
        return "Class{" +
                "claid=" + claid +
                ", claName='" + claName + '\'' +
                ", claMaster='" + claMaster + '\'' +
                ", grade_id=" + grade_id +
                '}';
    }
}
