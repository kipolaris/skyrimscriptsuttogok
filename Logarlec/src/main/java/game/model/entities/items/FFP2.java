package game.model.entities.items;

import game.model.entities.Character;
import game.model.entities.building.Room;
import game.model.logging.Suttogo;

public class FFP2 extends Item{
    public FFP2(boolean activated, boolean defensive, int durability, Room location, Character owner) {
        super(activated, defensive, durability, location, owner);
    }
    @Override
    public void activate() { /**ezzel lehet aktiválni a maszkot, innentől megvédi használóját a mérges gáztól*/
        Suttogo.info("activate()");
        this.activated = true;
    }

    public int getPriority(){/** visszaadja a tárgy prioritását, erre akkor van szükség, amikor két vagy több azonos tárgy található a játékosnál*/
        Suttogo.info("getPriority()");
        Suttogo.info("\treturn 1");
        return 1;
    }

    @Override
    public boolean decreaseDurability() {/** hátralévő élettartam csökkentése 1 körrel*/
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
    public boolean isPairable() { /** megmondja hogy a tárgy párosítható-e (tranzisztor esetén releváns csak)*/
        Suttogo.info("isPairable()");
        Suttogo.info("\treturn false");
        return false;
    }

    @Override
    public boolean protectFromKill() { /** ezzel a függvénnyel lehet megkérni a tárgyat, hogy az védje meg használóját a kibukástól*/
        Suttogo.info("protectFromKill()");
        Suttogo.info("\treturn false");
        return false;
    }

    @Override
    public boolean protectFromGas() {/** ezzel a függvénnyel lehet megkérni a tárgyat, hogy az védje meg használóját a mérges gáztól*/
        Suttogo.info("protectFromGas()");
        if(activated) {
            Suttogo.info("\treturn true");
            return true;
        }
        Suttogo.info("\treturn false");
        return false;
    }
}
