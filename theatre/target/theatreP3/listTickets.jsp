<%@ page import="java.util.List" %>
<%@ page import="model.Ticket" %><%--
  Created by IntelliJ IDEA.
  User: Mihaela
  Date: 02-Jun-20
  Time: 9:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link href = "../css/commonStyle.css" type = "text/css" rel = "stylesheet">
        <title>Tickets</title>
    </head>
    <body>
        <a href = "index.jsp">Go back to options page</a>
        <h1>Tickets</h1>
        <ul>
            <%
                List<Ticket> tickets = (List) request.getAttribute("tickets");
                for (Ticket ticket : tickets) {
            %>
            <li><%= ticket.toString()%></li>
            <%}%>

        </ul>


    </body>
</html>
