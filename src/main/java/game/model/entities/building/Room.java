package game.model.entities.building;

import game.model.entities.Professor;
import game.model.entities.Character;
import game.model.entities.Student;
import game.model.entities.items.Item;

import java.util.*;

import static game.model.main.GameMain.gameEngine;

//#todo: itt is megvalósítani a listener logikát
/**A szoba osztálya*/

public class Room {
    /**Visszaadja egy szoba egyedi azonosítóját*/
    public String getId() {
        return id;
    }

    private String id;

    private int capacity;

    private boolean gassed;

    private boolean cursed;

    /**Visszaadja, hogy a szoba volt-e takarítva*/
    public boolean isWasCleaned() {
        return wasCleaned;
    }

    private boolean wasCleaned;

    private boolean sticky;

    private int visitors;

    private boolean hasAirFreshener;

    private ArrayList<Door> doors;

    private ArrayList<Item> items;


    private ArrayList<Character> characters;

    /**
     * Paraméteres, kifinomult konstruktor. Beállítja a szoba tulajdonságait.
     *
     * @Attention A tárgyakat és karaktereket HOZZÁADJA a szobához! Előtte NE add hozzá!
     * @Attention A szobát NEM adja hozzá a labirinthoz! Azt neked kell!
     * @param max A szoba kapacitása
     * @param gaz Gázos-e a szoba
     * @param atok Átkozott-e a szoba
     * @param newDoors Az ajtók listája, NEM ELLENŐRZI
     * @param targyak A tárgyak listája, hozzáadja
     * @param karakter A karakterek listája, hozzáadja
     */
    public Room(int max, boolean gaz, boolean atok, List<Door> newDoors, List<Item> targyak, List<Character> karakter){
        id = "Room" + BuildingAI.getRoomID();

        //kezdetben üres listákra inicializálunk
        items = new ArrayList<>();
        characters = new ArrayList<>();
        doors = newDoors == null ? new ArrayList<>() : new ArrayList<>(newDoors);

        capacity = max;
        gassed = gaz;
        cursed = atok;

        if(targyak != null) {
            for (Item i : targyak) {
                addItem(i);
            }
        }

        if(karakter != null) {
            for (Character c : karakter) {
                addCharacter(c);
            }
        }

        wasCleaned = false;
        sticky = false;
        visitors = 0;
        hasAirFreshener = false;
    }

    /**Paraméter nélküli konstruktor*/
    public Room(){
        id = "Room" + BuildingAI.getRoomID();

        doors = new ArrayList<>();
        items = new ArrayList<>();
        characters = new ArrayList<>();

        wasCleaned = false;
        sticky = false;
        visitors = 0;
        hasAirFreshener = false;
    }

    /**Visszaadja a szoba szomszédjait*/
    public ArrayList<Room> getNeighbours(){
        ArrayList<Room> n = new ArrayList<>();

        for (Door d: doors) {
            n.add(d.getNeighbour(this));
        }

        return n;
    }

    /**Visszaadja azt az ajtót, amelyik az adott szobával összeköti ezt a szobát*/
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

        //ugye, mint a tárgyaknál, mind a karaktereknél is be kell állítani a helyüket
        if(is != null) {
            for (Item item : is) {
                item.setLocation(this);
            }
        }

        characters = cs != null ? new ArrayList<>(cs) : new ArrayList<>();

        if(cs != null) {
            for(Character character : cs){
                character.setLocation(this);
            }
        }

        wasCleaned = false;
        sticky = false;
        visitors = 0;
        hasAirFreshener = false;
    }

    /**Beállítja a szoba ajtajait*/
    public void setDoors(List<Door> ds) {
        doors.clear();
        for(Door d: ds) {
            doors.add(d);
        }
    }

    /**Hozzáad egy ajtót a szobához*/
    public void addDoor(Door door) {
        doors.add(door);
    }

    /**Lekérdezi a szoba ajtóit*/
    public ArrayList<Door> getDoors() {
        return doors;
    }

    /**Lehelyez egy tárgyat a szobában*/
    public void addItem(Item item) {
        items.add(item);
    }

    /**Eltávolít egy tárgyat a szobából*/
    public Item removeItem(Item item) {
        items.remove(item);
        return null;
    }

    /**Lekérdezi a szobában lévő tárgyakat*/
    public ArrayList<Item> getItems() {
        return items;
    }

    /**Visszaadja, hogy a szoba tele van-e*/
    public boolean isFull() {
        return characters.size() == capacity;
    }

    /**Hozzáad egy karaktert a szobához*/
    public boolean addCharacter(Character character) {
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
        characters.remove(character);
    }

    /**Lekérdezi a szobában lévő karaktereket*/
    public ArrayList<Character> getCharacters() {
        return characters;
    }

    /**Lekérdezi a szobában lévő hallgatókat*/
    public ArrayList<Student> getStudents() {
        ArrayList<Student> students = null;
        for (Character character : characters) {
            if(character.getId().startsWith("Student")) {
                students.add((Student) character);
            }
        }
        return students;
    }

    /**Lekérdezi a szobában lévő oktatókat*/
    public ArrayList<Professor> getProfessors() {
        ArrayList<Professor> professors = null;
        for (Character character : characters) {
            if (character.getId().startsWith("Professor")) {
                professors.add((Professor) character);
            }
        }
        return professors;
    }

    /**Megbénítja a szobában lévő oktatókat*/
    public void paralyzeProfessors() {
        for (Character character : characters) {
            if (character.getId().startsWith("Professor")) {
                character.setParalyzed(true);
            }
        }
    }

    /**Elgázosítja a szobát*/
    public void setGassed(boolean g) {
        gassed = g;
        for (Character character : characters) {
            character.setParalyzed(gassed);
        }
    }

    /**Megöli a szobában tartozkodó hallgatókat*/
    public void killStudents() {
        for (Character character : characters) {
            if (character instanceof Student) {
                character.die();
            }
        }
    }

    /**Lekérdezi, hogy gázos-e a szoba*/
    public boolean getGassed() {
        return gassed;
    }

    /**Lekérdezi, hogy átkozott-e a szoba*/
    public boolean getCursed() {
        return cursed;
    }

    /**Lekérdezi a szoba kapacitását*/
    public int getCapacity() {
        return capacity;
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
