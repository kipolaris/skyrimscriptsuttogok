package game.model.entities.items;

import game.model.entities.Character;
import game.model.entities.building.Room;
import game.model.logging.Suttogo;

import static game.model.main.GameMain.gameEngine;

/**A táblatörlő rongy osztálya*/
public class Rag extends Item{

    /**Konstruktor: létrehozza a tárgyat és inicializálja az értékeit*/
    public Rag(boolean activated, boolean defensive, int durability, Room location, Character owner) {
        super("Rag"+gameEngine.getItemID(), activated, defensive, durability, location, owner);
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
        owner.setActions(1);
        this.location.paralyzeProfessors();
        String s = this.getId() + " used. " + getEffect();
        Suttogo.info(s);
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

    @Override
    public String getEffect() {
        return "Professors in the room get paralyzed.";
    }
    /**
     * A tárgy létrehozásához szükséges command
     */
    @Override
    public String create(){
        return "rag "+durability;
    }
    /**
     * Hanyadikként volt a tárgy létrehozva
     */
    @Override
    public String getNumID(){
        String uj = getId().replace("Rag", "");
        return uj;
    }
}
