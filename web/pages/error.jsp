<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 23.05.2019
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isErrorPage="true" %>
<html>
<head>
    <title>Ошибка</title>
</head>
<body>
<div>
    <% exception.printStackTrace(response.getWriter()); %>
</div>
</body>
</html>
