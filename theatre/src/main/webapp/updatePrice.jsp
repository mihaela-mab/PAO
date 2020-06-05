<%@ page import="model.Play" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Mihaela
  Date: 02-Jun-20
  Time: 9:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link href = "../css/commonStyle.css" type = "text/css" rel = "stylesheet">
        <link href = "../css/form.css" type = "text/css" rel = "stylesheet">
        <link href = "../css/removePages.css" type = "text/css" rel = "stylesheet">
        <title>Update Price</title>
    </head>
    <body>
        <a href = "index.jsp">Go back to options page</a>
        <h1>Check the list below and choose the id of the play you want to update</h1>
        <ul>
            <%
                 if (request.getParameter("chosenId") != null && request.getParameter("chosenId") != ""){
                 List<Play> plays = (List) request.getAttribute("allPlays");
                    for (Play play : plays) {%>
                        <%if (play.getId() == ((Play)request.getAttribute("chosenPlay")).getId()) {%>
                            <li class = "chosenPlay"><%= play.showDetails()%></li>
                        <%}else {%>
                            <li><%= play.showDetails()%></li>
                        <%}
                        }
                 } else {
                     List<Play> plays = (List) request.getAttribute("allPlays");
                     for (Play play : plays) {%>
                         <li><%= play.showDetails()%></li>
                    <%}
                 }%>
        </ul>

        <form action="updatePrice" method="post">
            <label>Play id <input type="text" name="chosenId"></label>
            <label>New price <input type="text" name="newPrice"></label>
            <button type="submit">Update</button>
        </form>

    </body>
</html>
