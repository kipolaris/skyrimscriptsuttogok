package game.model.entities.building;

import game.model.entities.Character;
import game.model.logging.Suttogo;

public class Door {
    private Room from;
    private Room to;

    private boolean bothWays;

    private boolean visible;

    //Konstruktor: Létrehozza az ajtót, beállítja, hogy melyik szobák tartoznak hozzá,
    //hogy mindkét irányból lehet-e használni, és hogy látható-e
    public Door(Room f, Room t, boolean bW, boolean v) {
        from = f;
        to = t;
        bothWays = bW;
        visible = v;
    }

    //Megadja, hogy az adott karakter át tud-e lépni az adott szobába az ajtón keresztül,
    //és ha át tud lépni akkor átlépteti
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

    //Lekérdezi az adott szobának a szomszédját
    public Room getNeighbour(Room r){
        Suttogo.info("getNeighbour(Room)");
        Suttogo.info("\treturn Room");
        if(r==from) return to;
        else return from;
    }
}
