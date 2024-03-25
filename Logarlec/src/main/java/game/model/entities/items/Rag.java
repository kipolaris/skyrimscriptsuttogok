package game.model.entities.items;

import game.model.logging.Suttogo;
g
public class Rag extends Item{

    public int getPriority(){
        return -1;
    }
    @Override
    public void activate() {
        this.activated = true;
        this.location.paralyzeProfessors();
        owner.dropItem(this);
    }

    @Override
    public boolean decreaseDurability(){
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
        return false;
    }
}
