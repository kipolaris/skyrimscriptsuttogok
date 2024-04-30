package game.model.entities.items;

import game.model.entities.Character;
import game.model.entities.building.Room;
import game.model.logging.Suttogo;

import static game.model.main.Main.gameEngine;

public class AirFreshener extends Item{
    public AirFreshener(boolean activated, boolean defensive, int durability, Room location, Character owner) {
        super("Airfreshener"+gameEngine.getItemID(), activated, defensive, durability, location, owner);
    }

    /** visszaadja a tárgy prioritását, erre akkor van szükség, amikor két vagy több azonos tárgy található a játékosnál*/
    public int getPriority(){
        Suttogo.info("getPriority()");
        Suttogo.info("\treturn -1");
        return -1;
    }

    @Override
    public void activate() { /** ezzel lehet aktiválni a tárgyat, ezután a szoba megtisztul a mérgesgáztól*/
        Suttogo.info("activate()");
        location = owner.getLocation();
        owner = null;
        location.setGassed(false);
        location.setHasAirFreshener();
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
        return "The room gets fresh air.";
    }
}
