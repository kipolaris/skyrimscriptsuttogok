package game.model.entities.building;

import game.model.entities.Character;
import game.model.logging.Suttogo;


/**Az ajtó osztálya*/
public class Door {
    private Room from;
    private Room to;
    private boolean bothWays;
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
        if((!bothWays && !from.equals(r)) || to.getCharacters().size()+1 >= to.getCapacity()) {
            return false;
        }
        return true;
    }

    /**Lekérdezi az adott szobának a szomszédját*/
    public Room getNeighbour(Room r){
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

    /**
     * Az ajtó létrehozásához szükséges command
     */
    public String create(){
        if (bothWays) {
            if (visible)return "neighbour "+from.getId()+" "+to.getId()+" twoways visible";
            else return "neighbour "+from.getId()+" "+to.getId()+" twoways invisible";
        }else{
            if (visible) return "neighbour "+from.getId()+" "+to.getId()+" oneway_tosecond visible";
            else return "neighbour "+from.getId()+" "+to.getId()+" oneway_tosecond invisibility";
        }
    }

    public boolean isItInwards(Room r){
        if(!bothWays){
            return to.equals(r);
        }
        return true;
    }

    public boolean isBothWays(){
        return bothWays;
    }
}
