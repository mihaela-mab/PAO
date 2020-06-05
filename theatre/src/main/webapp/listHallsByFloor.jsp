<%@ page import="java.util.List" %>
<%@ page import="model.Hall" %><%--
  Created by IntelliJ IDEA.
  User: Mihaela
  Date: 02-Jun-20
  Time: 9:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link href = "../css/commonStyle.css" type = "text/css" rel = "stylesheet">
        <link href = "../css/form.css" type = "text/css" rel = "stylesheet">
        <title>Halls by Floor</title>
    </head>
    <body>
        <a href = "index.jsp">Go back to options page</a>
        <form action="listHallsByFloor" method="post">
            <label>Introduce the floor: <input type="text" name="floorNumber"></label>
            <button type="submit">Check</button>
        </form>
        <h1 class="all">Halls By Floor</h1>
        <ul>
            <%
                if (request.getParameter("floorNumber") != null && request.getParameter("floorNumber") != "") {
                    List<Hall> halls = (List) request.getAttribute("hallsByFloor");
                    for (Hall hall : halls) {
                %>

                <li><%= hall.toString() %></li>
                    <%}
                }%>
        </ul>
    </body>
</html>
