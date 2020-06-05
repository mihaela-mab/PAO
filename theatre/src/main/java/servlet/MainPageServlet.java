package servlet;

import helper.TheatreHelper;
import model.Theatre;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/index")
public class MainPageServlet extends HttpServlet {

    Theatre theatre = new TheatreHelper().buildInitialTheatre();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("theatreName", theatre.getTheatreName());
        req.setAttribute("theatreLocation", theatre.getLocation());
        req.setAttribute("numberOfHalls", theatre.getNumberOfHalls());
        resp.setContentType("text/html");

        RequestDispatcher rs = req.getRequestDispatcher("index.jsp");
        rs.forward(req, resp);
    }
}
