package theatre.model;

public class Playwright extends Employee {
    String play;

    public Playwright(String lastName, String firstName, String birthDate, String address, String phoneNumber, String play) {
        super(lastName, firstName, birthDate, address, phoneNumber, "Playwright");
        this.play = play;
    }

    public Playwright(String lastName, String firstName,  String birthDate, String address, String phoneNumber) {
        super(lastName, firstName, birthDate, address, phoneNumber, "Playwright");
    }

    public String getPlay() {
        return play;
    }
    public void setPlay(String play) {
        this.play = play;
    }

}
