package servlet;

import helper.AuditCSV;
import model.Actor;
import model.Designer;
import model.Employee;
import model.Playwright;
import repository.MainRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/addEmployee")
public class AddEmployee extends HttpServlet {

    private static final MainRepository mainRepository = new MainRepository();

    private static final AuditCSV auditCSV = AuditCSV.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rs = req.getRequestDispatcher("addEmployee.jsp");
        rs.forward(req, resp);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String birthDate = req.getParameter("birthDate");
        String address = req.getParameter("address");
        String phoneNumber = req.getParameter("phoneNumber");
        String job = req.getParameter("job");
        String type = req.getParameter("designerType");

        Employee employee = null;

        if (job.equalsIgnoreCase("actor")) {
            employee = new Actor(lastName, firstName, birthDate, address, phoneNumber);
        }
        else {
            if (job.equalsIgnoreCase("playwright")) {
                employee = new Playwright(lastName, firstName, birthDate, address, phoneNumber);
            }
            else {
                if (job.equalsIgnoreCase("designer")) {
                    employee = new Designer(lastName, firstName, birthDate, address, phoneNumber, type);
                }
            }
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            mainRepository.addEmployee(employee);
            auditCSV.write("Add en employee", Thread.currentThread().getName());
            req.setAttribute("allEmployees", mainRepository.getEmployees());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        resp.setContentType("text/html");
        RequestDispatcher rs = req.getRequestDispatcher("listAllEmployees.jsp");
        rs.forward(req, resp);
    }


}
