package game.model.entities.items;

import game.model.entities.Character;
import game.model.entities.building.Room;
import game.model.logging.Suttogo;

import static game.model.main.GameMain.gameEngine;

public class Cups extends Item{

    public Cups(boolean activated, boolean defensive, int durability, Room location, Character owner) {
        super("Cups"+gameEngine.getItemID(), activated, defensive, durability, location, owner);
    }
    public int getPriority(){/** visszaadja a tárgy prioritását, erre akkor van szükség, amikor két vagy több azonos tárgy található a játékosnál*/

        Suttogo.info("getPriority()");
        Suttogo.info("\treturn 1");
        return 1;
    }
    @Override
    public void activate() {/** ezzel lehet aktiválni a tárgyat, innentől a tárgy megvédi használóját a csúnya, gonosz, rosszakaró, hamis oktatóktól*/

        Suttogo.info("activate()");
        this.activated = true;
        String s = this.getId() + " used. " + getEffect();
        Suttogo.info(s);
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
    public boolean protectFromKill() {/** ezzel a függvénnyel lehet megkérni a tárgyat, hogy az védje meg használóját a kibukástól*/
        Suttogo.info("protectFromKill()");
        if(this.activated){
            Suttogo.info("\treturn true");
            return true;
        }
        Suttogo.info("\treturn false");
        return false;
    }

    @Override
    public String getEffect() {
        return "You feel much stronger, but you feel a bit dizzy.";
    }
}