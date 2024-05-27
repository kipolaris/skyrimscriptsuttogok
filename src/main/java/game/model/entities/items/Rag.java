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

        return -1;
    }

    /**Aktiválja a tárgyat, eldobódik, és a tanárokra ezután veszélyes lesz*/
    @Override
    public void activate() {
        this.activated = true;
        owner.dropItem(this);
        owner.addActions(1);
        this.location.paralyzeProfessors(true);
        String s = this.getId() + " used. " + getEffect();
        Suttogo.getSuttogo().info(s);
    }

    /**A tárgy élettartamát csökkenti. Ha aktív a tárgy, akkor körönként egy életet veszt.*/
    @Override
    public boolean decreaseDurability(){
        this.durability--;
        if (this.durability <= 0) {
            this.location.paralyzeProfessors(false);
            gameEngine.nullifyItem(this);
            return false;
        }
        return true;
    }

    /**Párosíthatóság lekérdezése. Nem párosítható.*/
    @Override
    public boolean isPairable() {

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
