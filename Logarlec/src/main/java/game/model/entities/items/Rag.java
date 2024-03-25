package game.model.entities.items;

import game.model.logging.Suttogo;

import game.model.logging.Suttogo;

public class Rag extends Item{

    @Override
    public int getPriority(){
        Suttogo.info("getPriority()");
        Suttogo.info("\treturn -1");
        return -1;
    }

    @Override
    public void activate() {
        Suttogo.info("activate()");
        this.activated = true;
        this.location.paralyzeProfessors();
        owner.dropItem(this);
    }

    @Override
    public boolean decreaseDurability(){
        Suttogo.info("decreaseDurability()");
        this.durability--;

        if (this.durability <= 0) {
            Suttogo.info("\treturn false");
            return false;
        }
        Suttogo.info("\treturn true");
        return true;
    }

    @Override
    public boolean isPairable() {
        Suttogo.info("isPairable()");
        Suttogo.info("\treturn false");
        return false;
    }

    @Override
    public boolean protectFromKill() {
        Suttogo.info("protectFromKill()");
        Suttogo.info("\treturn false");
        return false;
    }

    @Override
    public boolean protectFromGas() {
        Suttogo.info("protectFromGas()");
        Suttogo.info("\treturn false");
        return false;
    }
}

