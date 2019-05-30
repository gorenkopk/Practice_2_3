package com.gorenko.pavel.controller;

import com.gorenko.pavel.dao.OracleDAO;
import com.gorenko.pavel.model.EmployeeExtend;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestParam = req.getParameter("empno");
        int empno = 0;
        try {
            empno = Integer.parseInt(requestParam);
        } catch (Exception e) {
           req.getSession().setAttribute("IOerror", "Введенные данные не корректны. Повторите ввод.");
        }

        EmployeeExtend employeeExtend = OracleDAO.getInstance().getEmployee(empno);

        if (employeeExtend == null) {
            req.getSession().setAttribute("IOerror", "По Вашему запросу данных не найдено. Повторите ввод.");
        } else {
            req.getSession().setAttribute("employeeExtend", employeeExtend);
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(req,resp);

    }
}
