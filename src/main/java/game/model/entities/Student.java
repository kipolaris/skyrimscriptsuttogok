package game.model.entities;

import game.model.entities.items.Item;
import game.model.entities.items.Transistor;
import game.model.logging.Suttogo;
import game.model.main.GameEngine;
import game.model.main.GameMain;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

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

        PriorityQueue<Item> itemPriorityQueue = new PriorityQueue<>(priorityComparator);

        for (Item i : items.values()) {
            if (i.protectFromKill()) {
                itemPriorityQueue.add(i);
            }
        }

        //ha üres a prioritási sor, nincs professor ellen védő tárgy
        Item chosen = itemPriorityQueue.poll();

        if (chosen == null) {
            ArrayList<Item> all = new ArrayList<>(items.values());
            for(Item item : all) {
                item.setLocation(location);
                location.addItem(item);
                items.remove(item.getId());
            }
            return true;
        } else {
            String s = chosen.getId();
            chosen.decreaseDurability();
            if(s.startsWith("Cup")) {
                ArrayList<Item> itemList = new ArrayList<>(items.values());
                Random r = new Random();
                int indx = r.nextInt(itemList.size());
                if (indx == itemList.size()) indx--;
                itemList.get(indx).setLocation(location);
                location.addItem(itemList.get(indx));
                items.remove(itemList.get(indx).getId());
                gameEngine.notifyEveryone();
            }
        }
        return false;
    }

    /**
     * A hallgató ezáltal tudja meg, hogy van-e aktív tranzisztora, és ha van, akkor az melyik
     */
    public Transistor getActiveTransistor(Transistor t){
        for(Item i : items.values()){
            if(i.isPairable() && !i.equals(t)){
                return (Transistor) i;
            }
        }
        return null;
    }

    @Override
    public void useItem(Item i) {
        if(actions>0) {
            if(!paralyzed) {
                i.activate();
                actions--;
            }else Suttogo.getSuttogo().error("You are paralyzed.");
        } else noMoreActions();
        GameMain.gameEngine.notifyEveryone();
    }
}
