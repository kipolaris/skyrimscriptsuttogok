package game.model.entities.items;

import game.model.logging.Suttogo;

public class Camembert extends Item{

    @Override
    public int getPriority(){
        Suttogo.info("getPriority()");
        Suttogo.info("return -1");
        return -1;
    }

    @Override
    public void activate() {
        Suttogo.info("activate()");
        location.setGassed(true);
        durability = 0;
    }

    @Override
    public boolean decreaseDurability() {
        Suttogo.info("decreaseDurability()");
        Suttogo.info("\treturn false");
        return false;
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

