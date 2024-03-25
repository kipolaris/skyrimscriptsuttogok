package game.model.entities;

import game.model.entities.items.Item;
import game.model.entities.items.Transistor;

import java.util.PriorityQueue;

//#todo: implement class
public class Student extends Character{

    //Ha a hallgatót egy tanár megpróbálja megölni. Itt esik sor a védelmi tárgyak
    //leellenőrzésére, és ha nincs, akkor a hallgató meghal
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

    //Hallgató egy köre
    @Override
    public void doRound() {
        //ezt akkor kéne megcsinálni, amikor már a grafikus interfésszel kapcsolatos event kezelés is tálalékon lesz,
        //hiszen egy kör az tulajdonképpen egy loop lesz, eseményekkel.
        //egy állapotgép kéne ide, és egy enum: kezdjük el ezt megvalósítani?
        throw new UnsupportedOperationException();
    }

    //Ha van egy tranzisztora a hallgatónak, ezáltal tudja azt lekérdezni
    public Transistor getActiveTransistor(){
        for(Item i : items){
            if(i.isPairable()){
                return (Transistor) i;
            }
        }
        return null;
    }
}