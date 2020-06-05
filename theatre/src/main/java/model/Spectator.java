package model;

public class Spectator {
    private String firstName;
    private String lastName;
    private String type; // Child, Student, Pensioner, Full Price
    private Ticket ticket;
    private Play play;
    private String chosenRow;
    private int chosenPlace;

    public Spectator(String firstName, String lastName, String type, Play play, String chosenRow, int chosenPlace) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
        this.ticket = new Ticket(play.getFullTicketPrice(), type, play.getName(), chosenRow, chosenPlace);
    }

    public Spectator(String firstName, String lastName, String type, String chosenRow, int chosenPlace) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
        this.ticket = new Ticket(play.getFullTicketPrice(), type, play.getName(), chosenRow, chosenPlace);
    }

    public Spectator() {

    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Play getPlay() {
        return play;
    }

    public void setPlay(Play play) {
        this.play = play;
    }

    public String getChosenRow() {
        return chosenRow;
    }

    public void setChosenRow(String chosenRow) {
        this.chosenRow = chosenRow;
    }

    public int getChosenPlace() {
        return chosenPlace;
    }

    public void setChosenPlace(int chosenPlace) {
        this.chosenPlace = chosenPlace;
    }

    @Override
    public String toString() {
        return "Spectator " + this.firstName + " " + this.lastName + " , type: " + this.type;
    }
}