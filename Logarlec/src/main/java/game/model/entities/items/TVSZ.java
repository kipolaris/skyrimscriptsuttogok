package game.model.entities.items;


import game.model.entities.Character;
import game.model.entities.building.Room;
import game.model.logging.Suttogo;

public class TVSZ extends Item{

    /**
     * Konstruktor: ltrehozza a tárgyat, és inicializálja az értékeit
     */
    public TVSZ(boolean activated, boolean defensive, int durability, Room location, Character owner) {
        super(activated, defensive, durability, location, owner);
    }

    /**
     * A prioritási listán szerepet tölt be, értéke 2
     */
    public int getPriority(){
        Suttogo.info("getPriority()");
        Suttogo.info("\treturn 2");
        return 2;
    }

    /**
     * Aktiválás esetén
     */
    @Override
    public void activate() {
        Suttogo.info("activate()");
        this.activated = true;
    }

    /**
     * Ha hsználjuk, csökken az élettartama eggyel a kezdő 3-ból
     */
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

    /**
     * Nem párosítható
     */
    @Override
    public boolean isPairable() {
        Suttogo.info("isPairable()");
        Suttogo.info("\treturn false");
        return false;
    }

    /**
     * Meg tud védeni a tanártól, de csak akkor, ha aktiválva van
     */
    @Override
    public boolean protectFromKill() {
        Suttogo.info("protectFromKill()");
        if(this.activated) {
            Suttogo.info("\treturn true");
            return true;
        }
        Suttogo.info("\treturn false");
        return false;
    }

    /**
     * Gáz ellen nem tud védeni
     */
    @Override
    public boolean protectFromGas() {
        Suttogo.info("protectFromGas()");
        Suttogo.info("\treturn false");
        return false;
    }
}
