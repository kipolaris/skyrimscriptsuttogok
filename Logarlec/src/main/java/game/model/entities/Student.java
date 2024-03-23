package game.model.entities;

import game.model.entities.items.Item;
import game.model.entities.items.Transistor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//#todo: implement class
public class Student extends Character{
    private static final Logger logger = LogManager.getLogger();
    @Override
    public void addItem(Item item) {
        //implement
    }

    @Override
    public boolean die() {
        //implement
        return false;
    }

    @Override
    public void doRound() {
        //implement
    }

    public Transistor getActiveTransistor(){
        //implement
        return null;
    }
}