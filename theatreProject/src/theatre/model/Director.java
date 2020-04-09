package theatre.model;

public class Director {
    private static Director p = null;
    private String firstName;
    private String lastName;

    private Director() {
        this.firstName = "Ion";
        this.lastName = "Caramitru";
    }

    public static Director getDirector() {
        if(p == null) {
            p = new Director();
        }
        return p;
    }

    @Override
    public String toString() {
        return "The director of the theatre is " + this.firstName + " " + this.lastName;
    }
}
