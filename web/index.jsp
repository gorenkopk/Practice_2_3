<%@ page import="com.gorenko.pavel.model.EmployeeExtend" %>
<%@ page import="com.gorenko.pavel.model.Employee" %>
<%@ page import="java.util.List" %>
<%@ page import="com.gorenko.pavel.dao.Datasources" %>
<%@ page import="com.gorenko.pavel.dao.XMLConf" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 20.05.2019
  Time: 22:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="pages/error.jsp" %>
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Практическая 3</title>
  </head>
  <body>
  <%  String alerts = (String) request.getSession().getAttribute("IOerror"); %>
  <% if (alerts != null) {%>
  <script type="text/javascript">
      alert("<%=alerts%>");
  </script>
  <%}%>
  <% request.getSession().removeAttribute("IOerror");%>

    <h2>Задание 1</h2>
    <hr/>
    <form action="task1" method="post">
      <label for="empno">Для получение информации по работнику введите его идентификатор: </label>
      <input type="number" name="empno" id="empno"/>
      <input type="submit" value="Искать"/>
    </form>


      <%  EmployeeExtend emp = (EmployeeExtend) request.getSession().getAttribute("employeeExtend"); %>
      <% if (emp != null) {%>
      <div>
          <ul>
            <li>Имя: <b><%=emp.getEname()%></b></li>
            <li>Должность: <b><%=emp.getJob()%></b></li>
            <li>Менеджер: <b><%=emp.getMgr()%></b></li>
            <li>Дата приема на работу: <b><%=emp.getHireDate()%></b></li>
            <li>Зарплата: <b><%=emp.getSal()%></b></li>
            <li>Комиссионные: <b><%=emp.getComm()%></b></li>
            <li>Место работы: <b><%=emp.getLoc()%></b></li>
            <li>Название подразделение: <b><%=emp.getDname()%></b></li>
            <li>Разряд ЕТС: <b><%=emp.getGrade()%></b></li>
          </ul>
      </div>
      <%}%>
      <% request.getSession().removeAttribute("employeeExtend");%>

  <h2>Задание 2</h2>
  <hr/>

  <form action="task2" method="post">
    <button id="task2" name="task2" type="submit" value="1">Показать список работников</button>
    <button name="task2" type="submit" value="2">Добавить нового работника</button>
  </form>

  <% String newempl = (String) request.getSession().getAttribute("newEmployee");
    if(newempl != null) {%>
        <jsp:include page="pages/newemp.jsp"></jsp:include>
  <%}
    request.getSession().removeAttribute("newEmployee");%>


  <% List<Employee> employees = (List<Employee>) request.getSession().getAttribute("employees"); %>
  <% if(employees != null) {%>
  <div>
    <table>
      <th>Номер</th>
      <th>Имя</th>
      <th>Должность</th>
      <th>Менеджер</th>
      <th>Дата принятия</th>
      <th>Зарплата</th>
      <th>Комиссионные</th>
      <th>Департамент</th>
      <th>Действие</th>

    <% for (int i = 0; i < employees.size(); i++) { %>
      <tr>
        <td><%=employees.get(i).getEmpno()%></td>
        <td><%=employees.get(i).getEname()%></td>
        <td><%=employees.get(i).getJob()%></td>
        <td><%=employees.get(i).getMgr()%></td>
        <td><%=employees.get(i).getHireDate()%></td>
        <td><%=employees.get(i).getSal()%></td>
        <td><%=employees.get(i).getComm()%></td>
        <td><%=employees.get(i).getDeptno()%></td>
        <td><a href="remove?empno=<%=employees.get(i).getEmpno()%>">Удалить</a></td>
      </tr>
    <% } %>
    </table>
  </div>
  <%}%>
  <% request.getSession().removeAttribute("employees");%>

  <h2>Задание 3</h2>
  <hr/>
  <p>XML unmarshaling...</p>
  <% Datasources connectionFromXML = XMLConf.parseXMLConfiguration();
  for(int i = 0; i < connectionFromXML.datasource.size(); i++) {%>
    <ul>
    <li>source-name: <b><%=connectionFromXML.datasource.get(i).sourceName%></b></li>
    <li>connection-url: <b><%=connectionFromXML.datasource.get(i).connectionUrl%></b></li>
    <li>driver-class: <b><%=connectionFromXML.datasource.get(i).driverClass%></b></li>
    <li>user-name: <b><%=connectionFromXML.datasource.get(i).userName%></b></li>
    <li>password: <b><%=connectionFromXML.datasource.get(i).password%></b></li>
    </ul>
  <%}%>

  <%  String task2 = (String) request.getSession().getAttribute("Task2"); %>
  <% request.getSession().removeAttribute("Task2");%>
  <% if (task2 != null) {%>
  <script type="text/javascript">
      document.getElementById("task2").click();
  </script>
  <%}%>

  </body>
</html>
