package game.model.entities.items;

import game.model.entities.Character;
import game.model.entities.building.Room;
import game.model.logging.Suttogo;
import game.model.main.GameEngine;

public class SlideRule extends Item{
    /**
     * Konstruktor: létrehozza a tárgyat, inicializálja az értékeit
     */
    public SlideRule(boolean activated, boolean defensive, int durability, Room location, Character owner) {
        super(activated, defensive, durability, location, owner);
    }
    private game.model.main.GameEngine engine;

    /**
     * A prioritási listán való helyét adja vissza. A logarléc nem fontos védelmi szempontból.
     */
    public int getPriority(){
        Suttogo.info("getPriority()");
        Suttogo.info("\treturn -1");
        return -1;
    }

    /**
     * Logarléc aktiválása. Itt a játék vége, mert a hallgatók megnyerték
     */
    @Override
    public void activate() {
        Suttogo.info("activate()");
        //implement
    }

    /**
     * Élettartammal nem rendelkező tárg
     */
    @Override
    public boolean decreaseDurability() {
        Suttogo.info("decreaseDurability()");
        Suttogo.info("\treturn false");
        return false;
    }

    /**
     * Nem párosítható tárgy
     */
    @Override
    public boolean isPairable() {
        Suttogo.info("isPairable()");
        Suttogo.info("\treturn false");
        return false;
    }

    /**
     * Nem tud megvédeni a tanárok gyilkolási szándékai ellen
     */
    @Override
    public boolean protectFromKill() {
        Suttogo.info("protectFromKill()");
        Suttogo.info("\treturn false");
        return false;
    }

    /**
     * Nem tud megvédeni a gáz ellen
     */
    @Override
    public boolean protectFromGas() {
        Suttogo.info("protectFromGas()");
        Suttogo.info("\treturn false");
        return false;
    }

    /**Itt állítható be a tartózkodási helye, hogy éppen melyik szobában vagy kinél van*/
    @Override
    public void setLocation(Room room){
        Suttogo.info("setLocation(Room)");
        if(room==null){
            engine.endGame();
        }
        this.location=room;
    }

    /**A játékhoz való csatolása*/
    public void setGameEngine(GameEngine e){
        Suttogo.info("setGameEngine(GameEngine)");
        this.engine = e;
    }
}
