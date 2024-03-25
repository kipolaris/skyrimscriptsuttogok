package game.model.entities.items;

import game.model.entities.Character;
import game.model.entities.building.Room;
import game.model.logging.Suttogo;

public class Camembert extends Item{

    public Camembert(boolean activated, boolean defensive, int durability, Room location, Character owner) {
        super(activated, defensive, durability, location, owner);
    }
    public int getPriority(){// visszaadja a tárgy prioritását, erre akkor van szükség, amikor két vagy több azonos tárgy található a játékosnál


        Suttogo.info("getPriority()");
        Suttogo.info("\treturn -1");
        return -1;
    }

    @Override
    public void activate() { // ezzel lehet aktiválni a tárgyat, ezután a szoba mérgesgázzal teli lesz

        Suttogo.info("activate()");
        Room loc = owner.getLocation();
        loc.setGassed(true);
        durability = 0;
    }

    @Override
    public boolean decreaseDurability() {// hátralévő élettartam csökkentése 1 körrel
        Suttogo.info("decreaseDurability()");
        Suttogo.info("\treturn false");
        return false;
    }

    @Override
    public boolean isPairable() { // megmondja hogy a tárgy párosítható-e (tranzisztor esetén releváns csak)
        Suttogo.info("isPairable()");
        Suttogo.info("\treturn false");
        return false;
    }

    @Override
    public boolean protectFromKill() {// ezzel a függvénnyel lehet megkérni a tárgyat, hogy az védje meg használóját a kibukástól
        Suttogo.info("protectFromKill()");
        Suttogo.info("\treturn false");
        return false;
    }

    @Override
    public boolean protectFromGas() {// ezzel a függvénnyel lehet megkérni a tárgyat, hogy az védje meg használóját a mérges gáztól
        Suttogo.info("protectFromGas()");
        Suttogo.info("\treturn false");
        return false;
    }
}
