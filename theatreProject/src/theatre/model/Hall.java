package theatre.model;

import java.util.List;

public class Hall {
    String name;
    int floor;
    int capacity;
    List<Play> plays;

    public Hall(String name, int floor, int capacity, List<Play> plays) {
        this.name = name;
        this.floor = floor;
        this.capacity = capacity;
        this.plays = plays;
    }

    public Hall(String name, int floor, int capacity) {
        this.name = name;
        this.floor = floor;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Play> getPlays() {
        return plays;
    }

    public void setPlays(List<Play> plays) {
        this.plays = plays;
    }

    @Override
    public String toString() {
        return "Welcome to '" + this.name + "', located at floor " + this.floor + ", with the capacity of " + this.capacity + " spectators.";
    }
}
