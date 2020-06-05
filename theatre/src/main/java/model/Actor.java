package model;

import java.util.ArrayList;
import java.util.List;

public class Actor extends Employee {
    private List<String> roles = new ArrayList<>();
    private List<String> plays = new ArrayList<>();

    public Actor(String lastName, String firstName, String birthDate, String address, String phoneNumber, List<String> roles, List<String> plays) {
        super(lastName, firstName, birthDate, address, phoneNumber, "Actor");
        this.roles = roles;
        this.plays = plays;
    }
    public Actor(String lastName, String firstName, String birthDate, String address, String phoneNumber) {
        super(lastName, firstName, birthDate, address, phoneNumber, "Actor");
    }

    public Actor(int id, String lastName, String firstName, String birthDate, String address, String phoneNumber) {
        super(lastName, firstName, birthDate, address, phoneNumber, "Actor");
    }

    public Actor() {

    }

    public List<String> getRoles() {
        return roles;
   }

   public List<String> getPlays() {
        return plays;
   }

   public void addRole(String role) {
        roles.add(role);
   }
   public void removeRole(int roleIndex) {
        roles.remove(roleIndex);
   }

   public void addPlay(String play) {
        plays.add(play);
   }
   public void removePlay(int playIndex) {
        plays.remove(playIndex);
   }

}
