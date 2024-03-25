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

<<<<<<< HEAD
    // Konstruktor: létrehoz egy szobát, beállítja a tulajdonságait,
    // és inicializálja a szobához tartozó entitások és ajtók listáit
=======
    // Constructor
>>>>>>> 4a0a2b0d236c5c3f561ee41dd7a75984b397d749
    public Room(int c, boolean g, boolean cu, ArrayList<Door> ds, ArrayList<Item> is, ArrayList<Character> cs) {
        capacity = c;
        gassed = g;
        cursed = cu;
        doors = ds != null ? new ArrayList<>(ds) : new ArrayList<>();
        items = is != null ? new ArrayList<>(is) : new ArrayList<>();
        characters = cs != null ? new ArrayList<>(cs) : new ArrayList<>();
    }
    public void setDoors(ArrayList<Door> ds) {
        Suttogo.info("setDoors(ArrayList<Door>)");
        for(Door d: ds) {
            doors.add(d);
        }
    }

    public void addDoor(Door door) {
        Suttogo.info("addDoor(Door)");
        doors.add(door);
    }

    public Door removeDoor(Door door) {
        doors.remove(door);
        Suttogo.info("removeDoor(Door)");
        Suttogo.info("\treturn null");
        return null;
    }

    public ArrayList<Door> getDoors() {
        Suttogo.info("getDoors()");
        Suttogo.info("\treturn ArrayList<Door>");
        return doors;
    }

    public void addItem(Item item) {
        Suttogo.info("addDoor(Item)");
        items.add(item);
    }

    public Item removeItem(Item item) {
        items.remove(item);
        Suttogo.info("removeItem(Item)");
        Suttogo.info("\treturn null");
        return null;
    }

    public ArrayList<Item> getItems() {
        Suttogo.info("getItems()");
        Suttogo.info("\treturn ArrayList<Item>");
        return items;
    }

    public void addCharacter(Character character) {
        Suttogo.info("addCharacter(Character)");
        characters.add(character);
    }

    public void removeCharacter(Character character) {
        Suttogo.info("removeCharacter(Character)");
        characters.remove(character);
    }

    public ArrayList<Character> getCharacters() {
        Suttogo.info("getCharacters()");
        Suttogo.info("\treturn ArrayList<Character>");
        return characters;
    }

    public ArrayList<Student> getStudents() {
        ArrayList<Student> students = null;
        for (Character character : characters) {
            if (character instanceof Student) {
                students.add((Student) character);
            }
        }
        Suttogo.info("getStudents()");
        Suttogo.info("\treturn ArrayList<Student>");
        return students;
    }

    public ArrayList<Professor> getProfessors() {
        ArrayList<Professor> professors = null;
        for (Character character : characters) {
            if (character instanceof Professor) {
                professors.add((Professor) character);
            }
        }
        Suttogo.info("getProfessors()");
        Suttogo.info("\treturn ArrayList<Professor>");
        return professors;
    }

    public void paralyzeProfessors() {
        Suttogo.info("paralyzeProfessors()");
        for (Character character : characters) {
            if (character instanceof Professor) {
                character.setParalyzed(true);
            }
        }
    }

    public void setGassed(boolean g) {
        Suttogo.info("setGassed()");
        gassed = g;
    }

    public void setCursed(boolean c) {
        Suttogo.info("setCursed()");
        cursed = c;
    }

    public void checkGas() {
        Suttogo.info("checkGas()");
        if(gassed) {
            for (Character character : characters) {
                if (character.getItems().stream().noneMatch(FFP2.class::isInstance)) {
                    character.setParalyzed(true);
                }
            }
        }
    }

    public void killStudents() {
        Suttogo.info("killStudents()");
        for (Character character : characters) {
            if (character instanceof Student) {
                character.die();
            }
        }
    }

    public boolean getGassed() {
        Suttogo.info("getGassed()");
        Suttogo.info("\treturn boolean");
        return gassed;
    }

    public boolean getCursed() {
        Suttogo.info("getCursed()");
        Suttogo.info("\treturn boolean");
        return cursed;
    }

    public int getCapacity() {
        Suttogo.info("getCapacity()");
        Suttogo.info("\treturn int");
        return capacity;
    }
}
