package repository;

import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MainRepository {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/theatre";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "12345678";

    // queries
    private static final String LIST_ALL_ACTORS = "SELECT * FROM employee WHERE job = 'actor'";
    private static final String ADD_EMPLOYEE = "INSERT INTO employee(last_name, first_name, birth_date, " +
            "address, phone_number, job) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String DELETE_EMPLOYEE = "DELETE FROM employee WHERE employee_id = ?;";
    private static final String ADD_PLAY = "INSERT INTO play(name, hall_id, number_of_tickets, total_ticket_price) VALUES (?, ?, ?, ?);";
    private static final String DELETE_PLAY = "DELETE FROM play WHERE play_id = ?;";
    private static final String LIST_ALL_PLAYS = "SELECT p.play_id, p.name, hall.name, p.number_of_tickets, concat(e.first_name, ' ', e.last_name), playwright_name," +
            " p.total_ticket_price FROM play p JOIN hall USING (hall_id) LEFT JOIN writes_play w ON (p.play_id = w.play_id) LEFT JOIN employee e ON (e.employee_id = w.playwright_id);";
    private static final String ADD_TICKET = "INSERT INTO ticket(price, type, play_id, row, place, availability)" +
            "VALUES (?, ?, ?, ?, ?, ?);";
    private static final String REMOVE_TICKET = "DELETE FROM ticket WHERE ticket_id = ?;";
    private static final  String LIST_TICKETS = "SELECT price, type, play.name, hall.name, ticket.row, place\n" +
            "FROM ticket JOIN play USING (play_id) JOIN hall USING (hall_id);";
    private static final String ADD_HALL = "INSERT INTO hall(name, floor, capacity)" +
            "VALUES (?, ?, ?);";
    private static final String UPDATE_PRICE = "UPDATE play SET total_ticket_price = ? WHERE play_id = ?;";
    private static final String VERIFY_EMPLOYEE_DELETION = "SELECT employee_id FROM employee \n" +
            "WHERE employee_id NOT IN (SELECT actor_id FROM plays_in) AND \n" +
            "employee_id NOT IN (SELECT playwright_id FROM writes_play) AND\n" +
            "employee_id NOT IN (SELECT designer_id FROM designes_play);";
    private static final String GET_HALL = "SELECT name FROM hall;";
    private static final String GET_HALL_BY_NAME = "SELECT hall_id FROM hall WHERE  lower (name) = ?;";
    private static final String VERIFY_PLAY_DELETION = "SELECT play_id FROM play WHERE play_id NOT IN " +
            "(SELECT play_id FROM plays_in) AND play_id NOT IN (SELECT play_id FROM writes_play) AND play_id NOT IN " +
            "(SELECT play_id FROM designes_play);";
    private static final String GET_HALLS_BY_FLOOR = "SELECT name, floor, capacity FROM hall WHERE floor = ?;";
    private static final String REMOVE_EMPLOYEE_BY_NAME = "DELETE FROM employee WHERE lower(concat(first_name, ' ', last_name)) = ?";

    private static final String VERIFY_EMPLOYEE_DELETION_BY_NAME = "SELECT lower(concat(first_name, ' ', last_name)) FROM employee \n" +
            "WHERE employee_id NOT IN (SELECT actor_id FROM plays_in) AND \n" +
            "employee_id NOT IN (SELECT playwright_id FROM writes_play) AND\n" +
            "employee_id NOT IN (SELECT designer_id FROM designes_play);";
    private static final String LIST_ALL_EMPLOYEES = "SELECT employee_id, last_name, first_name, birth_date, address, phone_number, job FROM employee;";
    private static final String GET_PLAY_BY_ID = "SELECT p.play_id, p.name, hall.name, p.number_of_tickets, concat(e.first_name, ' ', e.last_name), playwright_name, \n" +
            "p.total_ticket_price FROM play p JOIN hall USING (hall_id) LEFT JOIN writes_play w ON (p.play_id = w.play_id) LEFT JOIN employee e ON (e.employee_id = w.playwright_id)\n" +
            "WHERE p.play_id = ?;";


    public static Connection getDbConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
    }

    public boolean addEmployee(Employee employee) throws SQLException {
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(ADD_EMPLOYEE);
        preparedStatement.setString(1, employee.getLastName());
        preparedStatement.setString(2, employee.getFirstName());
        preparedStatement.setString(3, employee.getBirthDate());
        preparedStatement.setString(4, employee.getAddress());
        preparedStatement.setString(5, employee.getPhoneNumber());
        preparedStatement.setString(6, employee.getJob());

        return preparedStatement.executeUpdate() > 0;
    }

    public boolean deleteEmployee(int id) throws SQLException {
        PreparedStatement verify = getDbConnection().prepareStatement(VERIFY_EMPLOYEE_DELETION);

        ResultSet result = verify.executeQuery();
        List<Integer> possible = new ArrayList<>();
        while (result.next()) {
            possible.add(result.getInt(1));
        }
        if (possible.contains(id)) {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(DELETE_EMPLOYEE);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        }
        else {
            System.out.println("You cannot fire an employee that's implied in a play!");
        }

        return false;

    }

    public List<Actor> getActors() throws SQLException {
        List<Actor> actors = new ArrayList<>();
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(LIST_ALL_ACTORS);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Actor actor = new Actor(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                    resultSet.getString(5), resultSet.getString(6));
            actors.add(actor);
        }

        return actors;

    }

    public List<Play> getPlays() throws SQLException {
        List<Play> plays = new ArrayList<>();
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(LIST_ALL_PLAYS);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Play play = null;
            if (resultSet.getString(5) == null) {
                play = new Play(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getInt(4), resultSet.getString(6), resultSet.getFloat(7));
            }
            else {
                play = new Play(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getInt(4), resultSet.getString(5), resultSet.getFloat(7));
            }
            plays.add(play);
        }

        return plays;
    }

    public boolean addPlay(Play play) throws SQLException {
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(ADD_PLAY);
        PreparedStatement hall = getDbConnection().prepareStatement(GET_HALL_BY_NAME);

        hall.setString(1, play.getLocation().toLowerCase());
        ResultSet rs = hall.executeQuery();
        ResultSet halls = getDbConnection().prepareStatement(GET_HALL).executeQuery();
        List<String> possible = new ArrayList<>();
        while (halls.next()) {
            possible.add(halls.getString(1));
            System.out.println(halls.getString(1));
        }

        if(possible.contains(play.getLocation())) {
            preparedStatement.setString(1, play.getName());
            if (rs.next()) {
                preparedStatement.setInt(2, rs.getInt(1));
                preparedStatement.setInt(3, play.getNumberOfTickets());
                preparedStatement.setFloat(4, play.getFullTicketPrice());
                return preparedStatement.executeUpdate() > 0;
            }

        }
        else {
            System.out.println("There is no hall named " + play.getName() + "!");
        }

    return false;
    }

    public boolean deletePlay(int id) throws SQLException {
        PreparedStatement verify = getDbConnection().prepareStatement(VERIFY_PLAY_DELETION);

        ResultSet result = verify.executeQuery();
        List<Integer> possible = new ArrayList<>();
        while (result.next()) {
            possible.add(result.getInt(1));
        }
        if (possible.contains(id)) {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(DELETE_PLAY);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        }
        else {
            System.out.println("You cannot remove a play that's currently running!");
        }

        return false;
    }

    public boolean updatePrice(int id, float price) throws SQLException {
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(UPDATE_PRICE);
        preparedStatement.setFloat(1, price);
        preparedStatement.setInt(2, id);

        return preparedStatement.executeUpdate() > 0;
    }

    public List<Hall> getHalls(int floor) throws SQLException {
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(GET_HALLS_BY_FLOOR);
        preparedStatement.setInt(1, floor);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Hall> halls = new ArrayList<>();

        while (resultSet.next()) {
            Hall hall = new Hall(resultSet.getString(1), resultSet.getInt(2), resultSet.getInt(3));
            halls.add(hall);
        }

        return halls;
    }

    public List<Ticket> getTickets() throws SQLException {
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(LIST_TICKETS);
        List<Ticket> tickets = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Ticket ticket = new Ticket(resultSet.getFloat(1), resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6));
            tickets.add(ticket);
        }

        return tickets;
    }

    public boolean removeEmployeeByName(String firstName, String lastName) throws SQLException {
        String name = firstName + ' ' + lastName;

        PreparedStatement verify = getDbConnection().prepareStatement(VERIFY_EMPLOYEE_DELETION_BY_NAME);

        ResultSet result = verify.executeQuery();
        List<String> possible = new ArrayList<>();
        while (result.next()) {
            possible.add(result.getString(1));
        }
        if (possible.contains(name)) {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(REMOVE_EMPLOYEE_BY_NAME);
            preparedStatement.setString(1, name);
            return preparedStatement.executeUpdate() > 0;
        }
        else {
            System.out.println("You cannot fire an employee that's implied in a play!");
        }

        return false;
    }

    public List<Employee> getEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(LIST_ALL_EMPLOYEES);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Employee employee = new Employee(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
                    resultSet.getString(6), resultSet.getString(7));
            employees.add(employee);
        }

        return employees;

    }

    public Play getGetPlayById(int id) throws SQLException {
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(GET_PLAY_BY_ID);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Play play = null;
        while (resultSet.next()) {
            if (resultSet.getString(5) != null) {
                play = new Play(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getInt(4), resultSet.getString(5), resultSet.getFloat(7));

            }
            else {
                play = new Play(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getInt(4), resultSet.getString(6), resultSet.getFloat(7));
            }

        }
        return play;
    }


}
