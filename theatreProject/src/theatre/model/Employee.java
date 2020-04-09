package theatre.model;

public abstract class Employee {
    private String lastName;
    private String firstName;
    private static int id;
    private String birthDate;
    private String address;
    private String phoneNumber;
    private String job;

    public Employee(String lastName, String firstName, String birthDate, String address, String phoneNumber, String job) {
        this.lastName = lastName;
        this.firstName = firstName;
        id++;
        this.birthDate = birthDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.job = job;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public String getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getJob() {
        return job;
    }
    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return this.firstName + ' ' + this.lastName + ", who is a/an " + this.job + ", born in " + this.birthDate + ", lives in " + this.address
                + " and has the phone number " + this.phoneNumber;
    }



}
