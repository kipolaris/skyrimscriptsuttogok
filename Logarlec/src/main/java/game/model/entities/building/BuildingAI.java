package game.model.entities.building;

import game.model.entities.Character;
import game.model.entities.items.Item;
import game.model.logging.Suttogo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@XmlRootElement
public class BuildingAI {
    @XmlAttribute
    private static int roomID;
    @XmlElement
    private Map<String, Room> labyrinth = new HashMap<>();

    public static int getRoomID() {
        return roomID++;
    }

    public Map<String, Room> getLabyrinth(){
        return labyrinth;
    }

    /**
     * Összeolvaszt két szobát a labirintusban
     */
    public void mergeRooms(Room r1, Room r2){
        Suttogo.info("mergeRooms(Room, Room)");
        int ossz = r1.getCharacters().size()+r2.getCharacters().size();
        int max = 0;
        if (r1.getCapacity() > r2.getCapacity())
            max = r1.getCapacity();
        else max = r2.getCapacity();

        if (ossz <= max) {
            ArrayList<Door> ajtok = new ArrayList<>(r1.getDoors());
            ArrayList<Item> targyak = new ArrayList<>(r1.getItems());
            ArrayList<Character> karakter = new ArrayList<>(r1.getCharacters());

            for (Item i : r2.getItems()) {
                targyak.add(i);
            }
            for (Character c : r2.getCharacters()) {
                karakter.add(c);
            }

            ArrayList<Door> rossz = new ArrayList<>();

            for (Door d1 : ajtok) {
                for (Door d2 : r2.getDoors()) {
                    if (d1.getNeighbour(r1) == d2.getNeighbour(r2)) {
                        rossz.add(d2);
                    }
                }
            }

            for (Door egy : r2.getDoors()) {
                boolean van = false;
                for (Door ketto : rossz) {
                    if (egy.getNeighbour(r2) == ketto.getNeighbour(r2))
                        van = true;
                }
                if (!van) ajtok.add(egy);
            }

            boolean gaz = false;
            boolean atok = false;

            if (r1.getCursed()) atok = true;
            else if (r2.getCursed()) atok = true;

            if (r1.getGassed()) gaz = true;
            else if (r2.getGassed()) gaz = true;

            Room uj = new Room(max, gaz, atok, ajtok, targyak, karakter);

            this.addRoom(uj);
            this.removeRoom(r1);
            this.removeRoom(r2);
        }
    }

    /**Szétválasztunk egy adott szobát a labirintusból két szobára*/
    public void splitRoom(Room r1){
        Suttogo.info("splitRoom(Room)");
        if (!(r1.getDoors().size() < 2)){
            ArrayList<Door> ajto1 = new ArrayList<>();
            ArrayList<Door> ajto2 = new ArrayList<>();
            ArrayList<Item> targy1 = new ArrayList<>();
            ArrayList<Item> targy2 = new ArrayList<>();
            ArrayList<Character> ember1 = new ArrayList<>();
            ArrayList<Character> ember2 = new ArrayList<>();

            int n=0;
            for (Door d : r1.getDoors()){
                if (n%2 == 0) ajto1.add(d);
                else ajto2.add(d);
                n++;
            }

            n=0;
            for (Item i : r1.getItems()){
                if (n%2 == 0) targy1.add(i);
                else targy2.add(i);
                n++;
            }

            n=0;
            for (Character c : r1.getCharacters()){
                if (n%2 == 0) ember1.add(c);
                else ember2.add(c);
                n++;
            }

            Room uj1 = new Room(r1.getCapacity()/2, r1.getGassed(), r1.getCursed(), ajto1, targy1, ember1);
            Room uj2 = new Room(r1.getCapacity()/2, r1.getGassed(), r1.getCursed(), ajto2, targy2, ember2);

            this.addRoom(uj1);
            this.addRoom(uj2);
            this.removeRoom(r1);
        }
    }

    /**Hozzáadunk egy szobát a labirintushoz*/
    public void addRoom(Room r1){
        Suttogo.info("addRoom(Room)");
        labyrinth.put(r1.getId(), r1);
    }

    /**Eltávolítunk egy szobát a labirintusból*/
    public void removeRoom(Room r1){
        Suttogo.info("removeRoom(Room)");
        labyrinth.remove(r1);
    }

    /**Visszaad egy szabad szobát, ami nem egyezik a megadott szobával*/
    public Room getFreeRoom(Room r) {
        for(Room room : labyrinth.values()) {
            if(!room.equals(r) && !room.isFull()) return room;
        }
        return null;
    }
}
