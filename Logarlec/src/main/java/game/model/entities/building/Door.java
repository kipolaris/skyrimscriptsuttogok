package game.model.entities.building;

import game.model.entities.Character;
import game.model.logging.Suttogo;

public class Door {
    private Room from;
    private Room to;

    private boolean bothWays;

    private boolean visible;

    //Létrehozza az ajtót, beállítja, hogy melyik szobák tartoznak hozzá,
    //hogy mindkét irányból lehet-e használni, és hogy látható-e
    public Door(Room f, Room t, boolean bW, boolean v) {
        from = f;
        to = t;
        bothWays = bW;
        visible = v;
    }

    //Visszaadja, hogy egy karakter be tud-e lépni az adott szobába. Ha be tud lépni akkor be is lépteti
    public boolean accept(Character c, Room r){
        if(r.getCharacters().size()+1 >= r.getCapacity() || !visible) {
            Suttogo.info("\tret false");
            return false;
        }
        else r.addCharacter(c);
        Suttogo.info("\tret true");
        return true;
    }

    //Lekéri az ajtó adott szobájának a szomszédját
    public Room getNeighbour(Room r){
        Suttogo.info("\tret Room");
        if(r==from) return to;
        else return from;
    }
}
