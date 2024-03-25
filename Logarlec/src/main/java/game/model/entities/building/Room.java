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

    // Konstruktor: létrehoz egy szobát, beállítja a tulajdonságait,
    // és inicializálja a szobához tartozó entitások listáit
    public Room(int c, boolean g, boolean cu, ArrayList<Door> ds, ArrayList<Item> is, ArrayList<Character> cs) {
        capacity = c;
        gassed = g;
        cursed = cu;
        doors = ds != null ? new ArrayList<>(ds) : new ArrayList<>();
        items = is != null ? new ArrayList<>(is) : new ArrayList<>();
        characters = cs != null ? new ArrayList<>(cs) : new ArrayList<>();
    }

    //Beállítja a szoba ajtajait
    public void setDoors(ArrayList<Door> ds) {
        for(Door d: ds) {
            doors.add(d);
        }
    }

    //Hozzáad egy ajtót a szobához
    public void addDoor(Door door) {
        doors.add(door);
    }


    //Eltávolít egy ajtót a szobából
    public Door removeDoor(Door door) {
        doors.remove(door);
        Suttogo.info("\tret null");
        return null;
    }

    //Lekérdezi a szoba ajtajait
    public ArrayList<Door> getDoors() {
        Suttogo.info("\tret ArrayList<Door>");
        return doors;
    }

    //Lehelyez egy tárgyat a szobába
    public void addItem(Item item) {
        items.add(item);
    }

    //Eltávolít egy tárgyat a szobából
    public Item removeItem(Item item) {
        items.remove(item);
        Suttogo.info("\tret null");
        return null;
    }

    //Lekérdezi a szobában levő tárgyakat
    public ArrayList<Item> getItems() {
        Suttogo.info("\tret ArrayList<Item>");
        return items;
    }

    //Hozzáad egy karaktert a szobához
    public void addCharacter(Character character) {
        characters.add(character);
    }

    //Eltávolít egy karaktert a szobából
    public void removeCharacter(Character character) {
        characters.remove(character);
    }

    //Lekérdezi a szobában tartozkodó karaktereket
    public ArrayList<Character> getCharacters() {
        Suttogo.info("\tret ArrayList<Character>");
        return characters;
    }

    //Lekérdezi a szobában tartozkodó hallgatókat
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

    //Lekérdezi a szobában tartozkodó oktatókat
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

    //Megbénítja a szobában tartozkodó oktatókat
    public void paralyzeProfessors() {
        for (Character character : characters) {
            if (character instanceof Professor) {
                character.setParalyzed(true);
            }
        }
    }

    //Elgázosítja a szobát
    public void setGassed(boolean g) {
        gassed = g;
    }

    //Elátkozza a szobát
    public void setCursed(boolean c) { cursed = c; }

    //Ellenőrzi, hogy a szobában levő karaktereknek van-e védelme gáz ellen,
    //és ha nincs akkor lebénítja őket
    public void checkGas() {
        if(gassed) {
            for (Character character : characters) {
                if (character.getItems().stream().noneMatch(FFP2.class::isInstance)) {
                    character.setParalyzed(true);
                }
            }
        }
    }

    //Megöli a szobában tartozkodó hallgatókat
    public void killStudents() {
        for (Character character : characters) {
            if (character instanceof Student) {
                character.die();
            }
        }
    }

    //Lekérdezi, hogy gázos-e a szoba
    public boolean getGassed() {
        Suttogo.info("\tret boolean");
        return gassed;
    }

    //Lekérdezi, hogy átkozott-e a szoba
    public boolean getCursed() {
        Suttogo.info("\tret boolean");
        return cursed;
    }

    //Lekérdezi a szoba kapacitását
    public int getCapacity() {
        Suttogo.info("\tret int");
        return capacity;
    }
}
