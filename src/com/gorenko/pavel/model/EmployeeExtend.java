package com.gorenko.pavel.model;

import java.sql.Date;

public class EmployeeExtend extends Employee {
    private int grade;
    private String dname;
    private String loc;

    public EmployeeExtend(int empno, String ename, String job, int mgr, Date hireDate, float sal, float comm, int deptno, int grade, String dname, String loc) {
        super(empno, ename, job, mgr, hireDate, sal, comm, deptno);
        this.grade = grade;
        this.dname = dname;
        this.loc = loc;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }
}
