package game.model.entities.items;

import game.model.logging.Suttogo;

import game.model.logging.Suttogo;

public class Rag extends Item{
    //A prioritási listán nem élvez előnyt
    public int getPriority(){
        Suttogo.info("getPriority()");
        Suttogo.info("\treturn -1");
        return -1;
    }

    //Aktiválja a tárgyat, ezután eldobódik, és a tanárokra ezután s lesz.
    @Override
    public void activate() {
        Suttogo.info("activate()");
        this.activated = true;
        this.location.paralyzeProfessors();
        owner.dropItem(this);
    }

    //A tárgy élettartamát csökkenti. Ha aktív a tárgy, akkor körönként egy életet veszt.
    @Override
    public boolean decreaseDurability(){
        Suttogo.info("decreaseDurability()");
        this.durability--;

        if (this.durability <= 0) {
            Suttogo.info("\treturn false");
            return false;
        }
        Suttogo.info("\treturn true");
        return true;
    }

    //Párosíthatóság lekérdezése. Nem párosítható.
    @Override
    public boolean isPairable() {
        Suttogo.info("isPairable()");
        Suttogo.info("\treturn false");
        return false;
    }

    //Védelem lekérdezése tanárok ellen. Ilyen esetben nem felhasználható.
    @Override
    public boolean protectFromKill() {
        Suttogo.info("protectFromKill()");
        Suttogo.info("\treturn false");
        return false;
    }

    //Védelem lekérdezése gáz ellen. Ilyen esetben nem felhasználható.
    @Override
    public boolean protectFromGas() {
        Suttogo.info("protectFromGas()");
        Suttogo.info("\treturn false");
        return false;
    }
}

