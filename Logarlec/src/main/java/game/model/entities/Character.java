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

    //Szoba/helyszín lekérdezése
    public Room getLocation() {
        Suttogo.info("getLocation()");
        Suttogo.info("\treturn Room");
        return location;
    }

    //Tartózkodási szoba beállítása
    public void setLocation(Room location) {
        Suttogo.info("setLocation(Room)");
        this.location = location;
    }

    //A karakter paralizáltságát mutatja meg
    public boolean getParalyzed() {
        Suttogo.info("getParalyzed()");
        Suttogo.info("\treturn boolean");
        return paralyzed;
    }

    //Ha egy tárgyat fel akar valaki használni, akkor ez a függvény azt lekezeli
    public void useItem(Item i) {
        Suttogo.info("useItem(Item)");
        i.activate();
    }

    //Visszaadja a karakternél levő tárgyakat
    public ArrayList<Item> getItems() {
        return items;
    }

    //Tárgy felvétele
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

    //Tárgy eldobása
    public void dropItem(Item item) {
        Suttogo.info("dropItem(Item)");
        if(items.contains(item)){
            item.setLocation(location);
            location.addItem(item);
        }
    }

    //A karakter paralizáltságának beállítása
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

    //Oktató bénítása
    public void setProfessorParalyzed(boolean b) {
        throw new UnsupportedOperationException();
    }

    public void move(Door d) {
        if(d.accept(this, location)){
            Room dest = d.getNeighbour(location);
            dest.addCharacter(this);
        }
    }

    //Passzolás
    public void skipTurn() {
        throw new UnsupportedOperationException();
    }

    //Kör lejátszása
    public void doRound() {
        throw new UnsupportedOperationException();
    }

    //Aktív tranzisztor meghatározása
    public Transistor getActiveTransistor() {
        throw new UnsupportedOperationException();
    }

    //Így nem lehet meghalni csak úgy
    public boolean die() {
        throw new UnsupportedOperationException();
    }
}
