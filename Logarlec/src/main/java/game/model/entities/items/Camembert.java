package game.model.entities.items;

import game.model.entities.Character;
import game.model.entities.building.Room;

public class Camembert extends Item{

    public Camembert(boolean activated, boolean defensive, int durability, Room location, Character owner) {
        super(activated, defensive, durability, location, owner);
    }
    public int getPriority(){
        return -1;
    }

    @Override
    public void activate() {
        location.setGassed(true);
        durability = 0;
    }

    @Override
    public boolean decreaseDurability() {
        return false;
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
        return false;
    }
}
