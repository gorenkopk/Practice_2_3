package com.gorenko.pavel.controller;

import com.gorenko.pavel.dao.OracleDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class AddEmp extends HttpServlet {

    public static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean inserted = false;

        try {
            int empno = Integer.parseInt(req.getParameter("empno"));
            String ename = req.getParameter("ename");
            String job = req.getParameter("job");
            int mgr = 0;
            if(!req.getParameter("mgr").equals("")) {
                mgr = Integer.parseInt(req.getParameter("mgr"));
            }
            Date hireDate = new Date((dateFormat.parse(req.getParameter("hiredate"))).getTime());
            float sal = Float.parseFloat(req.getParameter("sal"));
            float comm = 0;
            if(!req.getParameter("comm").equals("")) {
                Float.parseFloat(req.getParameter("comm"));
            }
            int deptno = Integer.parseInt(req.getParameter("deptno"));

            inserted = OracleDAO.getInstance().insert(empno, ename, job, mgr, hireDate, sal, comm, deptno);

        } catch (Exception e) {
            throw  new Error("Не корректный ввод - " + e.getMessage());
        }

        if(inserted) {
            req.getSession().setAttribute("Task2", "1");
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(req, resp);
    }
}
