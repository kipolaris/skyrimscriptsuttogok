package game.model.commands;

import game.model.entities.Student;
import game.model.entities.items.Item;
import game.model.entities.items.Transistor;
import game.model.logging.Suttogo;
import game.model.main.Main;

import java.util.Map;

public class Pair implements iCommand{

    @Override
    public void execute(String[] cmd) {
        if(cmd.length < 4) {
            Suttogo.error("Too few arguments!");
            return;
        }
        Map<String, Student> students = Main.gameEngine.getStudents();
        Student student = students.get(cmd[1]);

        Map<String, Item> items = student.getItems();
        Item item1 = items.get(cmd[2]);
        Item item2 = items.get(cmd[3]);

        if(item1 == null || item2 == null) Suttogo.error("You don’t have an item named like that.");

        else if(item1 instanceof Transistor && item2 instanceof Transistor) {
            Transistor t1 = (Transistor) item1;
            Transistor t2 = (Transistor) item2;


            if (t1.isPairable() == false || t2.isPairable() == false){
                Suttogo.error("This transistor is already paired.");
            }
            else {
                t1.pair(t2);
            }
        }
        else Suttogo.error("This item is not a Transistor.");
    }

    @Override
    public String getName() {
        return "pair";
    }
}
