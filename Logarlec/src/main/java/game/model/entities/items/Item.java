package game.model.entities.items;
import game.model.entities.Character;
import game.model.entities.building.Room;

//#todo: implement class
public abstract class Item {
    protected boolean activated;
    protected boolean defensive;

    protected int durability;
    protected Room location;
    protected Character owner;

    // Abstract methods
    public abstract void activate();

    //#todo: discuss with team
    public abstract int getPriority();

    public abstract boolean decreaseDurability();

    public abstract boolean isPairable();

    public Room getLocation(){
        return location;
    }

    public void setLocation(Room room){
        this.location=room;
    }

    public boolean getActivated() { return activated; }

    public abstract boolean protectFromKill();

    public abstract boolean protectFromGas();

    public void setOwner(Character character){
        this.owner = character;
    }

    public int getDurability(){
        return durability;
    }
}

