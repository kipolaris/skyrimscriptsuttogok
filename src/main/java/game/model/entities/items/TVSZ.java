package game.model.entities.items;


import game.model.entities.Character;
import game.model.entities.building.Room;
import game.model.logging.Suttogo;


import static game.model.main.GameMain.gameEngine;

/**A tvsz osztálya*/
public class TVSZ extends Item{
    boolean fake;

    /**
     * Konstruktor: ltrehozza a tárgyat, és inicializálja az értékeit
     */
    public TVSZ(boolean activated, boolean defensive, int durability, Room location, Character owner, boolean f) {
        super("TVSZ"+gameEngine.getItemID(), activated, defensive, durability, location, owner);
        fake = f;
    }

    /**
     * A prioritási listán szerepet tölt be, értéke 2
     */
    public int getPriority(){
        return 2;
    }

    /**
     * Aktiválás esetén
     */
    @Override
    public void activate() {
        if(!fake) this.activated = true;
        String s = this.getId() + " used. " + getEffect();
        Suttogo.getSuttogo().info(s);
    }

    /**
     * Ha hsználjuk, csökken az élettartama eggyel a kezdő 3-ból
     */
    @Override
    public boolean decreaseDurability() {
        this.durability--;
        if (this.durability <= 0) {
            gameEngine.nullifyItem(this);
            return false;
        }
        return true;
    }

    /**
     * Nem párosítható
     */
    @Override
    public boolean isPairable() {

        return false;
    }

    /**
     * Meg tud védeni a tanártól, de csak akkor, ha aktiválva van
     */
    @Override
    public boolean protectFromKill() {
        if(this.activated) {
            return true;
        }
        return false;
    }

    @Override
    public String getEffect() {
        if(fake) return "Rules have no power here.";
        return "Your life is saved.";
    }

    /**
     * A tárgy létrehozásához szükséges command
     */
    @Override
    public String create(){
        if(fake) return "tvsz fake "+durability;
        else return "tvsz notfake "+durability;
    }

    /**
     * Hanyadikként volt a tárgy létrehozva
     */
    @Override
    public String getNumID(){
        String uj = getId().replace("TVSZ", "");
        return uj;
    }
}
