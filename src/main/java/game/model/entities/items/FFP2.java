package game.model.entities.items;

import game.model.entities.Character;
import game.model.entities.building.Room;
import game.model.logging.Suttogo;

import javax.xml.bind.annotation.XmlElement;

import static game.model.main.GameMain.gameEngine;

/**Az ffp2 maszk osztálya*/
public class FFP2 extends Item{
    @XmlElement
    private boolean fake;

    /**Hat paraméteres konstruktor*/
    public FFP2(boolean activated, boolean defensive, int durability, Room location, Character owner, boolean f) {
        super("FFP2"+gameEngine.getItemID(), activated, defensive, durability, location, owner);
        fake = f;
    }

    @Override
    public void activate() { /**ezzel lehet aktiválni a maszkot, innentől megvédi használóját a mérges gáztól*/
        Suttogo.info("activate()");
        if(!fake) this.activated = true;
        String s = this.getId() + " used. " + getEffect();
        Suttogo.info(s);
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
    public boolean protectFromGas() {/** ezzel a függvénnyel lehet megkérni a tárgyat, hogy az védje meg használóját a mérges gáztól*/
        Suttogo.info("protectFromGas()");
        if(activated) {
            Suttogo.info("\treturn true");
            return true;
        }
        Suttogo.info("\treturn false");
        return false;
    }

    @Override
    public String getEffect() {
        if(fake) return "Wait.. is this a real gasmask?";
        return "Gas has no effect on you now.";
    }
    @Override
    public String create(){
        if (fake) return "ffp2 fake "+durability;
        else return "ffp2 notfake "+durability;
    }
    @Override
    public String getNumID(){
        String uj = getId().replace("FFP2", "");
        return uj;
    }
}
