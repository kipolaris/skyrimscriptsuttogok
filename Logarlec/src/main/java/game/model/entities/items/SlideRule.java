package game.model.entities.items;

import game.model.entities.Character;
import game.model.entities.building.Room;
import game.model.main.GameEngine;

//#todo: implement class
public class SlideRule extends Item{

    private game.model.main.GameEngine engine;

    public int getPriority(){
        return -1;
    }
    @Override
    public void activate() {
        //implement
    }

    @Override
    public boolean decreaseDurability() {
        return false;
    }

    @Override
    public boolean isPairable() {
        return false;
    }

    @Override
    public boolean protectFromKill() {
        return false;
    }

    @Override
    public boolean protectFromGas() {
        return false;
    }
    @Override
    public void setLocation(Room room){
        if(room==null){
            engine.endGame();
        }
        this.location=room;
    }
    public void setGameEngine(GameEngine e){
        this.engine = e;
    }
}
