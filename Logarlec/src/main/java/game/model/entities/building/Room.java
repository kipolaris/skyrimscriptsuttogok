package game.model.entities.building;

import game.model.entities.Professor;
import game.model.entities.Character;
import game.model.entities.Student;
import game.model.entities.Character;
import game.model.entities.items.Item;
import game.model.main.GameEngine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

//#todo: implement class
public class Room {

    private GameEngine gameEngine;

    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }
    private static final Logger logger = LogManager.getLogger();
    private int capacity;
    private boolean gassed;
    private boolean cursed;

    private ArrayList<Door> doors;

    private ArrayList<Item> items;

    private ArrayList<Character> characters;

    // Constructor
    public Room() {
        // Initialization logic can be added here
    }

    // Methods with empty bodies
    public void setDoors(ArrayList<Door> doors) {
        // Method body to be implemented
    }

    public void addDoor(Door door) {
        // Method body to be implemented
    }

    public Door removeDoor(Door door) {
        // Method body to be implemented
        return null;
    }

    public void addItem(Item item) {
        // Method body to be implemented
    }

    public Item removeItem(Item item) {
        // Method body to be implemented
        return null;
    }

    public ArrayList<Item> getItems() {
        // Method body to be implemented
        return null;
    }

    public boolean addCharacter(Character character) {
        // Method body to be implemented
        return false;
    }

    public boolean removeCharacter(Character character) {
        // Method body to be implemented
        return false;
    }

    public ArrayList<Student> getStudents() {
        // Method body to be implemented
        return null;
    }

    public ArrayList<Professor> getProfessors() {
        // Method body to be implemented
        return null;
    }

    public void paralyzeProfessors() {
        // Method body to be implemented
    }

    public void setGassed(boolean gassed) {
        // Method body to be implemented
    }

    public void checkGas() {
        // Method body to be implemented
    }

    public void killStudents() {
        // Method body to be implemented
    }

    public boolean getGassed() {
        // Method body to be implemented
        return false;
    }

    public boolean getCursed() {
        // Method body to be implemented
        return false;
    }
}
