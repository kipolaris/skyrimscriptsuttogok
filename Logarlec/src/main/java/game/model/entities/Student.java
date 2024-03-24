package game.model.entities;

import game.model.entities.items.Item;
import game.model.entities.items.Transistor;

import java.util.PriorityQueue;

public class Student extends Character{

    @Override
    public boolean die() {
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
        //ezt akkor kéne megcsinálni, amikor már a grafikus interfésszel kapcsolatos event kezelés is tálalékon lesz,
        //hiszen egy kör az tulajdonképpen egy loop lesz, eseményekkel.
        //egy állapotgép kéne ide, és egy enum: kezdjük el ezt megvalósítani?
        throw new UnsupportedOperationException();
    }

    public Transistor getActiveTransistor(){
        for(Item i : items){
            if(i.isPairable()){
                return (Transistor) i;
            }
        }
        return null;
    }
}