package game.model.entities.items;
import game.model.entities.Character;
import game.model.entities.building.Room;

import game.model.logging.Suttogo;
public abstract class Item {
    protected boolean activated;
    protected boolean defensive;
    protected int durability;
    protected Room location;
    protected Character owner;

    public Item() {}

    public Item(boolean activated, boolean defensive, int durability, Room location, Character owner) {
        this.activated = activated;
        this.defensive = defensive;
        this.durability = durability;
        this.location = location;
        this.owner = owner;
    }

    // Abstract methods
    public abstract void activate();

    public abstract int getPriority();

    public void setDurability(int durability) {
        Suttogo.info("setDurability(int)");
        this.durability = durability;
    }

    public abstract boolean decreaseDurability();

    public abstract boolean isPairable();

    public Room getLocation(){
        Suttogo.info("getLocation()");
        Suttogo.info("\treturn Room");
        return location;
    }

    public void setLocation(Room room){
        Suttogo.info("setLocation(Room)");
        this.location=room;
    }

    public boolean getActivated() {
        Suttogo.info("getActivated()");
        Suttogo.info("\treturn boolean");
        return activated;
    }

    public int getDurability(){
        Suttogo.info("getDurability()");
        Suttogo.info("\treturn int");
        return durability;
    }

    public abstract boolean protectFromKill();

    public abstract boolean protectFromGas();

    public void setOwner(Character character){
        Suttogo.info("setOwner(Character)");
        Suttogo.info("\treturn Character");
        this.owner = character;
    }
}

