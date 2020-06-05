package helper;

import contracts.TheatreHelperContract;
import model.*;

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
            Play otherPlay = new Play("The Dinner Game", "The Grand Hall", 400, 100, "Francis Veber");
            List<Play> plays = new ArrayList<>();
            plays.add(play);
            plays.add(otherPlay);

            // tickets
            Ticket ticket1 = new Ticket(70f, "Student", play.getName(), "10A", 5);
            ticket1.setAvailability(false);
            Ticket ticket2 = new Ticket(70f, "Pensioner", play.getName(), "2A", 10);
            ticket2.setAvailability(false);
//            System.out.println(ticket1.getId());
//            System.out.println(ticket2.getId());
            List<Ticket> tickets = new ArrayList<>();
            tickets.add(ticket1);
            tickets.add(ticket2);

            // director
            Director director = Director.getDirector();

            // halls
            List<Play> hall1Plays = new ArrayList<>();
            List<Play> hall2Plays = new ArrayList<>();

            for (Play p : plays) {
                    if (p.getLocation().equals("The Studio Hall")) {
                            hall1Plays.add(p);
                    }
                    else if (p.getLocation().equals("The Grand Hall")) {
                            hall2Plays.add(p);
                    }
            }
            Hall hall1 = new Hall("The Studio Hall", 1, 200);
            hall1.setPlays(hall1Plays);
            Hall hall2 = new Hall("The Grand Hall", 0, 940);
            hall2.setPlays(hall2Plays);
            List<Hall> halls = new ArrayList<>();
            halls.add(hall1);
            halls.add(hall2);


            return new Theatre(
                    "National Theatre of Bucharest",
                    "Boulevard Nicolae Balcescu No.2, postal code 010051, Bucharest 1",
                    7,
                    employees,
                    plays,
                    tickets,
                    halls,
                    director
            );
        }
}

