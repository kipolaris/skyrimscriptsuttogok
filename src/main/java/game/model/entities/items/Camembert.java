package game.model.entities.items;

import game.model.entities.Character;
import game.model.entities.building.Room;
import game.model.logging.Suttogo;

import static game.model.main.GameMain.gameEngine;

/**A camembert osztálya*/
public class Camembert extends Item{
    /**Öt paraméteres konstruktor*/
    public Camembert(boolean activated, boolean defensive, int durability, Room location, Character owner) {
        super("Camembert"+gameEngine.getItemID(), activated, defensive, durability, location, owner);
    }

    /** visszaadja a tárgy prioritását, erre akkor van szükség, amikor két vagy több azonos tárgy található a játékosnál*/
    public int getPriority(){
        Suttogo.info("getPriority()");
        Suttogo.info("\treturn -1");
        return -1;
    }

    @Override
    public void activate() { /** ezzel lehet aktiválni a tárgyat, ezután a szoba mérgesgázzal teli lesz*/
        Suttogo.info("activate()");
        Room loc = owner.getLocation();
        loc.setGassed(true);
        durability = 0;
        String s = this.getId() + " used. " + getEffect();
        Suttogo.info(s);
    }

    @Override
    public boolean decreaseDurability() {/** hátralévő élettartam csökkentése 1 körrel*/
        Suttogo.info("decreaseDurability()");
        Suttogo.info("\treturn false");
        return false;
    }

    @Override
    public boolean isPairable() { /** megmondja hogy a tárgy párosítható-e (tranzisztor esetén releváns csak)*/
        Suttogo.info("isPairable()");
        Suttogo.info("\treturn false");
        return false;
    }

    @Override
    public String getEffect() {
        return "The room gets gassed.";
    }
    @Override
    public String create(){
        return "camembert "+durability;
    }
    @Override
    public String getNumID(){
        String uj = getId().replace("Camembert", "");
        return uj;
    }
}
