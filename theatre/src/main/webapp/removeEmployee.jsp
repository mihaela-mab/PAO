<%@ page import="model.Employee" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Mihaela
  Date: 02-Jun-20
  Time: 9:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link href = "../css/commonStyle.css" type = "text/css" rel = "stylesheet">
        <link href = "../css/form.css" type = "text/css" rel = "stylesheet">
        <link href = "../css/removePages.css" type = "text/css" rel = "stylesheet">
        <title>Remove Employee</title>
    </head>
    <body>
        <a href = "index.jsp">Go back to options page</a>
        <h1>Check the list below and choose the id of the employee you want to fire</h1>
        <ul>
                <%
                List<Employee> employees = (List) request.getAttribute("allEmployees");
                for (Employee employee : employees) {%>
                    <li><%= employee.showDetails()%></li>

                <%}%>

        </ul>
        <form action="removeEmployee" method="post">
            <label>Employee id <input type="text" name="chosenId"></label>
            <button type="submit">Remove</button>
        </form>
    </body>
</html>
