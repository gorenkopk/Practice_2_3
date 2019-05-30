package com.gorenko.pavel.controller;

import com.gorenko.pavel.dao.OracleDAO;
import com.gorenko.pavel.model.Employee;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class RemoveEmp extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestParam = req.getParameter("empno");
        int empno = 0;
        try {
            empno = Integer.parseInt(requestParam);
        } catch (Exception e) {
            req.getSession().setAttribute("IOerror", "Введенные данные не корректны. Повторите ввод.");
        }

        boolean removed = OracleDAO.getInstance().remove(empno);

            req.getSession().setAttribute("Task2", "1");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(req, resp);


    }
}
