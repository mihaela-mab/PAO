package theatre.helper;

import theatre.contracts.TheatreHelperContract;
import theatre.model.*;

import java.util.ArrayList;
import java.util.List;

public class TheatreHelper extends TheatreHelperContract {
    @Override
    public Theatre buildInitialTheatre() {
            // employees
            Employee actor1 = new Actor("Cristin", "Tomi", "28.08.1965", "Bucharest", "0700000000");
            Employee actor2 = new Actor("Adam", "Eduard", "14.03.1976", "Bucharest", "0700000001");
            Employee actor3 = new Actor("Calin", "Natalia", "23.01.1973", "Bucharest", "0700000002");
            Employee playwright = new Playwright("Ionescu", "Andrei", "05.05.1980", "Bucharest", "0710000003");
            Employee designer = new Designer("Popa", "Mihai", "21.02.1988", "Bucharest", "0710000004", "Costumes");

            List<Employee> employees = new ArrayList<>();
            employees.add(actor1);
            employees.add(actor2);
            employees.add(actor3);
            employees.add(playwright);
            employees.add(designer);

            // plays
            Play play = new Play("Sherlock Holmes in 2020", "The Studio Hall", 150, 70, "George Georgescu");
            List<Play> plays = new ArrayList<>();
            plays.add(play);

            // tickets
            Ticket ticket1 = new Ticket(70f, "Student", play, "10A", 5);
            ticket1.setAvailability(false);
            Ticket ticket2 = new Ticket(70f, "Pensioner", play, "2A", 10);
            ticket2.setAvailability(false);
            List<Ticket> tickets = new ArrayList<>();
            tickets.add(ticket1);
            tickets.add(ticket2);

            // director
            Director director = Director.getDirector();

            // halls
            Hall hall1 = new Hall("Studio Hall", 1, 200);
            Hall hall2 = new Hall("Grand Hall", 0, 940);
            List<Hall> halls = new ArrayList<>();
            halls.add(hall1);
            halls.add(hall2);

            return new Theatre(
                    "National Theatre of Bucharest",
                    "Boulevard Nicolae Bălcescu No.2, postal code 010051, Bucharest 1",
                    7,
                    employees,
                    plays,
                    tickets,
                    halls,
                    director
            );
        }
}

