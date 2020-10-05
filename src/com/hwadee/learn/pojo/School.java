package com.hwadee.learn.pojo;

public class School {
    private int schid;
    private String schNAme;

    public int getSchid() {
        return schid;
    }

    public void setSchid(int schid) {
        this.schid = schid;
    }

    public String getSchNAme() {
        return schNAme;
    }

    public void setSchNAme(String schNAme) {
        this.schNAme = schNAme;
    }

    public School(int schid, String schNAme) {
        this.schid = schid;
        this.schNAme = schNAme;
    }

    @Override
    public String toString() {
        return "Shool{" +
                "schid=" + schid +
                ", schNAme='" + schNAme + '\'' +
                '}';
    }
}
