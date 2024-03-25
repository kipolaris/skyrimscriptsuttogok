package game.model.entities.items;

import game.model.entities.Character;
import game.model.entities.building.Room;
import game.model.logging.Suttogo;
import game.model.main.GameEngine;

public class SlideRule extends Item{
    public SlideRule(boolean activated, boolean defensive, int durability, Room location, Character owner) {
        super(activated, defensive, durability, location, owner);
    }
    private game.model.main.GameEngine engine;

    public int getPriority(){
        Suttogo.info("getPriority()");
        Suttogo.info("\treturn -1");
        return -1;
    }
    @Override
    public void activate() {
        Suttogo.info("activate()");
        //implement
    }

    @Override
    public boolean decreaseDurability() {
        Suttogo.info("decreaseDurability()");
        Suttogo.info("\treturn false");
        return false;
    }

    @Override
    public boolean isPairable() {
        Suttogo.info("isPairable()");
        Suttogo.info("\treturn false");
        return false;
    }

    @Override
    public boolean protectFromKill() {
        Suttogo.info("protectFromKill()");
        Suttogo.info("\treturn false");
        return false;
    }

    @Override
    public boolean protectFromGas() {
        Suttogo.info("protectFromGas()");
        Suttogo.info("\treturn false");
        return false;
    }
    @Override
    public void setLocation(Room room){
        Suttogo.info("setLocation(Room)");
        if(room==null){
            engine.endGame();
        }
        this.location=room;
    }
    public void setGameEngine(GameEngine e){
        Suttogo.info("setGameEngine(GameEngine)");
        this.engine = e;
    }
}
