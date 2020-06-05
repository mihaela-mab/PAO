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

@WebServlet("/listHallsByFloor")
public class ListHallsByFloor extends HttpServlet {

    private static final MainRepository mainRepository = new MainRepository();

    private static final AuditCSV auditCSV = AuditCSV.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rs = req.getRequestDispatcher("listHallsByFloor.jsp");
        rs.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int floor = Integer.parseInt(req.getParameter("floorNumber"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            req.setAttribute("hallsByFloor", mainRepository.getHalls(floor));
            auditCSV.write("List halls by floor number", Thread.currentThread().getName());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        resp.setContentType("text/html");
        RequestDispatcher rs = req.getRequestDispatcher("listHallsByFloor.jsp");
        rs.forward(req, resp);
    }
}
