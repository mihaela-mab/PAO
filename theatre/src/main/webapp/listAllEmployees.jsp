<%@ page import="model.Employee" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Mihaela
  Date: 02-Jun-20
  Time: 4:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link href = "../css/commonStyle.css" type = "text/css" rel = "stylesheet">
        <title>Actors</title>
    </head>
    <body>
        <a href = "index.jsp">Go back to options page</a>
        <h1>Employees</h1>
        <ul>
            <%
                List<Employee> employees = (List) request.getAttribute("allEmployees");
                for (Employee employee : employees) {
            %>
            <li><%= employee.toString()%></li>

            <%}%>

        </ul>

    </body>
</html>
