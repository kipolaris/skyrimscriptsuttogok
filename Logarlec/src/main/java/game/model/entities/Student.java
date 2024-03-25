package game.model.entities;

import game.model.entities.items.Item;
import game.model.entities.items.Transistor;
import game.model.logging.Suttogo;

import java.util.PriorityQueue;

public class Student extends Character{

    @Override
    public boolean die() {
        Suttogo.info("die()");
        Suttogo.info("\treturn boolean");
        PriorityQueue<Item> itemPriorityQueue = new PriorityQueue<>(priorityComparator);

        for (Item i : items) {
            if (i.protectFromKill()) {
                itemPriorityQueue.add(i);
            }
        }

        //ha üres a prioritási sor, nincs professor ellen védő tárgy
        Item chosen = itemPriorityQueue.poll();

        if (chosen == null) {
            return true;
        } else {
            if (!chosen.decreaseDurability()) {
                items.remove(chosen);
            }
        }
        return false;
    }

    @Override
    public void doRound() {
        Suttogo.info("doRound()");
        //ezt akkor kéne megcsinálni, amikor már a grafikus interfésszel kapcsolatos event kezelés is tálalékon lesz,
        //hiszen egy kör az tulajdonképpen egy loop lesz, eseményekkel.
        //egy állapotgép kéne ide, és egy enum: kezdjük el ezt megvalósítani?
        throw new UnsupportedOperationException();
    }

    public Transistor getActiveTransistor(){
        Suttogo.info("getActiveTransistor()");
        Suttogo.info("\treturn Transistor");
        for(Item i : items){
            if(i.isPairable()){
                return (Transistor) i;
            }
        }
        return null;
    }
}