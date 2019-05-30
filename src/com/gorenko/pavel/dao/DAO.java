package com.gorenko.pavel.dao;

import com.gorenko.pavel.model.*;

import java.sql.Date;
import java.util.List;

public interface DAO {

    void connect();

    void disconnect();

    EmployeeExtend getEmployee(int empno);

    List<Employee> getEmployees();

    boolean insert(int empno, String ename, String job, int mgr, Date hireDate, float sal, float comm, int deptno);

    boolean remove(int empno);
}
