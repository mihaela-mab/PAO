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

@WebServlet("/updatePrice")
public class UpdatePrice extends HttpServlet {

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

        RequestDispatcher rs = req.getRequestDispatcher("updatePrice.jsp");
        rs.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("chosenId"));
        float price = Float.parseFloat(req.getParameter("newPrice"));
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            mainRepository.updatePrice(id, price);
            auditCSV.write("Update the price of a play", Thread.currentThread().getName());
            req.setAttribute("allPlays", mainRepository.getPlays());
            req.setAttribute("chosenPlay", mainRepository.getGetPlayById(id));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        resp.setContentType("text/html");
        RequestDispatcher rs = req.getRequestDispatcher("updatePrice.jsp");
        rs.forward(req, resp);
    }
}
