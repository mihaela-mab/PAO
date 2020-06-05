<%@ page import="model.Play" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Mihaela
  Date: 02-Jun-20
  Time: 9:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<html>
    <head>
        <link href = "../css/commonStyle.css" type = "text/css" rel = "stylesheet">
        <link href = "../css/form.css" type = "text/css" rel = "stylesheet">
        <link href = "../css/removePages.css" type = "text/css" rel = "stylesheet">
        <title>Remove Play</title>
    </head>
    <body>
        <a href = "index.jsp">Go back to options page</a>
        <h1>Check the list below and choose the id of the play you want to remove</h1>
        <ul>
            <%
                List<Play> plays = (List) request.getAttribute("allPlays");
                for (Play play : plays) {%>
            <li><%= play.showDetails()%></li>

            <%}%>

        </ul>
        <form action="removePlay" method="post">
            <label>Play id <input type="text" name="chosenId"></label>
            <button type="submit">Remove</button>
    </form>
    </body>
</html>
