<%@ page import="model.Theatre" %>
<%@ page import="helper.TheatreHelper" %>
<html lang="ro">
    <head>
        <link href = "../css/commonStyle.css" type = "text/css" rel = "stylesheet">
        <link href = "../css/index.css" type = "text/css" rel = "stylesheet">
        <title>Theatre</title>
    </head>

    <body>
        <%Theatre theatre = new TheatreHelper().buildInitialTheatre();%>
        <h1>Welcome to the <%=theatre.getTheatreName()%> !</h1>
        <h2>Options</h2>
        <ul>
            <li><a href="listPlays">List all plays</a></li>
            <li><a href = "listActors">List all actors</a></li>
            <li><a href = "listTickets">List all tickets</a></li>
            <li><a href = "listHallsByFloor">List halls by floor number</a></li>
            <li><a href = "addEmployee">Add an employee</a></li>
            <li><a href = "addPlay">Add a play</a></li>
            <li><a href = "removeEmployee">Remove an employee</a></li>
            <li><a href = "removePlay">Remove a play</a></li>
            <li><a href = "updatePrice">Update the price of a play</a></li>
        </ul>
    </body>
    <hr>
    <footer>
        <p><%=theatre.getLocation()%></p>
    </footer>

</html>
