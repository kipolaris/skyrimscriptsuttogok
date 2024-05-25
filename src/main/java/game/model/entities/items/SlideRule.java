package game.model.entities.items;

import game.model.entities.Character;
import game.model.entities.building.Room;
import game.model.logging.Suttogo;
import game.model.main.GameEngine;
import game.model.main.GameMain;



import static game.model.main.GameMain.gameEngine;

/**A logarléc osztálya*/
public class SlideRule extends Item{

    boolean fake;
    /**
     * Konstruktor: létrehozza a tárgyat, inicializálja az értékeit
     */
    public SlideRule(boolean activated, boolean defensive, int durability, Room location, Character owner, boolean f) {
        super("SlideRule" + gameEngine.getItemID(), activated, defensive, durability, location, owner);
        fake = f;
        gameEngine.addItem(this);
    }

    /**
     * A prioritási listán való helyét adja vissza. A logarléc nem fontos védelmi szempontból.
     */
    public int getPriority(){

        return -1;
    }

    /**
     * Logarléc aktiválása. Itt a játék vége, mert a hallgatók megnyerték
     */
    @Override
    public void activate() {

    }

    /**
     * Élettartammal nem rendelkező tárg
     */
    @Override
    public boolean decreaseDurability() {

        return false;
    }

    /**
     * Nem párosítható tárgy
     */
    @Override
    public boolean isPairable() {

        return false;
    }

    /**Itt állítható be a tartózkodási helye, hogy éppen melyik szobában vagy kinél van*/
    @Override
    public void setLocation(Room room){

        if(room==null){
            String s = this.getId() + " picked up. " + getEffect();
            Suttogo.info(s);
            if(!fake) gameEngine.endGame();
        }
    }

    @Override
    public String getEffect() {
        if(fake) return "It's only a stick..";
        return "You win!";
    }

    /**
     * A tárgy létrehozásához szükséges command
     */
    @Override
    public String create(){
        if (fake) return "sliderule fake";
        else return "sliderule notfake";
    }

    /**
     * Hanyadikként volt a tárgy létrehozva
     */
    @Override
    public String getNumID(){
        String uj = getId().replace("SlideRule", "");
        return uj;
    }
}
