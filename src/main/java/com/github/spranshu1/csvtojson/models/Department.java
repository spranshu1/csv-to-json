package com.github.spranshu1.csvtojson.models;

public class Department {

    private String deptName;

    private Sector sector;

    public Department(String deptName, Sector sector) {
        this.deptName = deptName;
        this.sector = sector;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }
}
