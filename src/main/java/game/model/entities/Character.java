package game.model.entities;

import game.controller.ModelListener;
import game.model.ObservableModel;
import game.model.entities.building.Door;
import game.model.entities.building.Room;
import game.model.entities.items.*;
import game.model.logging.Suttogo;
import game.model.main.GameMain;

import java.util.*;

/**A karakterek ősosztálya*/
public class Character {
    public boolean isMoved() {
        return isMoved;
    }

    /**
     * ezt csak a Studentek használják
     */
    protected boolean isMoved = false;
    protected final String id;
    public static int maxInventorySize = 5;
    protected boolean paralyzed;

    /**Visszaadja az actions értékét*/
    public int getActions() {
        return actions;
    }

    protected int actions = 3;

    protected Room location;

    protected Map<String, Item> items = new HashMap<>();

    /**Megkeresi és visszaadja egy tárgy kulcsát*/
    public String getKey(Item item) {
        for (Map.Entry<String, Item> entry : items.entrySet()) {
            if (item.equals(entry.getValue())) { return entry.getKey(); }
        }
        return null;
    }

    /**Egy paraméteres konstruktor*/
    public Character(String name){
        id = name;
    }

    /**Visszaadja az egyedi azonosítót*/
    public String getId() {
        return id;
    }

    /**
     * prioritásKomparátor. Ez valósítja meg, hogy hierarchia szerint használja védelemre
     * a tárgyakat a karakter.
     */
    protected static Comparator<Item> priorityComparator = Comparator.comparingInt(Item::getPriority).thenComparingInt(Item::getDurability);

    /**
     * Szoba/karakter tartózkodási helyének lekérdezése
     */
    public Room getLocation() {
        return location;
    }

    /**
     * Tartózkodási hely beállítása
     */
    public void setLocation(Room location) {
        this.location = location;
    }

    /**
     * A karakter bénítottságát kérdezi le
     */
    public boolean getParalyzed() {
        return paralyzed;
    }

    /**
     * Ha egy tárgyat fel akar valaki használni, akkor ez a függvény
     * hívódik meg az adott tárgyra
     */
    public void useItem(Item i) {
        //??
    }

    /**
     * Visszaadja a karakternél levő tárgyakat
     */
    public Map<String, Item> getItems() {
        return items;
    }

    /**
     * Tárgy felvétele
     */
    public void addItem(Item item) {
        if(actions>0) {
            if (!(item.getId().startsWith("Rag") && item.isActivated())) {
                if (items.size() < maxInventorySize) {
                    if(!location.getSticky()) {
                        location.removeItem(item);
                        items.put(item.getId(), item);
                        item.setLocation(null);
                        item.setOwner(this);
                        actions--;
                    } else GameMain.gameEngine.getSuttogo().error("Oh no! The floor is sticky!");
                } else GameMain.gameEngine.getSuttogo().error("Your inventory is full!");
            } else GameMain.gameEngine.getSuttogo().error("This item can't be picked up");
        } else noMoreActions();
        GameMain.gameEngine.notifyEveryone();
    }

    /**
     * Tárgy eldobása
     */
    public void dropItem(Item item) {
        if(actions>0) {
            if(items.containsValue(item)){
                item.setLocation(location);
                location.addItem(item);
                this.items.remove(item.getId());
                actions--;
            }
            else GameMain.gameEngine.getSuttogo().error("There is no such item!");
        }else noMoreActions();
        GameMain.gameEngine.notifyEveryone();
    }

    /**
     * Karakter bénítása, ha nincs gáz ellen védő tárgya
     */
    public void setParalyzed(boolean b) {
        if(b) {
            PriorityQueue<Item> itemPriorityQueue = new PriorityQueue<>(priorityComparator);

            for (Item i : items.values()) {
                if (i.protectFromGas()) {
                    itemPriorityQueue.add(i);
                }
            }

            //ha üres a prioritási sor, nincs gáz ellen védő tárgy
            Item chosen = itemPriorityQueue.poll();

            if (chosen == null) {
                //#todo: check
                this.paralyzed = true;
                GameMain.gameEngine.next();
                GameMain.gameEngine.getSuttogo().error("You have been paralyzed!");
            } else {
                if (!chosen.decreaseDurability()) {
                    items.remove(chosen);
                }
            }
        }else{
            this.paralyzed=false;
        }
    }

    /**
     * Oktató bénítása
     */
    public void setProfessorParalyzed(boolean b) {
        throw new UnsupportedOperationException();
    }

    /**
     * Másik szobába való átlépés
     */
    public void move(Door d) {
        if(!isMoved && d.accept(this, location)){
            Room dest = d.getNeighbour(location);
            if(!dest.addCharacter(this)){
                GameMain.gameEngine.getSuttogo().error("The room is full!");
            }
            isMoved = true;
        }
        else if(isMoved) { GameMain.gameEngine.getSuttogo().error("You have no more energy to move"); }

        //itt meghívjuk a gameengine notifyeveryone-jét, hogy értesítse a szobát, hogy mozgás történt
        GameMain.gameEngine.notifyEveryone();
    }

    /**
     * Passz, üres kör alkalmazása
     */
    public void skipTurn() {
        actions=0;
        isMoved=true;
        GameMain.gameEngine.next();
        GameMain.gameEngine.getSuttogo().info("Turn skipped");
    }

    /**
     * Kör lejátszása
     */
    public void doRound() {
        throw new UnsupportedOperationException();
    }

    /**
     * Aktív tranzisztorok lekérdezése
     */
    public Transistor getActiveTransistor() {
        throw new UnsupportedOperationException();
    }

    /**Elpusztítja a karaktert*/
    public boolean die() {
        throw new UnsupportedOperationException();
    }

    /**Beállítja az actions értékét*/
    public void setActions(int i) {
        actions += i;
        if(actions < 0) actions = 0;
    }

    /**Alaphelyzetbe állítja az akció és mozgás pontokat*/
    public void resetActions(){
        actions = 3;
        isMoved = false;
    }

    /**Hibaüzenetet ad, ha nincs több akciópont*/
    public void noMoreActions(){
        GameMain.gameEngine.getSuttogo().error("You have no more actions!");
    }

    /**
     * Tárgy megsemmisülése
     */
    public void loseItem(Item item) {
        if(items.containsValue(item)){
            this.items.remove(item.getId());
        }
        else GameMain.gameEngine.getSuttogo().error("There is no such item!");
        GameMain.gameEngine.notifyEveryone();
    }
}
