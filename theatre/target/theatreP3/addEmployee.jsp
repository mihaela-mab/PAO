<%@ page import="model.Employee" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Mihaela
  Date: 02-Jun-20
  Time: 9:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link href = "../css/commonStyle.css" type = "text/css" rel = "stylesheet">
        <link href = "../css/form.css" type = "text/css" rel = "stylesheet">
        <title>Add Employee</title>
    </head>
    <body>
        <a href = "index.jsp">Go back to options page</a>
        <form action = "addEmployee" method = "post">
            <label>First name <input type = "text" name = "firstName"></label>
            <label>Last name <input type="text" name="lastName"></label>
            <label>Birth date <input type="text" name="birthDate"></label>
            <label>Address <input type="text" name="address"></label>
            <label>Phone number <input type="text" name="phoneNumber"></label>
            <label>Job <input type="text" name = "job"></label>
            <label id = "designerType">Designer type <input type="text" name = "designerType"></label>

            <button type="submit">Add</button>
        </form>

    </body>
</html>
