package model;

public class Ticket {
    private int id;
    private float price;
    private String type; // full price ticket, Student, Pensioner
    private String play;
    private String row; // 1A, 15D...
    private int place;
    private boolean availability;
    private static int number = 0; // total number of tickets
    private String location;

    public Ticket(float price, String type, String play, String row, int place) {
        this.type = type;
        this.play = play;
        this.row = row;
        this.place = place;
        this.availability = true;

        if (type.equals("Child") || type.equals("Student")) {
            this.price = price - price * 0.25f;
        }
        else
            if (type.equals("Pensioner")) {
                this.price = price - price * 0.35f;
            }
            else {
                this.price = price;
            }
    }

    public Ticket(float price, String type, String play, String location, String row, int place) {
        this.type = type;
        this.play = play;
        this.row = row;
        this.place = place;
        this.availability = true;
        this.location = location;

        if (type.equals("Child") || type.equals("Student")) {
            this.price = price - price * 0.25f;
        }
        else
        if (type.equals("Pensioner")) {
            this.price = price - price * 0.35f;
        }
        else {
            this.price = price;
        }
    }

    public Ticket() {

    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getPlay() {
        return play;
    }
    public void setPlay(String play) {
        this.play = play;
    }

    public String getRow() {
        return row;
    }
    public void setRow(String row) {
        this.row = row;
    }

    public int getPlace() {
        return place;
    }
    public void setPlace(int place) {
        this.place = place;
    }

    public boolean getAvailability() {
        return availability;
    }
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Ticket for play '" + this.play + "', type " + this.type + ", hall " + this.location + ", row " + this.row + ", place " + this.place + ", price " +
                this.price;
    }

}
