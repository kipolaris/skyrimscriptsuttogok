package game.model.entities;

import game.model.entities.building.Door;
import game.model.entities.building.Room;
import game.model.entities.items.Item;
import game.model.main.GameEngine;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Predicate;

import static game.model.main.GameMain.gameEngine;

/**Az oktató osztálya*/
public class Professor extends Character{
    /**Paraméter nélküli konstruktor*/
    public Professor(){
        super("Professor"+ GameEngine.getProfessorID());
        gameEngine.addProfessor(this);
    }

    /**Egy paraméteres konstruktor*/
    public Professor(boolean paralyzed){
        super("Professor"+ GameEngine.getProfessorID());
        this.paralyzed = paralyzed;

        gameEngine.addProfessor(this);
    }

    /**
     * Egy professzor körének menete
     */
    @Override
    public void doRound(){
        boolean attacked = false;
        int targets = location.getStudents().size();
        // Ha van célpont a szobájában, akkor elősször támad
        if(!paralyzed && targets > 0) {
            location.killStudents();
            attacked = true;
        }
        // Ha nincs célpont, vagy sikerült valakit megölnie, tovább lép
        Random rand = new Random();
        if(!paralyzed && (targets==0 || targets != location.getStudents().size())) {
            ArrayList<Door> doors = location.getDoors();
            Door d;
            Room prevloc = location;
            for(int i = 0; i < 15; i++) {
                int indx = rand.nextInt(doors.size());
                if(indx == doors.size()) indx--;
                d = doors.get(indx);
                this.move(d);
                if(prevloc != location) break;
            }
        }
        // Az aktuális helyen megpróbál felvenni egy tárgyat
        // Ha túl sok tárgy van nála, előbb eldob egyet
        ArrayList<Item> locItems = location.getItems();
        ArrayList<Item> profItems = new ArrayList<>(items.values());
        if(!paralyzed && items.size() == maxInventorySize) {
            dropItem(profItems.get(rand.nextInt(items.size() - 1)));
        }
        if(!paralyzed && !locItems.isEmpty()) {
            int itemCount = locItems.size();
            while (itemCount == locItems.size()) {
                if(itemCount == 1) addItem(locItems.get(0));
                else addItem(locItems.get(rand.nextInt(locItems.size() - 1)));
            }
        }
        // Ha ebben a körben még nem támadt és van rá módja, akkor megteszi
        if(!paralyzed && !attacked && !location.getStudents().isEmpty()) {
            location.killStudents();
        }

        gameEngine.next();
    }

    /**Beállítja a paralyzed értékét*/
    @Override
    public void setProfessorParalyzed(boolean b) {
        paralyzed = b;
    }
}
