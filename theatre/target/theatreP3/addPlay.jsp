<%--
  Created by IntelliJ IDEA.
  User: Mihaela
  Date: 02-Jun-20
  Time: 9:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link href = "../css/commonStyle.css" type = "text/css" rel = "stylesheet">
        <link href = "../css/form.css" type = "text/css" rel = "stylesheet">
        <title>Add Play</title>
    </head>
    <body>
        <a href = "index.jsp">Go back to options page</a>
        <a href="listPlays">Check all the available plays</a>
        <form action = "addPlay" method = "post">
            <label>Play name <input type = "text" name = "playName"></label>
            <label>Playwright <input type="text" name="playwrightName"></label>
            <label>Hall <input type="text" name="hall"></label>
            <label>Number of tickets <input type="text" name="numberOfTickets"></label>
            <label>Full ticket price <input type="text" name = "fullTicketPrice"></label>

            <button type="submit">Add</button>
        </form>

    </body>
</html>
