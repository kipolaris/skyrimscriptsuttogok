package game.model.entities.items;


import game.model.entities.Character;
import game.model.entities.building.Room;

public class TVSZ extends Item{
    public TVSZ(boolean activated, boolean defensive, int durability, Room location, Character owner) {
        super(activated, defensive, durability, location, owner);
    }
    public int getPriority(){
        return 2;
    }
    @Override
    public void activate() {
        this.activated = true;
    }

    @Override
    public boolean decreaseDurability() {
        this.durability--;

        if (this.durability <= 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isPairable() {
        return false;
    }

    @Override
    public boolean protectFromKill() {
        if(this.activated) {
            return true;
        }
        return false;
    }

    @Override
    public boolean protectFromGas() {
        return false;
    }
}
