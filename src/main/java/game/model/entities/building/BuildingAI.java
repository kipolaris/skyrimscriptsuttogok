package game.model.entities.building;

import game.model.entities.Character;
import game.model.entities.items.Item;
import game.model.logging.Suttogo;
import game.model.main.GameMain;

import java.util.*;
import java.util.stream.Collectors;

import static game.model.main.GameMain.gameEngine;

/**A pályaépítő osztálya*/

public class BuildingAI {
    /**Beállítja az egyedi azonosító értékét*/
    public static void setRoomID(int roomID) {
        BuildingAI.roomID = roomID;
    }
    private static int roomID = 0;
    private final Map<String, Room> labyrinth = new HashMap<>();

    /**Visszaad egy egyedi szoba azonosítót*/
    public static int getRoomID() {
        return roomID++;
    }

    /**Visszaadja a szobák egy kulccsal ellátott osztályát*/
    public Map<String, Room> getLabyrinth(){
        return labyrinth;
    }

    /**
     * Összeolvaszt két szobát a labirintusban
     */
    public void mergeRooms(Room r1, Room r2){
        int ossz = r1.getCharacters().size()+r2.getCharacters().size();
        int max = 0;
        if (r1.getCapacity() > r2.getCapacity())
            max = r1.getCapacity();
        else max = r2.getCapacity();

        if (ossz <= max) {
            ArrayList<Item> targyak = new ArrayList<>(r1.getItems());
            ArrayList<Character> karakter = new ArrayList<>(r1.getCharacters());

            karakter.addAll(r2.getCharacters());

            targyak.addAll(r2.getItems());

            boolean gaz = false;
            boolean atok = false;

            if (r1.getCursed()) atok = true;
            else if (r2.getCursed()) atok = true;

            if (r1.getGassed()) gaz = true;
            else if (r2.getGassed()) gaz = true;

            Room uj = new Room(max, gaz, atok, doorLogic(r1, r2), targyak, karakter);

            this.addRoom(uj);
            this.removeRoom(r1);
            this.removeRoom(r2);

            refineDoors(uj, r1);
            refineDoors(uj, r2);

            Suttogo.getSuttogo().info("Rooms merged");
            Suttogo.getSuttogo().note(labyrinth.size() + " rooms in labyrinth");
        }
    }

    /**
     * Egyesíti a két megadott szoba ajtajait
     * @param r1 az egyik szoba
     * @param r2 a másik szoba
     * @return az ajtók listája
     */
    private List<Door> doorLogic(Room r1, Room r2) {

        Set<Door> ajtok = new HashSet<>(r1.getDoors());

        Set<Door> r2Doors = new HashSet<>(r2.getDoors());

        // Remove common doors from the ajtok set
        ajtok.removeAll(r2Doors);

        // Add unique doors from r2 to ajtok
        ajtok.addAll(r2Doors.stream()
                .filter(door -> !r1.getDoors().contains(door))
                .collect(Collectors.toSet()));

        return new ArrayList<>(ajtok);
    }

    /**
     * Átrendezzük az ajtókat, hogy a régi szobára mutató ajtók az új szobára mutassanak
     * @param newroom az új szoba
     * @param oldroom a régi szoba
     */
    private void refineDoors(Room newroom, Room oldroom){
        for (Door d : newroom.getDoors()) {
            if(d.getFrom() == oldroom) d.setFrom(newroom);
            //nyilván egyszerre a kettő nem lehet, mert akkor saját magába lenne ajtó
            if(d.getTo() == oldroom) d.setTo(newroom);
        }

        // Use a Set to remove duplicate doors
        Set<Door> uniqueDoors = new HashSet<>(newroom.getDoors());

        // Replace the list of doors in newroom with the unique doors
        newroom.setDoors(new ArrayList<>(uniqueDoors));
    }

    /**Szétválasztunk egy adott szobát a labirintusból két szobára*/
    public void splitRoom(Room r1){
        if (r1.getDoors().size() >= 2){
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

            GameMain.perform("neighbour "+uj1.getId()+" "+uj2.getId());

            this.addRoom(uj1);
            this.addRoom(uj2);
            this.removeRoom(r1);

            refineDoors(uj1, r1);
            refineDoors(uj2, r1);

            Suttogo.getSuttogo().info("Room split");
            Suttogo.getSuttogo().note(labyrinth.size() + " rooms in labyrinth");
        }
    }

    /**Hozzáadunk egy szobát a labirintushoz*/
    public void addRoom(Room r1){
        labyrinth.put(r1.getId(), r1);
    }

    /**Eltávolítunk egy szobát a labirintusból*/
    public void removeRoom(Room r1){
        labyrinth.remove(r1.getId());

        List<Door> doorsToRemove = new ArrayList<>();

        for(Door d : r1.getDoors()){
            Room r2 = d.getNeighbour(r1);
            r2.getDoors().remove(d);
            doorsToRemove.add(d);
        }

        r1.getDoors().removeAll(doorsToRemove);
    }

    /**Visszaad egy szabad szobát, ami nem egyezik a megadott szobával*/
    public Room getFreeRoom(Room r) {
        for(Room room : labyrinth.values()) {
            if(!room.equals(r) && !room.isFull()) return room;
        }
        return null;
    }
}
