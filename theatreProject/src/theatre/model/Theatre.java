package theatre.model;

import theatre.contracts.TheatreContract;
import theatre.helper.EmployeeComparator;

import java.util.*;

public class Theatre implements TheatreContract {

    private String theatreName;
    private String location;
    private int numberOfHalls;

    private List<Employee> employees = new ArrayList<>();
    private List<Play> plays = new ArrayList<>();
    private List<Ticket> tickets = new ArrayList<>();
    private List<Hall> halls = new ArrayList<>();
    private Map<Play, List<Spectator>> spectators = new HashMap<>();
    public Theatre(String theatreName, String location, int numberOfHalls) {
        this.theatreName = theatreName;
        this.location = location;
        this.numberOfHalls = numberOfHalls;
    }
    private Director director;

    public Theatre(String theatreName, String location, int numberOfHalls, List<Employee> employees, List<Play> plays, List<Ticket> tickets, List<Hall> halls, Director director) {
        this(theatreName, location, numberOfHalls);
        this.employees = employees;
        this.plays = plays;
        this.tickets = tickets;
        this.halls = halls;
        this.director = director;
    }

    public Theatre(String theatreName, String location, int numberOfHalls, List<Employee> employees, List<Play> plays, List<Ticket> tickets, List<Hall> halls, Map<Play, List<Spectator>> spectators) {
        this(theatreName, location, numberOfHalls);
        this.employees = employees;
        this.plays = plays;
        this.tickets = tickets;
        this.halls = halls;
        this.spectators = spectators;
    }

    public String getTheatreName() {
        return theatreName;
    }
    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public int getNumberOfHalls() {
        return numberOfHalls;
    }
    public void setNumberOfHalls(int numberOfHalls) {
        this.numberOfHalls = numberOfHalls;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Play> getPlays() {
        return plays;
    }
    public void setPlays(List<Play> plays) {
        this.plays = plays;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }
    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<Hall> getHalls() {
        return halls;
    }

    public void setHalls(List<Hall> halls) {
        this.halls = halls;
    }

    public Map<Play, List<Spectator>> getSpectators() {
        return spectators;
    }

    public void setSpectators(Map<Play, List<Spectator>> spectators) {
        this.spectators = spectators;
    }

    public Director getDirector() {
        return director;
    }

    @Override
    public void addEmployee() {
        Scanner in = new Scanner((System.in));
        System.out.println("First name: ");
        String firstName = in.nextLine();
        System.out.println("Last name: ");
        String lastName = in.nextLine();
        System.out.println("Birth date: ");
        String birthDate = in.nextLine();
        System.out.println("Address: ");
        String address = in.nextLine();
        System.out.println("Phone number: ");
        String phoneNumber = in.next();
        System.out.println("Job: ");
        String job = in.next();

        Employee employee = null;

        if (job.equals("Actor")) {
            employee = new Actor(lastName, firstName, birthDate, address, phoneNumber);
            this.employees.add(employee);
            System.out.println("The theatre has a new employee! Say hi to:");
            System.out.println("---------------------------------------");
            System.out.println(employee);
            System.out.println("---------------------------------------");
        }
        else
            if (job.equals("Playwright")) {
                employee = new Playwright(lastName, firstName, birthDate, address, phoneNumber);
                this.employees.add(employee);
                System.out.println("The theatre has a new employee! Say hi to:");
                System.out.println("---------------------------------------");
                System.out.println(employee);
                System.out.println("---------------------------------------");
            }
        else
            if (job.equals("Designer")) {
                System.out.println("Type: ");
                String type = in.next();
                employee = new Designer(lastName, firstName, birthDate, address, phoneNumber, type);
                this.employees.add(employee);
                System.out.println("The theatre has a new employee! Say hi to:");
                System.out.println("---------------------------------------");
                System.out.println(employee);
                System.out.println("---------------------------------------");
            }
            else {
                System.out.println("This job does not exist. Please choose between the following: Actor, Playwright, Designer");
            }

    }

    @Override
    public void removeEmployee(int employeeIndex) {
        try {
            System.out.println("---------------------------------------");
            System.out.println(this.employees.get(employeeIndex));
            System.out.println("---------------------------------------");
            this.employees.remove(employeeIndex);
            System.out.println("The employee with index " + employeeIndex + " has been removed... What a loss!");
        } catch (Exception e) {
            System.out.println("Something went wrong. The program generated the following exception: " +
                     e.getMessage() + ". Be more careful with the index you choose!");
        }

    }

    @Override
    public void addPlay() {
        Scanner in = new Scanner((System.in));
        System.out.println("Please provide the following information about the play you want to add: ");
        System.out.println("Name: ");
        String name = in.nextLine();
        System.out.println("Playwright: ");
        String playwright = in.nextLine();
        System.out.println("Hall: ");
        String location = in.nextLine();
        System.out.println("Number of tickets: ");
        int numberOfTickets = in.nextInt();
        System.out.println("Full ticket price: ");
        float fullTicketPrice = in.nextFloat();
        Play play = new Play(name, location, numberOfTickets, fullTicketPrice, playwright);
        this.plays.add(play);
        System.out.println("The theatre has a new play! Let's see what we've got:");
        System.out.println("---------------------------------------");
        System.out.println(play);
        System.out.println("---------------------------------------");

    }

    @Override
    public void removePlay(int playIndex) {
        try {
            System.out.println("---------------------------------------");
            System.out.println(this.plays.get(playIndex));
            System.out.println("---------------------------------------");
            this.plays.remove(playIndex);
            System.out.println("The play with index " + playIndex + " has been removed. We are sorry for any inconvenience!");
        } catch (Exception e) {
            System.out.println("Something went wrong. The program generated the following exception: " +
                    e.getMessage() + ". Be more careful with the index you choose!");
        }


    }

    @Override
    public void addTicket() {
        Scanner in = new Scanner(System.in);
        System.out.println("Play name: ");
        String playName = in.nextLine();
        boolean exists = false;
        Play newPlay = null;
        for (Play p : plays) {
            if (playName.equals(p.getName())) {
                exists = true;
                newPlay = p;
                break;
            }
        }
        if (!exists) {
            System.out.println("The play does not exist!");
            System.out.println("Choose to exit? (yes / no)");
            if (in.next().equals("yes")) {
                System.exit(0);
            }
            else {
                addTicket();
            }
        }
        else {
            System.out.println("Price: ");
            float price = in.nextFloat();
            System.out.println("Type: ");
            String type = in.next();
            System.out.println("Row: ");
            String row = in.next();
            System.out.println("Place: ");
            int place = in.nextInt();

            Ticket ticket = new Ticket(price, type, newPlay, row, place);
            this.tickets.add(ticket);
            System.out.println("A new ticket has been purchased. Here are the details:");
            System.out.println("---------------------------------------");
            System.out.println(ticket);
            System.out.println("---------------------------------------");
        }

    }

    @Override
    public void removeTicket(int ticketIndex) {
        try {
            System.out.println("You remove the following ticket: ");
            System.out.println("---------------------------------------");
            System.out.println(this.tickets.get(ticketIndex));
            System.out.println("---------------------------------------");
            this.tickets.remove(ticketIndex);
            System.out.println("Ticket with index " + ticketIndex + " is now available.");
        } catch (Exception e) {
            System.out.println("Something went wrong. The program generated the following exception: " +
                    e.getMessage() + ". Be more careful with the index you choose!");
        }


    }

    @Override
    public void listPlays() {
        System.out.println("---------------------------------------");
        int ind = 1;
        for(Play play : plays) {
            System.out.println(String.valueOf(ind) + ". " + play);
            ind++;
        }
        System.out.println("---------------------------------------");
    }

    @Override
    public void listActors() {
        Collections.sort(employees, new EmployeeComparator());
        int ind = 1;
        for(Employee employee : employees) {
            if(employee.getJob().equals("Actor")) {
                System.out.println(String.valueOf(ind) + ". " + employee);
                ind++;
            }
        }

    }

    @Override
    public void listPurchasedTickets() {
        int ind = 1;
        for(Ticket ticket : tickets) {
            System.out.println(String.valueOf(ind) + ". " + ticket);
            ind++;
        }

    }

    @Override
    public void numberOfSpectators() {
        Scanner in = new Scanner(System.in);
        System.out.println("You chose to list the number of spectators of a play.");
        System.out.println("Enter the name of the play: ");
        String playName = in.nextLine();
        Play play = null;
        for (Play p : this.plays) {
            if (p.getName().equals(playName)) {
                play = p;
                break;
            }
        }
        if (play == null) {
            System.out.println("The play does not exist!");
        }
        else {
            int number = 0;
            for (Ticket ticket : tickets) {
                if(ticket.getPlay().getName().equals(playName)) {
                    number++;
                }
            }
            System.out.println("Number of spectators: " + number);
        }
    }

    @Override
    public void listHallsByFloor() {
        System.out.println("Please introduce the floor: ");
        Scanner in = new Scanner(System.in);
        int floor = in.nextInt();
        int i = 1;
        for (Hall hall : halls) {
            if(hall.getFloor() == floor) {
                System.out.println(i + ". " + hall);
                i++;
            }
        }
        if (i == 1) {
            System.out.println("There are no halls at floor " + floor);
        }
    }

    @Override
    public void showTheatre() {
        System.out.println("We're glad you're interested in out theatre! For more information, don't hesitate to access our website!");
        System.out.println(this.theatreName + ", located in " + this.location + ", with a total number of halls of " + this.numberOfHalls);
    }


}