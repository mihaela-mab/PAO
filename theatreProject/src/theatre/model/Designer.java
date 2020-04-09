package theatre.model;

public class Designer extends Employee {
    String type; // costume designer, scenic designer etc.

    public Designer(String lastName, String firstName, String birthDate, String address, String phoneNumber, String type) {
        super(lastName, firstName, birthDate, address, phoneNumber, "Designer");
        this.type = type;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
