package game.model.entities.building;

import game.model.entities.Character;
import game.model.logging.Suttogo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**Az ajtó osztálya*/
@XmlRootElement
public class Door {
    @XmlElement
    private Room from;
    @XmlElement
    private Room to;

    @XmlElement
    private boolean bothWays;
    @XmlElement

    private boolean visible;

    /**Konstruktor: Létrehozza az ajtót, beállítja, hogy melyik szobák tartoznak hozzá,
    *hogy mindkét irányból lehet-e használni, és hogy látható-e
     */
    public Door(Room f, Room t, boolean bW, boolean v) {
        from = f;
        to = t;
        bothWays = bW;
        visible = v;
    }

    /**Megadja, hogy az adott karakter át tud-e lépni az adott szobába az ajtón keresztül,
    *és ha át tud lépni akkor átlépteti
     */
    public boolean accept(Character c, Room r){
        Suttogo.info("accept(Character, Room)");
        if((!bothWays && !from.equals(r)) || to.getCharacters().size()+1 >= to.getCapacity()) {
            Suttogo.info("\treturn false");
            return false;
        }
        Suttogo.info("\treturn true");
        return true;
    }

    /**Lekérdezi az adott szobának a szomszédját*/
    public Room getNeighbour(Room r){
        Suttogo.info("getNeighbour(Room)");
        Suttogo.info("\treturn Room");
        if(r==from) return to;
        return from;
    }

    /**Beállítja az ajtó láthatóságát*/
    public void setVisible(boolean v) {
        visible = v;
    }

    /**
     * Visszaadja, hogy az ajtó láthatóságát.
     */
    public boolean getVisible() { return visible; }
}
