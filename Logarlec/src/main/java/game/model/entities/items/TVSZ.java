package game.model.entities.items;

import game.model.logging.Suttogo;

public class TVSZ extends Item{

    //Prioritási listán levő pozíciója
    public int getPriority(){
        Suttogo.info("getPriority()");
        Suttogo.info("\treturn 2");
        return 2;
    }

    //Aktiválás esetén
    @Override
    public void activate() {
        Suttogo.info("activate()");
        this.activated = true;
    }

    //Használat esetén csökkenti az élettartamát
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

    //Nem párosítható
    @Override
    public boolean isPairable() {
        Suttogo.info("isPairable()");
        Suttogo.info("\treturn false");
        return false;
    }

    //Meg tud védeni a tanártól, és meg is véd, ha aktiválva van
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

    //Nem véd gáz ellen
    @Override
    public boolean protectFromGas() {
        Suttogo.info("protectFromGas()");
        Suttogo.info("\treturn false");
        return false;
    }
}
