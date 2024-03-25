package game.model.entities.items;

import game.model.entities.Character;
import game.model.entities.building.Room;
import game.model.logging.Suttogo;

public class FFP2 extends Item{
    public FFP2(boolean activated, boolean defensive, int durability, Room location, Character owner) {
        super(activated, defensive, durability, location, owner);
    }
    @Override
    public void activate() {
        Suttogo.info("activate()");
        this.activated = true;
    }

    public int getPriority(){
        Suttogo.info("getPriority()");
        Suttogo.info("\treturn 1");
        return 1;
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
        Suttogo.info("\treturn false");
        return false;
    }

    @Override
    public boolean protectFromGas() {
        Suttogo.info("protectFromGas()");
        if(activated) {
            Suttogo.info("\treturn true");
            return true;
        }
        Suttogo.info("\treturn false");
        return false;
    }
}
