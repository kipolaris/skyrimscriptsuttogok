package game.model.entities;

import game.model.entities.building.Door;
import game.model.entities.building.Room;
import game.model.entities.items.Item;
import game.model.logging.Suttogo;
import game.model.main.GameEngine;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Predicate;

import static game.model.main.Main.gameEngine;

public class Professor extends Character{

    public Professor(){
        super("Professor"+ GameEngine.getProfessorID());
        gameEngine.addProfessor(this);
    }

    public Professor(boolean paralyzed){
        super("Professor"+ GameEngine.getProfessorID());
        this.paralyzed = paralyzed;

        gameEngine.addProfessor(this);
    }

    /**
     * Egy professzor körének menete
     */
    public void doRound(){
        //Suttogo.info("doRound()");

        Random rand = new Random();

        Predicate<Boolean> p = (a) -> rand.nextInt(2)==1;

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

        for(int i =0 ; i<3; i++){
            if(p.test(true)){
                Item item = items.get(rand.nextInt(items.size()));

                this.addItem(item);
            }
        }

        //#todo: ez is random? vagy ez mindig?
        if(p.test(true)){
            this.location.killStudents();
        }

        gameEngine.next();
    }

    @Override
    public void setProfessorParalyzed(boolean b) {
        paralyzed = b;
    }
}
