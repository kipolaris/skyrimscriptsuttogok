package game.model.entities.items;

import game.model.logging.Suttogo;

public class TVSZ extends Item{

    @Override
    public int getPriority(){
        Suttogo.info("getPriority()");
        Suttogo.info("\treturn 2");
        return 2;
    }

    @Override
    public void activate() {
        Suttogo.info("activate()");
        this.activated = true;
    }

    @Override
    public boolean decreaseDurability() {
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
        if(this.activated) {
            Suttogo.info("\treturn true");
            return true;
        }
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
