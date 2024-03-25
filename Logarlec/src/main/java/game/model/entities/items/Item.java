package game.model.entities.items;
import game.model.entities.Character;
import game.model.entities.building.Room;
import game.model.logging.Suttogo;

import java.util.ArrayList;

public abstract class Item {/** tárgy ősosztály*/
    protected boolean activated;
    protected boolean defensive;
    protected int durability;
    protected Room location;
    protected Character owner;

    public Item() {}

    public Item(boolean activated, boolean defensive, int durability, Room location, Character owner) { //Item konstruktor, alapértékek beállításához
        this.activated = activated;
        this.defensive = defensive;
        this.durability = durability;
        this.location = location;
        this.owner = owner;
    }

    // Abstract methods
    public abstract void activate();/** ez a függvény aktiválja a tárgyat, ami a legtöbb tárgynál máshogy működik*/

    public abstract int getPriority();/** visszaadja a tárgy prioritását, erre akkor van szükség, amikor két vagy több azonos tárgy található a játékosnál*/

    public int getDurability() {/** hátralévő élettartam lekérdezése*/
        Suttogo.info("getDurability()");
        Suttogo.info("\treturn int");
        return durability;
    }

    public void setDurability(int durability) {/** hátralévő élettartam beállítása*/
        Suttogo.info("setDurability(int)");
        this.durability = durability;
    }

    public abstract boolean decreaseDurability(); /** hátralévő élettartam csökkentése 1 körrel*/

    public abstract boolean isPairable();/** megmondja hogy a tárgy párosítható-e (tranzisztor esetén releváns csak)*/

    public Room getLocation(){/** visszaadja, hogy a tárgy melyik szobában van*/
        Suttogo.info("getLocation()");
        Suttogo.info("\treturn Room");
        return location;
    }

    public void setLocation(Room room){ /** beállítja, hogy melyik szobában található a tárgy*/
        Suttogo.info("setLocation(Room)");
        this.location=room;
    }

    public boolean getActivated() {/* visszaadja, hogy a táegy aktiválva van-e*/
        Suttogo.info("getActivated()");
        return activated;
    }

    public abstract boolean protectFromKill();// ezzel a függvénnyel lehet megkérni a tárgyat, hogy az védje meg használóját a kibukástól

    public abstract boolean protectFromGas();// ezzel a függvénnyel lehet megkérni a tárgyat, hogy az védje meg használóját a mérges gáztól

    public void setOwner(Character character){// ez a függvény állítja be, hogy kinél van a tárgy
        Suttogo.info("setOwner(Character)");
        this.owner = character;
    }
}

