package contracts;

import model.Play;

import java.sql.SQLException;
import java.util.List;

public interface TheatreContract {
    void addEmployee() throws SQLException;
    void removeEmployee(int employeeIndex) throws SQLException;
    void addPlay() throws SQLException;
    void removePlay(int playIndex) throws SQLException;
    void addTicket();
    void removeTicket(int ticketID);
    void listPlays() throws SQLException;
    void listActors() throws SQLException;
    void listPurchasedTickets() throws SQLException;
    void numberOfSpectators();
    void listHallsByFloor() throws SQLException;
    void showTheatre();
    void updatePrice() throws SQLException;
    void addHall();
    void addSpectator();
    void csvHandler();

}
