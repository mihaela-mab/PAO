package theatre.model;

public class Ticket {
    private static int id;
    private float price;
    private String type; // full price ticket, Student, Pensioner
    private Play play;
    private String row; // 1A, 15D...
    private int place;
    private boolean availability;

    public Ticket(float price, String type, Play play, String row, int place) {
        this.type = type;
        this.play = play;
        this.row = row;
        this.place = place;
        id++;
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

    public int getId() {
        return id;
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

    public Play getPlay() {
        return play;
    }
    public void setPlay(Play play) {
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
        return "Ticket for play '" + this.play.getName() + "', type " + this.type + ", row " + this.row + ", place " + this.place + ", price " +
                this.price + ", available: " + this.availability;
    }

}
