/*
    Tema abordata implementeaza structura unui teatru.
    Clase:
        - Angajat (clasa de baza) -> Actor, Scenarist, Designer
        - Piesa de teatru
        - Bilet
        - Director (clasa singleton)
        - Clasa de servicii: Theatre
        - Spectator
        - Sala

    Actiuni (interogari):
        1. Adaugarea unui angajat
        2. Stergerea unui angajat
        3. Adaugarea unei piese de teatru
        4. Stergerea unei piese de teatru
        5. Adaugarea unui bilet
        6. Stergerea unui bilet
        7. Afisarea tuturor pieselor de teatru
        8. Afisarea tuturor actorilor
        9. Afisarea biletelor cumparate
        10. Afisarea numarului de spectatori ai unei piese
        11. Afisarea tuturor salilor de la un anumit etaj.
        12. Afisarea informatiilor despre teatru.
        0. Exit
*/

package theatre;
import theatre.helper.TheatreHelper;
import theatre.model.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Theatre theatre = new TheatreHelper().buildInitialTheatre();

        Scanner in = new Scanner(System.in);
        System.out.println("----- Welcome to the " + theatre.getTheatreName() + "! We were waiting for your visit. Enjoy! -----");
        System.out.println("\n");
        System.out.println("You have a list of options below. Don't hesitate to try as many as you want. :)");
        System.out.println("\n");
        System.out.println("1. Add an employee.");
        System.out.println("2. Delete an employee.");
        System.out.println("3. Add a play.");
        System.out.println("4. Remove a play.");
        System.out.println("5. Add a ticket.");
        System.out.println("6. Remove a ticket.");
        System.out.println("7. List all the plays.");
        System.out.println("8. List all the actors.");
        System.out.println("9. List all the purchased tickets.");
        System.out.println("10. List the number of spectators of a play.");
        System.out.println("11. List halls by floor number.");
        System.out.println("12. Show details about the theatre.");
        System.out.println("0. Exit.");

        // actions
        while(true) {
            System.out.println("Choose your option: ");
            String option = in.next();
            switch (option) {
                case "1":
                    System.out.println("You chose to add an employee.");
                    theatre.addEmployee();
                    break;

                case "2":
                    System.out.println("You chose to remove an employee.");
                    System.out.println("Insert the index of the employee you want to fire: ");
                    int index = in.nextInt();
                    theatre.removeEmployee(index);
                    break;
                case "3":
                    System.out.println("You chose to add a play.");
                    theatre.addPlay();
                    break;

                case "4":
                    System.out.println("You chose to remove a play. Insert the index of the play you want to remove: ");
                    int playIndex = in.nextInt();
                    theatre.removePlay(playIndex);
                    break;

                case "5":
                    System.out.println("You chose to add a ticket. Please provide some information about it.");
                    theatre.addTicket();
                    break;

                case "6":
                    System.out.println("You chose to remove a ticket. Insert the index of the ticket you want to remove.");
                    System.out.println("Index: ");
                    int ticketIndex = in.nextInt();
                    theatre.removeTicket(ticketIndex);
                    break;

                case "7":
                    System.out.println("You chose to list all the plays.");
                    theatre.listPlays();
                    break;

                case "8":
                    System.out.println("You chose to list all the actors.");
                    theatre.listActors();
                    break;

                case "9":
                    System.out.println("You chose to list all the purchased tickets.");
                    theatre.listPurchasedTickets();
                    break;

                case "10":
                    theatre.numberOfSpectators();
                    break;

                case "11":
                    System.out.println("You chose to list the halls by floor.");
                    theatre.listHallsByFloor();
                    break;

                case "12":
                    System.out.println("You chose to show information about the theatre.");
                    theatre.showTheatre();
                    break;

                case "0":
                    System.out.println("You chose to exit.");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Your option is incorrect! Try one of the above!");
            }

        }

    }

}
