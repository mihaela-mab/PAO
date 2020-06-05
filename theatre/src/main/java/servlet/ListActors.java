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

@WebServlet("/listActors")
public class ListActors extends HttpServlet {

    private static final MainRepository mainRepository = new MainRepository();

    private static final AuditCSV auditCSV = AuditCSV.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            req.setAttribute("actors", mainRepository.getActors());
            auditCSV.write("List all the actors", Thread.currentThread().getName());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        resp.setContentType("text/html");
        RequestDispatcher rs = req.getRequestDispatcher("listActors.jsp");
        rs.forward(req, resp);
    }
}
