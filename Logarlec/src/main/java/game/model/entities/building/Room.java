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
    // és inicializálja a szobához tartozó entitások és ajtók listáit
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
        Suttogo.info("setDoors(ArrayList<Door>)");
        for(Door d: ds) {
            doors.add(d);
        }
    }

    //Hozzáad egy ajtót a szobához
    public void addDoor(Door door) {
        Suttogo.info("addDoor(Door)");
        doors.add(door);
    }

    //Eltávolítja a szoba egyik ajtaját
    public Door removeDoor(Door door) {
        doors.remove(door);
        Suttogo.info("removeDoor(Door)");
        Suttogo.info("\treturn null");
        return null;
    }

    //Lekérdezi a szoba ajtóit
    public ArrayList<Door> getDoors() {
        Suttogo.info("getDoors()");
        Suttogo.info("\treturn ArrayList<Door>");
        return doors;
    }

    //Lehelyez egy tárgyat a szobában
    public void addItem(Item item) {
        Suttogo.info("addDoor(Item)");
        items.add(item);
    }

    //Eltávolít egy tárgyat a szobából
    public Item removeItem(Item item) {
        items.remove(item);
        Suttogo.info("removeItem(Item)");
        Suttogo.info("\treturn null");
        return null;
    }

    //Lekérdezi a szobában lévő tárgyakat
    public ArrayList<Item> getItems() {
        Suttogo.info("getItems()");
        Suttogo.info("\treturn ArrayList<Item>");
        return items;
    }

    //Hozzáad egy karaktert a szobához
    public void addCharacter(Character character) {
        Suttogo.info("addCharacter(Character)");
        characters.add(character);
    }

    //Eltávolít egy karaktert a szobából
    public void removeCharacter(Character character) {
        Suttogo.info("removeCharacter(Character)");
        characters.remove(character);
    }

    //Lekérdezi a szobában lévő karaktereket
    public ArrayList<Character> getCharacters() {
        Suttogo.info("getCharacters()");
        Suttogo.info("\treturn ArrayList<Character>");
        return characters;
    }

    //Lekérdezi a szobában lévő hallgatókat
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

    //Lekérdezi a szobában lévő oktatókat
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

    //Megbénítja a szobában lévő oktatókat
    public void paralyzeProfessors() {
        Suttogo.info("paralyzeProfessors()");
        for (Character character : characters) {
            if (character instanceof Professor) {
                character.setParalyzed(true);
            }
        }
    }

    //Elgázosítja a szobát
    public void setGassed(boolean g) {
        Suttogo.info("setGassed()");
        gassed = g;
    }

    //Elátkozza a szobát
    public void setCursed(boolean c) {
        Suttogo.info("setCursed()");
        cursed = c;
    }

    //Megpróbálja megbénítani a szobában tartozkodó karaktereket,
    //és ha nincs védelmük meg is bénítja
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

    //Megöli a szobában tartozkodó hallgatókat
    public void killStudents() {
        Suttogo.info("killStudents()");
        for (Character character : characters) {
            if (character instanceof Student) {
                character.die();
            }
        }
    }

    //Lekérdezi, hogy gázos-e a szoba
    public boolean getGassed() {
        Suttogo.info("getGassed()");
        Suttogo.info("\treturn boolean");
        return gassed;
    }

    //Lekérdezi, hogy átkozott-e a szoba
    public boolean getCursed() {
        Suttogo.info("getCursed()");
        Suttogo.info("\treturn boolean");
        return cursed;
    }

    //Lekérdezi a szoba kapacitását
    public int getCapacity() {
        Suttogo.info("getCapacity()");
        Suttogo.info("\treturn int");
        return capacity;
    }
}
