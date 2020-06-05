package servlet;

import helper.AuditCSV;
import model.Play;
import repository.MainRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/addPlay")
public class AddPlay extends HttpServlet {

    private static final MainRepository mainRepository = new MainRepository();

    private static final AuditCSV auditCSV = AuditCSV.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rs = req.getRequestDispatcher("addPlay.jsp");
        rs.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("playName");
        String playwright = req.getParameter("playwrightName");
        String hall = req.getParameter("hall");
        int numberOfTickets = Integer.parseInt(req.getParameter("numberOfTickets"));
        float fullTicketPrice = Float.parseFloat(req.getParameter("fullTicketPrice"));
        Play play = new Play(name, hall, numberOfTickets, fullTicketPrice, playwright);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            mainRepository.addPlay(play);
            auditCSV.write("Add a play", Thread.currentThread().getName());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        resp.setContentType("text/html");
        RequestDispatcher rs = req.getRequestDispatcher("addPlay.jsp");
        rs.forward(req, resp);
    }
}
