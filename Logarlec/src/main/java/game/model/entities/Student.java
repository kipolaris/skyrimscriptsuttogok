package game.model.entities;

import game.model.entities.items.Item;
import game.model.entities.items.Transistor;

//#todo: implement class
public class Student extends Character{
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