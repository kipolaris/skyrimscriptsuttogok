package game.model.entities.items;
import game.model.entities.building.Room;
import game.model.main.GameEngine;

import game.model.logging.Suttogo;

public class SlideRule extends Item{
    private GameEngine engine;
    //A prioritási listán nem élvez előnyt
    public int getPriority(){
        Suttogo.info("getPriority()");
        Suttogo.info("\treturn -1");
        return -1;
    }

    //A tárgy felvételekor hívódik meg a függvény. Ekkor a hallgatók megnyerték a játékot..
    @Override
    public void activate() {
        Suttogo.info("activate()");
        // Implementation details here
    }

    //Élettartama nincs
    @Override
    public boolean decreaseDurability() {
        Suttogo.info("decreaseDurability()");
        Suttogo.info("\treturn false");
        return false;
    }

    //Nem párosítható
    @Override
    public boolean isPairable() {
        Suttogo.info("isPairable()");
        Suttogo.info("\treturn false");
        return false;
    }

    //Nem tud megvédeni tanártól
    @Override
    public boolean protectFromKill() {
        Suttogo.info("protectFromKill()");
        Suttogo.info("\treturn false");
        return false;
    }

    //Nem tud megvédeni gáztól
    @Override
    public boolean protectFromGas() {
        Suttogo.info("protectFromGas()");
        Suttogo.info("\treturn false");
        return false;
    }

    @Override
    public void setLocation(Room room){
        Suttogo.info("setLocation(Room)");
        if(room == null){
            engine.endGame();
        }
        this.location = room;
    }

    public void setGameEngine(GameEngine e){
        Suttogo.info("setGameEngine(GameEngine)");
        this.engine = e;
    }
}
