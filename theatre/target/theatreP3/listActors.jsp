<%@ page import="model.Actor" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Mihaela
  Date: 02-Jun-20
  Time: 9:33 AM
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
        <h1>Actors</h1>
        <ul>
            <%
                List<Actor> actors = (List) request.getAttribute("actors");
                for (Actor actor : actors) {
            %>
            <li><%= actor.toString()%></li>

            <%}%>

        </ul>

    </body>
</html>
