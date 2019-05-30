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

public class ShowEmployees extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestParam = req.getParameter("task2");
        int requestTask = 0;
        try {
            requestTask = Integer.parseInt(requestParam);
        } catch (Exception e) {
            req.getSession().setAttribute("IOerror", "Введенные данные не корректны. Повторите ввод.");
        }

        if(requestTask == 1) {
            List<Employee> employees = OracleDAO.getInstance().getEmployees();

            if (employees == null) {
                req.getSession().setAttribute("IOerror", "По Вашему запросу данных не найдено. Повторите ввод.");
            } else {
                req.getSession().setAttribute("employees", employees);
            }
        } else if(requestTask == 2) {
            req.getSession().setAttribute("newEmployee", "1");
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(req,resp);
    }
}
