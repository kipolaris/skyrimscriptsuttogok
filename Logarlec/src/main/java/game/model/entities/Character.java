package game.model.entities;

import game.model.entities.building.Door;
import game.model.entities.building.Room;
import game.model.entities.items.*;
import game.model.logging.Suttogo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Character {
    public static int maxInventorySize = 5;
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
        Suttogo.info("getLocation()");
        Suttogo.info("\treturn Room");
        return location;
    }

    public void setLocation(Room location) {
        Suttogo.info("setLocation(Room)");
        this.location = location;
    }

    public boolean getParalyzed() {
        Suttogo.info("getParalyzed()");
        Suttogo.info("\treturn boolean");
        return paralyzed;
    }

    public void useItem(Item i) {
        Suttogo.info("useItem(Item)");
        i.activate();
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        Suttogo.info("addItem(Item)");
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
        Suttogo.info("addItemForTest(Item)");
        items.add(item);
    }

    public void dropItem(Item item) {
        Suttogo.info("dropItem(Item)");
        if(items.contains(item)){
            item.setLocation(location);
            location.addItem(item);
        }
    }

    public void setParalyzed(boolean b) {
        Suttogo.info("setParalyzed(boolean)");
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
        throw new UnsupportedOperationException();
    }

    public Transistor getActiveTransistor() {
        throw new UnsupportedOperationException();
    }

    public boolean die() {
        throw new UnsupportedOperationException();
    }
}
