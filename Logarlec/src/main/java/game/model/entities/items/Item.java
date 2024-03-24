package game.model.entities.items;
import game.model.entities.Character;
import game.model.entities.building.Room;

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

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) { this.durability = durability; }

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

