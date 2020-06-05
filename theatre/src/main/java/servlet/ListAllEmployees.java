package servlet;

import repository.MainRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/listAllEmployees")
public class ListAllEmployees extends HttpServlet {

    private static final MainRepository mainRepository = new MainRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            req.setAttribute("allEmployees", mainRepository.getEmployees());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        resp.setContentType("text/html");
        RequestDispatcher rs = req.getRequestDispatcher("listAllEmployees.jsp");
        rs.forward(req, resp);

    }
}
