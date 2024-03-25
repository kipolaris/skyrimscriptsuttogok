package game.model.entities.items;

import game.model.entities.Character;
import game.model.entities.building.Room;

public class Cups extends Item{

    public Cups(boolean activated, boolean defensive, int durability, Room location, Character owner) {
        super(activated, defensive, durability, location, owner);
    }
    public int getPriority(){
        return 1;
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
        if(this.activated){
            return true;
        }
        return false;
    }

    @Override
    public boolean protectFromGas() {
        return false;
    }
}
