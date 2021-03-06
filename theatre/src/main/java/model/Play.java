package model;

public class Play {

    private String name;
    private String location;
    private int numberOfTickets;
    private float fullTicketPrice;
    private String  playwright;

    private int id;
    private int hall_id;
//    private List<Actor> actors = new ArrayList<>();


    public Play(String name, String location, int numberOfTickets, float fullTicketPrice, String playwright) {
        this.name = name;
        this.location = location;
        this.numberOfTickets = numberOfTickets;
        this.fullTicketPrice = fullTicketPrice;
//        this.actors = actors;
        this.playwright = playwright;
    }

    public Play(int id, String name, String location, int numberOfTickets, String playwright, float fullTicketPrice) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.numberOfTickets = numberOfTickets;
        this.playwright = playwright;
        this.fullTicketPrice = fullTicketPrice;
    }

    public Play() {}

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }
    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public float getFullTicketPrice() {
        return fullTicketPrice;
    }
    public void setFullTicketPrice(float fullTicketPrice) {
        this.fullTicketPrice = fullTicketPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //    public List<Actor> getActors() {
//        return actors;
//    }
//    public void setActors(List<Actor> actors) {
//        this.actors = actors;
//    }

    public String getPlaywright() {
        return playwright;
    }
    public void setPlaywright(String playwright) {
        this.playwright = playwright;
    }

    @Override
    public String toString() {
        return this.name + ", hall " + this.location + ", written / adapted by " + (this.playwright != null ? this.playwright : "Unknown")  + ", with a total number of tickets of "
                + this.numberOfTickets + " and a full price of " + this.fullTicketPrice;
    }

    public String showDetails() {
        return this.id + ". " + this.name + ", hall " + this.location + ", written / adapted by " + (this.playwright != null ? this.playwright : "Unknown")  + ", with a total number of tickets of "
                + this.numberOfTickets + " and a full price of " + this.fullTicketPrice;
    }

}
