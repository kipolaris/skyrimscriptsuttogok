package game.model.entities.items;

import game.model.entities.Character;
import game.model.entities.building.Room;

import static game.model.main.GameMain.gameEngine;

/**A camembert osztálya*/
public class Camembert extends Item{
    /**Öt paraméteres konstruktor*/
    public Camembert(boolean activated, boolean defensive, int durability, Room location, Character owner) {
        super("Camembert"+gameEngine.getItemID(), activated, defensive, durability, location, owner);
    }

    /** visszaadja a tárgy prioritását, erre akkor van szükség, amikor két vagy több azonos tárgy található a játékosnál*/
    public int getPriority(){
        return -1;
    }

    @Override
    public void activate() { /** ezzel lehet aktiválni a tárgyat, ezután a szoba mérgesgázzal teli lesz*/
        Room loc = owner.getLocation();
        loc.setGassed(true);
        String s = this.getId() + " used. " + getEffect();
        gameEngine.getSuttogo().info(s);
        gameEngine.nullifyItem(this);
    }

    @Override
    public boolean isPairable() { /** megmondja hogy a tárgy párosítható-e (tranzisztor esetén releváns csak)*/

        return false;
    }

    @Override
    public String getEffect() {
        return "The room gets gassed.";
    }

    /**
     * A tárgy létrehozásához szükséges command
     */
    @Override
    public String create(){
        return "camembert "+durability;
    }

    /**
     * Hanyadikként volt a tárgy létrehozva
     */
    @Override
    public String getNumID(){
        String uj = getId().replace("Camembert", "");
        return uj;
    }
}
