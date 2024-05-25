package game.model.entities.items;

import game.model.entities.Character;
import game.model.entities.building.Room;
import game.model.logging.Suttogo;

import static game.model.main.GameMain.gameEngine;

/**A légfrissítő osztálya*/
public class AirFreshener extends Item{
    /**Öt paraméteres konstruktor*/
    public AirFreshener(boolean activated, boolean defensive, int durability, Room location, Character owner) {
        super("Airfreshener"+gameEngine.getItemID(), activated, defensive, durability, location, owner);
    }

    /** visszaadja a tárgy prioritását, erre akkor van szükség, amikor két vagy több azonos tárgy található a játékosnál*/
    public int getPriority(){
        return -1;
    }

    @Override
    public void activate() { /** ezzel lehet aktiválni a tárgyat, ezután a szoba megtisztul a mérgesgáztól*/
        location = owner.getLocation();
        owner = null;
        location.setGassed(false);
        location.setHasAirFreshener();
        String s = this.getId() + " used. " + getEffect();
        Suttogo.info(s);
    }

    @Override
    public boolean decreaseDurability() {/** hátralévő élettartam csökkentése 1 körrel*/

        return false;
    }

    @Override
    public boolean isPairable() { /** megmondja hogy a tárgy párosítható-e (tranzisztor esetén releváns csak)*/

        return false;
    }

    @Override
    public String getEffect() {
        return "The room gets fresh air.";
    }

    /**
     * A tárgy létrehozásához szükséges command
     */
    @Override
    public String create(){
        return "airfreshener";
    }

    /**
     * Hanyadikként volt a tárgy létrehozva
     */
    @Override
    public String getNumID(){
        String uj = getId().replace("Airfreshener", "");
        return uj;
    }
}
