package game.model.entities.items;
import game.model.entities.Character;
import game.model.entities.building.Room;
import game.model.logging.Suttogo;



/**A tárgyak ősosztálya*/

public abstract class Item {
    protected final String id;

    /**Visszaadja az egyedi azonosítót*/
    public String getId() {
        return id;
    }

    /**Visszaadja, hogy a tárgy aktiválva van-e*/
    public boolean isActivated() {
        return activated;
    }
    protected boolean activated;
    protected boolean defensive;
    protected int durability;
    protected Room location;
    protected Character owner;

    /**Egy paraméteres konstruktor*/
    public Item(String id) {
        this.id = id;
    }

    /**Hat paraméteres konstruktor*/
    public Item(String id, boolean activated, boolean defensive, int durability, Room location, Character owner) { //Item konstruktor, alapértékek beállításához
        this.id = id;
        this.activated = activated;
        this.defensive = defensive;
        this.durability = durability;
        this.location = location;
        this.owner = owner;
    }

    // Absztrakt metódusok
    /** Ez a függvény aktiválja a tárgyat, ami a legtöbb tárgynál máshogy működik*/
    public abstract void activate();

    /** Visszaadja a tárgy prioritását, erre akkor van szükség, amikor két vagy több azonos tárgy található a játékosnál*/
    public abstract int getPriority();

    /** Hátralévő élettartam lekérdezése*/
    public int getDurability() {
        return durability;
    }

    /** Hátralévő élettartam beállítása*/
    public void setDurability(int durability) {
        this.durability = durability;
    }

    /** Hátralévő élettartam csökkentése 1 körrel*/
    public abstract boolean decreaseDurability();

    /** Megmondja hogy a tárgy párosítható-e (tranzisztor esetén releváns csak)*/
    public abstract boolean isPairable();

    /** Visszaadja, hogy a tárgy melyik szobában van*/
    public Room getLocation(){

        return location;
    }

    /** Beállítja, hogy melyik szobában található a tárgy*/
    public void setLocation(Room room){
        this.location=room;
    }



    /** Ezzel a függvénnyel lehet megkérni a tárgyat, hogy az védje meg használóját a kibukástól*/
    public boolean protectFromKill() {

        return false;
    }

    /** Ezzel a függvénnyel lehet megkérni a tárgyat, hogy az védje meg használóját a mérges gáztól*/
    public boolean protectFromGas() {

        return false;
    }

    /** Ez a függvény állítja be, hogy kinél van a tárgy*/
    public void setOwner(Character character){
        this.owner = character;
    }

    /**
     * A tárgy gazdája
     */
    public Character getOwner(){

        return owner;
    }

    /**
     * A tranzisztor párja, csak párosított tranzisztorok esetében fontos
     */
    public  Transistor getPair(){ return null;}

    /**
     * A tárgy létrehozásához szükséges command
     */
    public String create(){return null;}

    /**
     * Hanyadikként volt a tárgy létrehozva
     */
    public String getNumID(){return null;}

    /**Visszaadja a specifikus tárgy hatását*/
    public String getEffect() {
        return null;
    }
}

