package com.gorenko.pavel.dao;

import com.gorenko.pavel.model.Employee;
import com.gorenko.pavel.model.EmployeeExtend;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OracleDAO implements DAO {

    public static final OracleDAO instance = new OracleDAO();
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public static OracleDAO getInstance() {
        return instance;
    }

    @Override
    public void connect() {

// Коннект к базе для задания № 1
/*        try {
            Class.forName("oracle.jdbc.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE",
                    "gorenko",
                    "Pasha1985");
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }*/
// Коннект к базе для задания № 3
    try {
        Datasources connectionFromXML = XMLConf.parseXMLConfiguration();
        Class.forName(connectionFromXML.datasource.get(0).driverClass);
        connection = DriverManager.getConnection(connectionFromXML.datasource.get(0).connectionUrl,
                                                 connectionFromXML.datasource.get(0).userName,
                                                 connectionFromXML.datasource.get(0).password);

        } catch (ParserConfigurationException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } catch (SAXException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    @Override
    public void disconnect() {
        try {
            if (connection != null) {
                connection.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            throw new Error(e.getMessage());
        }
    }

    @Override
    public EmployeeExtend getEmployee(int empno) {
        EmployeeExtend employeeExtend = null;
        connect();
        try {
            preparedStatement = connection.prepareStatement("select EMP.*, DEPT.dname, DEPT.loc, SALGRADE.grade " +
                    "from EMP left outer join DEPT on EMP.deptno = DEPT.deptno left outer join " +
                    "SALGRADE on EMP.sal between SALGRADE.minsal and SALGRADE.hisal where EMP.empno = ?");
            preparedStatement.setInt(1, empno);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()) {
                System.out.println("No Data Found.");
            } else {

                    String ename = resultSet.getString("ENAME");
                    String job = resultSet.getString("JOB");
                    int mgr = resultSet.getInt("MGR");
                    Date hiredate = resultSet.getDate("HIREDATE");
                    float sal = resultSet.getFloat("SAL");
                    float comm = resultSet.getFloat("COMM");
                    int deptno = resultSet.getInt("DEPTNO");
                    String dname = resultSet.getString("DNAME");
                    String loc = resultSet.getString("LOC");
                    int grade = resultSet.getInt("GRADE");
                    if(!resultSet.next()) {
                        employeeExtend = new EmployeeExtend(empno, ename, job, mgr, hiredate, sal, comm, deptno, grade, dname, loc);
                    } else {
                        throw new Error("Number of rows are more than one.");
                    }
            }
        } catch (SQLException e) {
            throw new Error(e.getMessage());
        }
        disconnect();
        return employeeExtend;
    }

    @Override
    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        connect();
        try {
            preparedStatement = connection.prepareStatement("select EMP.* from EMP order by EMP.deptno, EMP.ename");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employees.add(parseEmployee(resultSet));
            }
        } catch (SQLException e) {
            throw new Error(e.getMessage());
        }
        disconnect();
        return employees;
    }


    private Employee parseEmployee(ResultSet resultSet) {
        Employee employee = null;
        try {
            int empno = resultSet.getInt("EMPNO");
            String ename = resultSet.getString("ENAME");
            String job = resultSet.getString("JOB");
            int mgr = resultSet.getInt("MGR");
            Date hiredate = resultSet.getDate("HIREDATE");
            float sal = resultSet.getFloat("SAL");
            float comm = resultSet.getFloat("COMM");
            int deptno = resultSet.getInt("DEPTNO");
            employee = new Employee(empno, ename, job, mgr, hiredate, sal, comm, deptno);
        } catch (SQLException e) {
            throw new Error(e.getMessage());
        }
        return employee;
    }

    @Override
    public boolean insert(int empno, String ename, String job, int mgr, java.sql.Date hireDate, float sal, float comm, int deptno) {
        boolean result = false;
        connect();
        try {
                preparedStatement = connection.prepareStatement("INSERT INTO EMP (EMPNO, ENAME, JOB, MGR, HIREDATE, "
                                                                    +" SAL, COMM, DEPTNO) VALUES (?,?,?,?,?,?,?,?)");
                preparedStatement.setInt(1, empno);
                preparedStatement.setString(2, ename);
                preparedStatement.setString(3, job);
                preparedStatement.setInt(4, mgr);
                preparedStatement.setDate(5, hireDate);
                preparedStatement.setFloat(6, sal);
                preparedStatement.setFloat(7, comm);
                preparedStatement.setInt(8, deptno);
                int rows = preparedStatement.executeUpdate();

                if(rows == 1){
                    result = true;
                }

        } catch (SQLException e) {
            throw new Error(e.getMessage());
        }
        disconnect();
        return result;
    }

    @Override
    public boolean remove(int empno) {
        boolean result = false;
        connect();
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM EMP WHERE EMPNO = ?");
            preparedStatement.setInt(1, empno);
            int res = preparedStatement.executeUpdate();
            result = ((res > 0) ? true : false);
        } catch (SQLException e) {
            throw new Error(e.getMessage());
        }
        return result;
    }
}
