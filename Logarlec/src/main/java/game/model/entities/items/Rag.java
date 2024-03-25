package game.model.entities.items;

import game.model.entities.Character;
import game.model.entities.building.Room;
import game.model.logging.Suttogo;

public class Rag extends Item{

    /**Konstruktor: létrehozza a tárgyat és inicializálja az értékeit*/
    public Rag(boolean activated, boolean defensive, int durability, Room location, Character owner) {
        super(activated, defensive, durability, location, owner);
    }

    /**Prioritási listán nem számít*/
    public int getPriority(){
        Suttogo.info("getPriority()");
        Suttogo.info("\treturn -1");
        return -1;
    }

    /**Aktiválja a tárgyat, eldobódik, és a tanárokra ezután veszélyes lesz*/
    @Override
    public void activate() {
        Suttogo.info("activate()");
        this.activated = true;
        owner.dropItem(this);
        this.location.paralyzeProfessors();
    }

    /**A tárgy élettartamát csökkenti. Ha aktív a tárgy, akkor körönként egy életet veszt.*/
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

    /**Párosíthatóság lekérdezése. Nem párosítható.*/
    @Override
    public boolean isPairable() {
        Suttogo.info("isPairable()");
        Suttogo.info("\treturn false");
        return false;
    }

    /**Védelem lekérdezése tanárok ellen. Ilyen esetben nem felhasználható.*/
    @Override
    public boolean protectFromKill() {
        Suttogo.info("protectFromKill()");
        Suttogo.info("\treturn false");
        return false;
    }

    /**Védelem lekérdezése gáz ellen. Ilyen esetben nem felhasználható.*/
    @Override
    public boolean protectFromGas() {
        Suttogo.info("protectFromGas()");
        Suttogo.info("\treturn false");
        return false;
    }
}
