package game.model.entities;

import game.model.entities.building.Door;
import game.model.entities.building.Room;
import game.model.entities.items.Item;
import game.model.logging.Suttogo;
import game.model.main.GameEngine;

import java.util.ArrayList;
import java.util.Random;

import static game.model.main.GameMain.gameEngine;

/**A takarító osztálya*/
public class Cleaner extends Character {
    /**Paraméter nélküli konstruktor*/
    public Cleaner(){
        super("Cleaner"+ GameEngine.getCleanerID());
        gameEngine.addCleaner(this);
    }

    /**Egy paraméteres konstruktor*/
    public Cleaner(boolean paralyzed){
        super("Cleaner"+ GameEngine.getCleanerID());
        gameEngine.addCleaner(this);

        this.paralyzed = paralyzed;
    }

    @Override
    public void doRound(){
        Random rand = new Random();

        ArrayList<Door> doors = this.location.getDoors();

        Door d;

        Room prevloc = location;

        int timer = 0;

        while(prevloc == location && timer < 0.5 * doors.size()) {
            d = doors.get(rand.nextInt(doors.size()));
            this.move(d);
            timer++;
        }

        ArrayList<Item> items = this.location.getItems();

        if(!items.isEmpty()){
            Item i = items.get(rand.nextInt(items.size()));

            this.addItem(i);
        }

        gameEngine.next();

    }

    @Override
    public void move(Door d){
        Suttogo.info("move(Door)");

        if(d.accept(this, location)){
            Room dest = d.getNeighbour(location);
            if(dest.addCharacter(this)){
                this.location.removeCharacter(this);
                this.location = dest;

                if(!this.location.isWasCleaned()){
                    location.setWasCleaned();
                    location.sendOut();
                }
                if(location.getGassed()){
                    location.setGassed(false);
                }
            }
        }
    }

}
