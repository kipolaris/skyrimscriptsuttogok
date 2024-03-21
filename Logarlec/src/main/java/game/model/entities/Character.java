package game.model.entities;

import game.model.entities.building.Door;
import game.model.entities.building.Room;
import game.model.entities.items.*;

import java.util.ArrayList;

public class Character {
    protected boolean paralyzed;

    protected int actions;
    protected Room location;

    public Room getLocation() {
        return location;
    }

    public void setLocation(Room location) {
        this.location = location;
    }


    public void useItem(Item i) {
        // Method body to be implemented
    }

    public ArrayList<Item> getItems() {
        // Method body to be implemented
        return null;
    }

    public void addItem(Item item) {
        // Method body to be implemented
    }

    public void dropItem(Item item) {
        // Method body to be implemented
    }

    public void setParalyzed(boolean b) {
        // Method body to be implemented
    }

    public void setProfessorParalyzed(boolean b) {
        // Method body to be implemented
    }

    public void move(Door d) {
        // Method body to be implemented
    }

    public void skipTurn() {
        // Method body to be implemented
    }

    public void doRound() {
        // Method body to be implemented
    }

    public Transistor getActiveTransistor() {
        // Method body to be implemented
        return null;
    }

    public boolean die() {
        // Method body to be implemented
        return false;
    }
}
