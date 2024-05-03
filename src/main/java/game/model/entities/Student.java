package game.model.entities;

import game.model.entities.items.Item;
import game.model.entities.items.Transistor;
import game.model.logging.Suttogo;
import game.model.main.GameEngine;

import java.util.PriorityQueue;

import static game.model.main.GameMain.gameEngine;

/**A hallgató osztálya*/
public class Student extends Character{
    /**Paraméter nélküli konstruktor*/
    public Student() {
        super("Student" + GameEngine.getStudentID());
        gameEngine.addStudent(this);
    }

    /**Egy paraméteres konstruktor*/
    public Student(boolean paralyzed){
        super("Student" + GameEngine.getStudentID());
        this.paralyzed = paralyzed;
        gameEngine.addStudent(this);
    }
    /**
     * Ha a hallgatót egy tanár megpróbálja megölni. Itt esik sor a védelmi tárgyak
     *  leellenőrzésére, és ha nincs, akkor a hallgató meghal
     */
    @Override
    public boolean die() {
        Suttogo.info("die()");

        PriorityQueue<Item> itemPriorityQueue = new PriorityQueue<>(priorityComparator);

        for (Item i : items.values()) {
            if (i.protectFromKill()) {
                itemPriorityQueue.add(i);
            }
        }

        //ha üres a prioritási sor, nincs professor ellen védő tárgy
        Item chosen = itemPriorityQueue.poll();

        if (chosen == null) {
            Suttogo.info("\treturn true");
            return true;
        } else {
            if (!chosen.decreaseDurability()) {
                items.remove(chosen);
            }
        }
        Suttogo.info("\treturn false");
        return false;
    }

    /**
     * Hallgató egy köre
     */
    @Override
    public void doRound() {
        Suttogo.info("doRound()");
        //NEM HASZNÁLJUK
        throw new UnsupportedOperationException();
    }

    /**
     * A hallgató ezáltal tudja meg, hogy van-e aktív tranzisztora, és ha van, akkor az melyik
     */
    public Transistor getActiveTransistor(){
        Suttogo.info("getActiveTransistor()");
        Suttogo.info("\treturn Transistor");
        for(Item i : items.values()){
            if(i.isPairable()){
                return (Transistor) i;
            }
        }
        return null;
    }

    @Override
    public void useItem(Item i) {
        if(actions>0) {
            i.activate();
            i.decreaseDurability();
            actions--;
        } else noMoreActions();
    }
}
