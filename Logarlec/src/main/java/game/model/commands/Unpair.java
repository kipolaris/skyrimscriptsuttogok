package game.model.commands;

import game.model.entities.Student;
import game.model.entities.items.Item;
import game.model.entities.items.Transistor;
import game.model.logging.Suttogo;
import game.model.main.Main;

import java.util.Map;

import static game.model.main.Main.gameEngine;

public class Unpair implements iCommand{
    @Override
    public void execute(String[] cmd) {
        Map<String, Student> students = Main.gameEngine.getStudents();
        Student student = students.get(cmd[1]);
        Map<String, Item> items = student.getItems();
        Item item = items.get(cmd[2]);
        if(item == null) Suttogo.error("You don’t have an item named like that.");
        else if(item instanceof Transistor) {
            Transistor t = (Transistor) item;
            if (t.isPairable() == true){
                Suttogo.error("This transistor is already unpaired.");
            }
            else {
                t.unpair();
            }
        }
        else Suttogo.error("This item is not a Transistor.");
    }

    @Override
    public String getName() {
        return "unpair";
    }
}
