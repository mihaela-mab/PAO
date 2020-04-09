package theatre.contracts;

import theatre.model.Employee;
import theatre.model.Play;
import theatre.model.Ticket;

public interface TheatreContract {
    void addEmployee();
    void removeEmployee(int employeeIndex);
    void addPlay();
    void removePlay(int playIndex);
    void addTicket();
    void removeTicket(int ticketID);
    void listPlays();
    void listActors();
    void listPurchasedTickets();
    void numberOfSpectators();
    void listHallsByFloor();
    void showTheatre();

}
