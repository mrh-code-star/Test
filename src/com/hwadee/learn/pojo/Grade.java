package com.hwadee.learn.pojo;

public class Grade {
    private int graid;
    private String graName;
    private String graMaster;
    private int sch_id;

    public int getGraid() {
        return graid;
    }

    public void setGraid(int graid) {
        this.graid = graid;
    }

    public String getGraName() {
        return graName;
    }

    public void setGraName(String graName) {
        this.graName = graName;
    }

    public String getGraMaster() {
        return graMaster;
    }

    public void setGraMaster(String graMaster) {
        this.graMaster = graMaster;
    }

    public int getSch_id() {
        return sch_id;
    }

    public void setSch_id(int sch_id) {
        this.sch_id = sch_id;
    }

    public Grade(int graid, String graName, String graMaster, int sch_id) {
        this.graid = graid;
        this.graName = graName;
        this.graMaster = graMaster;
        this.sch_id = sch_id;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "graid=" + graid +
                ", graName='" + graName + '\'' +
                ", graMaster='" + graMaster + '\'' +
                ", sch_id=" + sch_id +
                '}';
    }
}
