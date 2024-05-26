package game.model.entities.items;

import game.model.entities.Character;
import game.model.entities.building.Room;
import game.model.logging.Suttogo;

import static game.model.main.GameMain.gameEngine;

/**A sörös poharak osztálya*/
public class Cups extends Item{
    /**Öt paraméteres konstruktor*/
    public Cups(boolean activated, boolean defensive, int durability, Room location, Character owner) {
        super("Cups"+gameEngine.getItemID(), activated, defensive, durability, location, owner);
    }

    public int getPriority(){/** visszaadja a tárgy prioritását, erre akkor van szükség, amikor két vagy több azonos tárgy található a játékosnál*/

        return 1;
    }

    @Override
    public void activate() {/** ezzel lehet aktiválni a tárgyat, innentől a tárgy megvédi használóját a csúnya, gonosz, rosszakaró, hamis oktatóktól*/
        this.activated = true;
        String s = this.getId() + " used. " + getEffect();
        gameEngine.getSuttogo().info(s);
    }

    @Override
    public boolean decreaseDurability() {/** hátralévő élettartam csökkentése 1 körrel*/
        this.durability--;
        if (this.durability <= 0) {
            gameEngine.nullifyItem(this);
            return false;
        }
        return true;
    }

    @Override
    public boolean isPairable() { /** megmondja hogy a tárgy párosítható-e (tranzisztor esetén releváns csak)*/
        return false;
    }

    @Override
    public boolean protectFromKill() {/** ezzel a függvénnyel lehet megkérni a tárgyat, hogy az védje meg használóját a kibukástól*/

        if(this.activated){
            return true;
        }
        return false;
    }

    @Override
    public String getEffect() {
        return "You feel much stronger, but you feel a bit dizzy.";
    }

    /**
     * A tárgy létrehozásához szükséges command
     */
    @Override
    public String create(){
        return "cups "+durability;
    }

    /**
     * Hanyadikként volt a tárgy létrehozva
     */
    @Override
    public String getNumID(){
        String uj = getId().replace("Cups", "");
        return uj;
    }
}
