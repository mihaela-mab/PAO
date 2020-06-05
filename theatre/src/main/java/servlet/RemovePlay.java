package servlet;

import helper.AuditCSV;
import repository.MainRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/removePlay")
public class RemovePlay extends HttpServlet {

    private static final MainRepository mainRepository = new MainRepository();
    private static final AuditCSV auditCSV = AuditCSV.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            req.setAttribute("allPlays", mainRepository.getPlays());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        resp.setContentType("text/html");

        RequestDispatcher rs = req.getRequestDispatcher("removePlay.jsp");
        rs.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("chosenId"));
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            mainRepository.deletePlay(id);
            req.setAttribute("allPlays", mainRepository.getPlays());
            auditCSV.write("Remove a play", Thread.currentThread().getName());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        resp.setContentType("text/html");
        RequestDispatcher rs = req.getRequestDispatcher("removePlay.jsp");
        rs.forward(req, resp);
    }
}
