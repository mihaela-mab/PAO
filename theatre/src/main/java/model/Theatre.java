package model;

import contracts.TheatreContract;
import helper.AuditCSV;
import helper.CSVRepository;
import helper.EmployeeComparator;
import repository.MainRepository;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// service class
public class Theatre implements TheatreContract {

    private String theatreName;
    private String location;
    private int numberOfHalls;

    // csv services
    private CSVRepository csvRepository = CSVRepository.getInstance();

    private static final MainRepository mainRepository = new MainRepository();

    AuditCSV auditCSV = AuditCSV.getInstance();

    ExecutorService es = Executors.newFixedThreadPool(7);

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
    private Director director = Director.getDirector();



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
    public void addEmployee() throws SQLException {
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
        String job = in.next().toLowerCase();

        Employee employee = null;

        if (job.equals("actor")) {
            employee = new Actor(lastName, firstName, birthDate, address, phoneNumber);
            this.employees.add(employee);
            System.out.println("The theatre has a new employee! Say hi to:");
            System.out.println("---------------------------------------");
            System.out.println(employee);
            System.out.println("---------------------------------------");

        }
        else
            if (job.equals("playwright")) {
                employee = new Playwright(lastName, firstName, birthDate, address, phoneNumber);
                this.employees.add(employee);
                System.out.println("The theatre has a new employee! Say hi to:");
                System.out.println("---------------------------------------");
                System.out.println(employee);
                System.out.println("---------------------------------------");
            }
        else
            if (job.equals("designer")) {
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

        mainRepository.addEmployee(employee);

    }

    @Override
    public void removeEmployee(int employeeIndex) throws SQLException {
//        try {
//            System.out.println("---------------------------------------");
//            System.out.println(this.employees.get(employeeIndex));
//            System.out.println("---------------------------------------");
//            this.employees.remove(employeeIndex);
//            System.out.println("The employee with index " + employeeIndex + " has been removed... What a loss!");
//        } catch (Exception e) {
//            System.out.println("Something went wrong. The program generated the following exception: " +
//                     e.getMessage() + ". Be more careful with the index you choose!");
//        }

        mainRepository.deleteEmployee(employeeIndex);

    }

    @Override
    public void addPlay() throws SQLException {
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

        // add the play to the specific csv
        Path path =  Paths.get("src/data/plays.csv").toAbsolutePath();
        String filePath = path.toString();
        List<Play> helperList = new ArrayList<>();
        helperList.add(play);
        try {
            csvRepository.writeCSV(helperList, filePath);
            System.out.println("fine");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //CSVRepository.getInstance().writeCSV(readPlays, filePath + "/plays.csv");

        mainRepository.addPlay(play);

    }

    @Override
    public void removePlay(int playIndex) throws SQLException {
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

        mainRepository.deletePlay(playIndex);


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

            Ticket ticket = new Ticket(price, type, newPlay.getName(), row, place);
            this.tickets.add(ticket);
            System.out.println("A new ticket has been purchased. Here are the details:");
            System.out.println("---------------------------------------");
            System.out.println(ticket);
            System.out.println("---------------------------------------");

            // add the ticket to the specific csv
            Path path =  Paths.get("src/data/tickets.csv").toAbsolutePath();
            String filePath = path.toString();
            List<Ticket> helperList = new ArrayList<>();
            helperList.add(ticket);
            try {
                csvRepository.writeCSV(helperList, filePath);

            } catch (Exception e) {
                e.printStackTrace();
            }
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
    public void listPlays() throws SQLException {
//        System.out.println("---------------------------------------");
//        int ind = 1;
//        for(Play play : plays) {
//            System.out.println(String.valueOf(ind) + ". " + play);
//            ind++;
//        }
//        System.out.println("---------------------------------------");
//

        List<Play> plays = mainRepository.getPlays();
        for (Play play : plays) {
            System.out.println(play);
        }


        //auditCSV.write("List all plays", Thread.currentThread().getName());
//        return mainRepository.getPlays();


    }

    @Override
    public void listActors() throws SQLException {
        Collections.sort(employees, new EmployeeComparator());
//        int ind = 1;
//        for(Employee employee : employees) {
//            if(employee.getJob().equals("Actor")) {
//                System.out.println(String.valueOf(ind) + ". " + employee);
//                ind++;
//            }
//        }

        List<Actor> actors = mainRepository.getActors();
        for (Actor actor : actors) {
            System.out.println(actor);
        }

    }

    @Override
    public void listPurchasedTickets() throws SQLException {
        int ind = 1;
        for(Ticket ticket : tickets) {
            System.out.println(String.valueOf(ind) + ". " + ticket);
            ind++;
        }

        List<Ticket> ticketList = mainRepository.getTickets();
        for (Ticket ticket : ticketList) {
            System.out.println(ticket);
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
                if(ticket.getPlay().equals(playName)) {
                    number++;
                }
            }
            System.out.println("Number of spectators: " + number);
        }
    }

    @Override
    public void listHallsByFloor() throws SQLException {
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

        List<Hall> hallsByFloor = mainRepository.getHalls(floor);
        for (Hall hall : hallsByFloor) {
            System.out.println(hall);
        }
    }

    @Override
    public void showTheatre() {
        System.out.println("We're glad you're interested in out theatre! For more information, don't hesitate to access our website!");
        System.out.println(this.theatreName + ", located in " + this.location + ", with a total number of halls of " + this.numberOfHalls);
    }

    @Override
    public void updatePrice() throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.println("Play id: ");
        int id = in.nextInt();
        System.out.println("New price: ");
        float price = in.nextFloat();
        mainRepository.updatePrice(id, price);

    }

    @Override
    public void addHall() {
        Scanner in = new Scanner(System.in);
        System.out.println("Name:");
        String name = in.nextLine();
        System.out.println("Floor:");
        int floor = in.nextInt();
        System.out.println("Capacity:");
        int capacity = in.nextInt();

        Hall hall = new Hall(name, floor, capacity);
        this.halls.add(hall);

        System.out.println("We're happy to announce the inauguration of the hall ");
        System.out.println("---------------------------");
        System.out.println(hall);
        System.out.println("---------------------------");

        // add the hall to the specific csv
        Path path =  Paths.get("src/data/halls.csv").toAbsolutePath();
        String filePath = path.toString();
        List<Hall> helperList = new ArrayList<>();
        helperList.add(hall);
        try {
            csvRepository.writeCSV(helperList, filePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addSpectator() {
        Scanner in = new Scanner(System.in);
        System.out.println("First name:");
        String firstName = in.nextLine();
        System.out.println("Last name:");
        String lastName = in.nextLine();
        System.out.println("Type (Child, Student, Pensioner, Full Price):");
        String type = in.nextLine();
        Play play = null;
        while (true) {
            System.out.println("Play name: ");
            String playName = in.nextLine();
            for (Play p : this.plays) {
                if (playName.equals(p.getName())) {
                    play = p;
                    break;
                }
            }

            if (play == null) {
                System.out.println("The is no play with the given name. Do you want to try again? (yes/no)");
                if (in.next().equals("no")) {
                    System.exit(0);
                }
                else {
                    continue;
                }

            }
            else {
                break;
            }
        }
        System.out.println("Chosen row: ");
        String chosenRow = in.nextLine();
        System.out.println("Chosen place:");
        int chosenPlace = in.nextInt();

        Spectator spectator = new Spectator(firstName, lastName, type, play, chosenRow, chosenPlace);

        System.out.println("There is a new spectator:");
        System.out.println("---------------------------");
        System.out.println(spectator);
        System.out.println("---------------------------");

        // add the spectator to the specific csv
        Path path =  Paths.get("src/data/spectators.csv").toAbsolutePath();
        String filePath = path.toString();
        List<Spectator> helperList = new ArrayList<>();
        helperList.add(spectator);
        try {
            csvRepository.writeCSV(helperList, filePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void csvHandler() {
        // get path for csv files
        Path path =  Paths.get("src/data").toAbsolutePath();
        String filePath = path.toString();

        // read/write data in/from a csv file

        // Class Play
        try {
            List<Play> readPlays = CSVRepository.getInstance().readCSV(filePath + "/plays.csv", Play.class);
            for (Play play : readPlays) {
                this.plays.add(play);
            }
            //System.out.println(readPlays);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Class Hall
        try {
            List<Hall> readHalls = CSVRepository.getInstance().readCSV(filePath + "/halls.csv", Hall.class);
            //System.out.println(readHalls);
            for (Hall hall : readHalls) {
                this.halls.add(hall);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }


        // Class Ticket
        try {
            List<Ticket> readTickets = CSVRepository.getInstance().readCSV(filePath + "/tickets.csv", Ticket.class);
            //System.out.println(readTickets);
            for (Ticket ticket : readTickets) {
                this.tickets.add(ticket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Class Spectator
        try {
            List<Spectator> readSpectators = CSVRepository.getInstance().readCSV(filePath + "/spectators.csv", Spectator.class);
            //System.out.println(readSpectators);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}