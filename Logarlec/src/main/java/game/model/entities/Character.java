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
        Suttogo.info("priorityComparator(Item, Item)");
        Suttogo.info("\treturn Comparator<Item>");
        int priorityCheck = Integer.compare(o1.getPriority(), o2.getPriority());

        if(priorityCheck!=0){
            return priorityCheck;
        }

        return Integer.compare(o1.getDurability(), o1.getDurability());
    };

    //Szoba/karakter tartózkodási helyének lekérdezése
    public Room getLocation() {
        Suttogo.info("getLocation()");
        Suttogo.info("\treturn Room");
        return location;
    }

    //Tartózkodási hely beállítása
    public void setLocation(Room location) {
        Suttogo.info("setLocation(Room)");
        this.location = location;
    }

    //A karakter bénítottságát kérdezi le
    public boolean getParalyzed() {
        Suttogo.info("getParalyzed()");
        Suttogo.info("\treturn boolean");
        return paralyzed;
    }

    //Ha egy tárgyat fel akar valaki használni, akkor ez a függvény
    //hívódik meg az adott tárgyra
    public void useItem(Item i) {
        Suttogo.info("useItem(Item)");
        i.activate();
    }

    //Visszaadja a karakternél levő tárgyakat
    public ArrayList<Item> getItems() {
        Suttogo.info("getItems()");
        Suttogo.info("\treturn ArrayList<Item>");
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

    //Tárgy eldobása
    public void dropItem(Item item) {
        Suttogo.info("dropItem(Item)");
        if(items.contains(item)){
            item.setLocation(location);
            location.addItem(item);
        }
    }

    //Karakter bénítása, ha nincs gáz ellen védő tárgya
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
        Suttogo.info("setProfessorParalyzed(boolean)");
        throw new UnsupportedOperationException();
    }

    //Másik szobába való átlépés
    public void move(Door d) {
        Suttogo.info("move(Door)");
        if(d.accept(this, location)){
            Room dest = d.getNeighbour(location);
            dest.addCharacter(this);
        }
    }

    //Passz, üres kör alkalmazása
    public void skipTurn() {
        Suttogo.info("skipTurn()");
        throw new UnsupportedOperationException();
    }

    //Kör lejátszása
    public void doRound() {
        Suttogo.info("doRound()");
        throw new UnsupportedOperationException();
    }

    //Aktív tranzisztorok lekérdezése
    public Transistor getActiveTransistor() {
        Suttogo.info("getActiveTransistor()");
        Suttogo.info("\treturn Transistor");
        throw new UnsupportedOperationException();
    }

    public boolean die() {
        Suttogo.info("die()");
        Suttogo.info("\treturn boolean");
        throw new UnsupportedOperationException();
    }
}
