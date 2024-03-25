package game.model.entities;

import game.model.entities.items.Item;
import game.model.entities.items.Transistor;
import game.model.logging.Suttogo;

import java.util.PriorityQueue;

//#todo: implement class
public class Student extends Character{

    @Override
    public boolean die() {
        Suttogo.info("die()");
        PriorityQueue<Item> itemPriorityQueue = new PriorityQueue<>(priorityComparator);

        for (Item i : items) {
            if (i.protectFromKill()) {
                itemPriorityQueue.add(i);
            }
        }

        //ha üres a prioritási sor, nincs professor ellen védő tárgy
        Item chosen = itemPriorityQueue.poll();

        if (chosen == null) {
            Suttogo.info("return true");
            return true;
        } else {
            if (!chosen.decreaseDurability()) {
                items.remove(chosen);
            }
        }
        Suttogo.info("return false");
        return false;
    }

    @Override
    public void doRound() {
        Suttogo.info("doRound()");
        throw new UnsupportedOperationException();
    }

    public Transistor getActiveTransistor(){
        Suttogo.info("getActiveTransistor()");
        for(Item i : items){
            if(i.isPairable()){
                Suttogo.info("return Transistor");
                return (Transistor) i;
            }
        }
        Suttogo.info("return null");
        return null;
    }
}