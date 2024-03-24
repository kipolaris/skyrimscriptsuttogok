package game.model.entities.items;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//#todo: implement class
public class Transistor extends Item{

    public int getPriority(){
        return -1;
    }

    private static final Logger logger = LogManager.getLogger();
    @Override
    public void activate() {

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

    public void pair(Transistor t){
        //implement
    }

    public void unpair(){
        //implement
    }
}
