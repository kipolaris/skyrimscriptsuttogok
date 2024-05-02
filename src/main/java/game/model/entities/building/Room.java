package game.model.entities.building;

import game.model.entities.Professor;
import game.model.entities.Character;
import game.model.entities.Student;
import game.model.entities.items.FFP2;
import game.model.entities.items.Item;
import game.model.logging.Suttogo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.*;

import static game.model.main.GameMain.gameEngine;
@XmlRootElement
public class Room {
    public String getId() {
        return id;
    }
    @XmlAttribute
    private String id;
    @XmlElement
    private int capacity;
    @XmlElement
    private boolean gassed;
    @XmlElement
    private boolean cursed;

    public boolean isWasCleaned() {
        return wasCleaned;
    }
    @XmlElement
    private boolean wasCleaned;
    @XmlElement
    private boolean sticky;
    @XmlElement
    private int visitors;
    @XmlElement
    private boolean hasAirFreshener;
    @XmlElement
    private ArrayList<Door> doors;
    @XmlElement
    private ArrayList<Item> items;

    @XmlElement
    private ArrayList<Character> characters;

    public Room(){
        id = "Room" + BuildingAI.getRoomID();

        gameEngine.getBuilder().addRoom(this);
    }

    public ArrayList<Room> getNeighbours(){
        ArrayList<Room> n = new ArrayList<>();

        for (Door d: doors) {
            n.add(d.getNeighbour(this));
        }

        return n;
    }

    public Door getDoorOf(Room r){
        for(Door d : doors){
            if(d.getNeighbour(r).equals(this)){
                return d;
            }
        }

        return null;
    }


    /** Konstruktor: létrehoz egy szobát, beállítja a tulajdonságait,
    * és inicializálja a szobához tartozó entitások és ajtók listáit*/
    public Room(int c, boolean g, boolean cu, ArrayList<Door> ds, ArrayList<Item> is, ArrayList<Character> cs) {
        id = "Room" + BuildingAI.getRoomID();

        capacity = c;
        gassed = g;
        cursed = cu;
        doors = ds != null ? new ArrayList<>(ds) : new ArrayList<>();
        items = is != null ? new ArrayList<>(is) : new ArrayList<>();
        characters = cs != null ? new ArrayList<>(cs) : new ArrayList<>();
        wasCleaned = false;
        sticky = false;
        visitors = 0;
        hasAirFreshener = false;

        //rögtön hozzá is adjuk a labirynthoz
        gameEngine.getBuilder().addRoom(this);
    }

    /**Beállítja a szoba ajtajait*/
    public void setDoors(List<Door> ds) {
        Suttogo.info("setDoors(ArrayList<Door>)");
        for(Door d: ds) {
            doors.add(d);
        }
    }

    /**Hozzáad egy ajtót a szobához*/
    public void addDoor(Door door) {
        Suttogo.info("addDoor(Door)");
        doors.add(door);
    }

    /**Eltávolítja a szoba egyik ajtaját*/
    public Door removeDoor(Door door) {
        doors.remove(door);
        Suttogo.info("removeDoor(Door)");
        Suttogo.info("\treturn null");
        return null;
    }

    /**Lekérdezi a szoba ajtóit*/
    public ArrayList<Door> getDoors() {
        Suttogo.info("getDoors()");
        Suttogo.info("\treturn ArrayList<Door>");
        return doors;
    }

    /**Lehelyez egy tárgyat a szobában*/
    public void addItem(Item item) {
        Suttogo.info("addDoor(Item)");
        items.add(item);
    }

    /**Eltávolít egy tárgyat a szobából*/
    public Item removeItem(Item item) {
        items.remove(item);
        Suttogo.info("removeItem(Item)");
        Suttogo.info("\treturn null");
        return null;
    }

    /**Lekérdezi a szobában lévő tárgyakat*/
    public ArrayList<Item> getItems() {
        Suttogo.info("getItems()");
        Suttogo.info("\treturn ArrayList<Item>");
        return items;
    }

    public boolean isFull() {
        return characters.size() == capacity;
    }

    /**Hozzáad egy karaktert a szobához*/
    public boolean addCharacter(Character character) {
        Suttogo.info("addCharacter(Character)");
        if(!isFull()){
            Room room = character.getLocation();
            if(room!=null) room.removeCharacter(character);
            characters.add(character);
            character.setLocation(this);
            if(wasCleaned && (++visitors > 4)) { sticky = true; }
            return true;
        }
        return false;
    }

    /**Eltávolít egy karaktert a szobából*/
    public void removeCharacter(Character character) {
        Suttogo.info("removeCharacter(Character)");
        characters.remove(character);
    }

    /**Lekérdezi a szobában lévő karaktereket*/
    public ArrayList<Character> getCharacters() {
        Suttogo.info("getCharacters()");
        Suttogo.info("\treturn ArrayList<Character>");
        return characters;
    }

    /**Lekérdezi a szobában lévő hallgatókat*/
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

    /**Lekérdezi a szobában lévő oktatókat*/
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

    /**Megbénítja a szobában lévő oktatókat*/
    public void paralyzeProfessors() {
        Suttogo.info("paralyzeProfessors()");
        for (Character character : characters) {
            if (character instanceof Professor) {
                character.setParalyzed(true);
            }
        }
    }

    /**Elgázosítja a szobát*/
    public void setGassed(boolean g) {
        Suttogo.info("setGassed()");
        gassed = g;
    }

    /**Elátkozza a szobát*/
    public void setCursed(boolean c) {
        Suttogo.info("setCursed()");
        cursed = c;
    }

    /**Megpróbálja megbénítani a szobában tartozkodó karaktereket,
    *és ha nincs védelmük meg is bénítja*/
    public void checkGas() {
        Suttogo.info("checkGas()");
        if(gassed) {
            for (Character character : characters) {
                if (character.getItems().values().stream().noneMatch(FFP2.class::isInstance)) {
                    character.setParalyzed(true);
                }
            }
        }
    }

    /**Megöli a szobában tartozkodó hallgatókat*/
    public void killStudents() {
        Suttogo.info("killStudents()");
        for (Character character : characters) {
            if (character instanceof Student) {
                character.die();
            }
        }
    }

    /**Lekérdezi, hogy gázos-e a szoba*/
    public boolean getGassed() {
        Suttogo.info("getGassed()");
        Suttogo.info("\treturn boolean");
        return gassed;
    }

    /**Lekérdezi, hogy átkozott-e a szoba*/
    public boolean getCursed() {
        Suttogo.info("getCursed()");
        Suttogo.info("\treturn boolean");
        return cursed;
    }

    /**Lekérdezi a szoba kapacitását*/
    public int getCapacity() {
        Suttogo.info("getCapacity()");
        Suttogo.info("\treturn int");
        return capacity;
    }

    public void setSticky(boolean s){
        this.sticky = s;
    }


    /**Beállítja a wasCleaned és a sticky értékét*/
    public void setWasCleaned() {
        wasCleaned = true;
        sticky = false;
    }

    /**Visszaadja a sticky értékét*/
    public boolean getSticky() {
        return  sticky;
    }

    /**Minden karaktert, amelyik nem Cleaner és nem paralyzed, kiküldi a szobából*/
    public void sendOut() {
        ArrayList<Professor> professors = getProfessors();
        ArrayList<Student> students = getStudents();
        for(Student student : students) {
            if(!student.getParalyzed()) {
                student.getLocation().removeCharacter(student);
                Room destination = gameEngine.getBuilder().getFreeRoom(this);
                if(destination!=null) {
                    student.setLocation(destination);
                    destination.addCharacter(student);
                }
            }
        }
        for(Professor professor : professors) {
            if(!professor.getParalyzed()) {
                professor.getLocation().removeCharacter(professor);
                Room destination = gameEngine.getBuilder().getFreeRoom(this);
                if(destination!=null) {
                    professor.setLocation(destination);
                    destination.addCharacter(professor);
                }
            }
        }
    }

    /**Igazra állítja a hasAirFreshener értékét*/
    public void setHasAirFreshener() {
        hasAirFreshener = true;
    }
}