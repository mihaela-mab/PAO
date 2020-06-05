package servlet;

import helper.AuditCSV;
import helper.TheatreHelper;
import model.Theatre;
import repository.MainRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/listPlays")
public class ListPlays extends HttpServlet {
    private static final MainRepository mainRepository = new MainRepository();

    private static final AuditCSV auditCSV = AuditCSV.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            req.setAttribute("plays", mainRepository.getPlays());
            auditCSV.write("List all the plays", Thread.currentThread().getName());

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        resp.setContentType("text/html");

        RequestDispatcher rs = req.getRequestDispatcher("listPlays.jsp");
        rs.forward(req, resp);
    }
}
