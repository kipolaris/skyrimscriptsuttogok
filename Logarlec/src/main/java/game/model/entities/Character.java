package game.model.entities;

import game.model.entities.building.Door;
import game.model.entities.building.Room;
import game.model.entities.items.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

//#todo: implement class
public class Character {
    public static int maxInventorySize = 5;
    private static final Logger logger = LogManager.getLogger();
    protected boolean paralyzed;

    protected int actions;
    protected Room location;

    protected ArrayList<Item> items = new ArrayList<>();

    /**
     * prioritásKomparátor. Ez valósítja meg, hogy hiearchia szerint használja védelemre
     * a tárgyakat a karakter.
     */
    protected static Comparator<Item> priorityComparator = (o1, o2) -> {
        int priorityCheck = Integer.compare(o1.getPriority(), o2.getPriority());

        if(priorityCheck!=0){
            return priorityCheck;
        }

        return Integer.compare(o1.getDurability(), o1.getDurability());
    };

    public Room getLocation() {
        return location;
    }

    public void setLocation(Room location) {
        this.location = location;
    }


    public void useItem(Item i) {
        i.activate();
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        if(actions>0 && items.size()<maxInventorySize){
            location.removeItem(item);
            items.add(item);
        }
    }

    /**
     * Kérdés nélkül hozzáadja a tárgyat az inventory-hoz.
     * @param item hozzáadandó item
     */
    public void addItemForTest(Item item){
        items.add(item);
    }

    public void dropItem(Item item) {
        if(items.contains(item)){
            item.setLocation(location);
            location.addItem(item);
        }
    }

    public void setParalyzed(boolean b) {
        if(b) {
            PriorityQueue<Item> itemPriorityQueue = new PriorityQueue<>(priorityComparator);

            for (Item i : items) {
                if (i.protectFromGas()) {
                    itemPriorityQueue.add(i);
                }
            }

            //ha üres a prioritási sor, nincs gáz ellen védő tárgy
            Item chosen = itemPriorityQueue.poll();

            if (chosen == null) {
                this.paralyzed = true;
            } else {
                if (!chosen.decreaseDurability()) {
                    items.remove(chosen);
                }
            }
        }else{
            this.paralyzed=false;
        }
    }

    public void setProfessorParalyzed(boolean b) {
        throw new UnsupportedOperationException();
    }

    public void move(Door d) {
        if(d.accept(this, location)){
            Room dest = d.getNeighbour(location);
            dest.addCharacter(this);
        }
    }

    public void skipTurn() {
        throw new UnsupportedOperationException();
    }

    public void doRound() {
        // Method body to be implemented
    }

    public Transistor getActiveTransistor() {
        for(Item i : items){
            if(i.isPairable()){
                return (Transistor) i;
            }
        }
        return null;
    }

    public boolean die() {
        //#todo: ezt talán kiemelni, mert kódduplikáció?
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

}
//end class Character
