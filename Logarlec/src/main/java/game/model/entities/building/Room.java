package game.model.entities.building;

import game.model.entities.Professor;
import game.model.entities.Character;
import game.model.entities.Student;
import game.model.entities.items.FFP2;
import game.model.entities.items.Item;
import game.model.logging.Suttogo;
import game.model.main.GameEngine;

import java.util.*;

public class Room {

    private GameEngine gameEngine;

    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }
    private int capacity;
    private boolean gassed;
    private boolean cursed;

    private ArrayList<Door> doors;

    private ArrayList<Item> items;

    private ArrayList<Character> characters;

    // Constructor
    public Room(int c, boolean g, boolean cu, ArrayList<Door> ds, ArrayList<Item> is, ArrayList<Character> cs) {
        capacity = c;
        gassed = g;
        cursed = cu;
        doors = ds != null ? new ArrayList<>(ds) : new ArrayList<>();
        items = is != null ? new ArrayList<>(is) : new ArrayList<>();
        characters = cs != null ? new ArrayList<>(cs) : new ArrayList<>();
    }

    public void setDoors(ArrayList<Door> ds) {
        for(Door d: ds) {
            doors.add(d);
        }
    }

    public void addDoor(Door door) {
        doors.add(door);
    }

    public Door removeDoor(Door door) {
        doors.remove(door);
        Suttogo.info("\tret null");
        return null;
    }

    public ArrayList<Door> getDoors() {
        Suttogo.info("\tret ArrayList<Door>");
        return doors;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public Item removeItem(Item item) {
        items.remove(item);
        Suttogo.info("\tret null");
        return null;
    }

    public ArrayList<Item> getItems() {
        Suttogo.info("\tret ArrayList<Item>");
        return items;
    }

    public void addCharacter(Character character) {
        characters.add(character);
    }

    public void removeCharacter(Character character) {
        characters.remove(character);
    }

    public ArrayList<Character> getCharacters() {
        Suttogo.info("\tret ArrayList<Character>");
        return characters;
    }

    public ArrayList<Student> getStudents() {
        ArrayList<Student> students = null;
        for (Character character : characters) {
            if (character instanceof Student) {
                students.add((Student) character);
            }
        }
        Suttogo.info("\tret ArrayList<Student>");
        return students;
    }

    public ArrayList<Professor> getProfessors() {
        ArrayList<Professor> professors = null;
        for (Character character : characters) {
            if (character instanceof Professor) {
                professors.add((Professor) character);
            }
        }
        Suttogo.info("\tret ArrayList<Professor>");
        return professors;
    }

    public void paralyzeProfessors() {
        for (Character character : characters) {
            if (character instanceof Professor) {
                character.setParalyzed(true);
            }
        }
    }

    public void setGassed(boolean g) {
        gassed = g;
    }

    public void setCursed(boolean c) { cursed = c; }

    public void checkGas() {
        if(gassed) {
            for (Character character : characters) {
                if (character.getItems().stream().noneMatch(FFP2.class::isInstance)) {
                    character.setParalyzed(true);
                }
            }
        }
    }

    public void killStudents() {
        for (Character character : characters) {
            if (character instanceof Student) {
                character.die();
            }
        }
    }

    public boolean getGassed() {
        Suttogo.info("\tret boolean");
        return gassed;
    }

    public boolean getCursed() {
        Suttogo.info("\tret boolean");
        return cursed;
    }

    public int getCapacity() {
        Suttogo.info("\tret int");
        return capacity;
    }
}
