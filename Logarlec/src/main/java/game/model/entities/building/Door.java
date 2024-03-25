package game.model.entities.building;

import game.model.entities.Character;
import game.model.logging.Suttogo;

public class Door {
    private Room from;
    private Room to;

    private boolean bothWays;

    private boolean visible;

<<<<<<< HEAD
    //Konstruktor: Létrehozza az ajtót, beállítja, hogy melyik szobák tartoznak hozzá,
    //hogy mindkét irányból lehet-e használni, és hogy látható-e
=======
>>>>>>> 4a0a2b0d236c5c3f561ee41dd7a75984b397d749
    public Door(Room f, Room t, boolean bW, boolean v) {
        from = f;
        to = t;
        bothWays = bW;
        visible = v;
    }

    public boolean accept(Character c, Room r){
        Suttogo.info("accept(Character, Room)");
        if(r.getCharacters().size()+1 >= r.getCapacity()) {
            Suttogo.info("\treturn false");
            return false;
        }
        else r.addCharacter(c);
        Suttogo.info("\treturn true");
        return true;
    }

    public Room getNeighbour(Room r){
        Suttogo.info("getNeighbour(Room)");
        Suttogo.info("\treturn Room");
        if(r==from) return to;
        else return from;
    }
}
