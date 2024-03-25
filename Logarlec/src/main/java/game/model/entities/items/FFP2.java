package game.model.entities.items;

import game.model.entities.Character;
import game.model.entities.building.Room;

public class FFP2 extends Item{
    public FFP2(boolean activated, boolean defensive, int durability, Room location, Character owner) {
        super(activated, defensive, durability, location, owner);
    }
    @Override
    public void activate() {
        this.activated = true;
    }

    public int getPriority(){
        return 1;
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
        return false;
    }

    @Override
    public boolean protectFromGas() {
        if(activated) {
            return true;
        }
        return false;
    }
}
