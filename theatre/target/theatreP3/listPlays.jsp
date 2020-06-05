<%@ page import="java.util.List" %>
<%@ page import="model.Play" %><%--
  Created by IntelliJ IDEA.
  User: Mihaela
  Date: 02-Jun-20
  Time: 9:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link href = "../css/commonStyle.css" type = "text/css" rel = "stylesheet">
        <title>Plays</title>
    </head>
    <body>
        <a href = "index.jsp">Go back to options page</a>
        <h1>Plays</h1>
        <ul>
            <%
                List<Play> plays = (List) request.getAttribute("plays");
                for (Play play : plays) {
            %>
            <li><%= play.toString()%></li>
            <%}%>
        </ul>

    </body>
</html>
