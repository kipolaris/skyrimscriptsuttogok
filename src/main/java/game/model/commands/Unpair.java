package game.model.commands;

import game.model.entities.Student;
import game.model.entities.items.Item;
import game.model.entities.items.Transistor;
import game.model.logging.Suttogo;
import game.model.main.GameMain;

import java.util.Map;

import static game.model.main.GameMain.gameEngine;

/**
 * Parancs osztály párosított tranzisztorok szétválasztására
 */
public class Unpair implements iCommand{
    @Override
    public void execute(String[] cmd) {
        if(cmd.length < 3) {
            gameEngine.getSuttogo().error("Too few arguments!");
            return;
        }
        Map<String, Student> students = GameMain.gameEngine.getStudents();
        Student student = students.get(cmd[1]);
        Map<String, Item> items = student.getItems();
        Item item = items.get(cmd[2]);
        if(item == null) gameEngine.getSuttogo().error("You don’t have an item named like that.");
        else if(item.getId().startsWith("Transistor")) {
            Transistor t = (Transistor) item;
            if (t.isPairable() == true){
                gameEngine.getSuttogo().error("This transistor is already unpaired.");
            }
            else {
                t.unpair();
            }
        }
        else gameEngine.getSuttogo().error("This item is not a Transistor.");
    }

}
