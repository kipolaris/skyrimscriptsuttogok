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

    // Abstract methods
    public abstract void activate();

    public abstract int getPriority();

    public abstract boolean decreaseDurability();

    public abstract boolean isPairable();

    public Room getLocation(){
        Suttogo.info("getLocation()");
        Suttogo.info("\treturn Room");
        return location;
    }

    public void setLocation(Room room){
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

