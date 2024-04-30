package game.model.commands;

import game.model.entities.Student;
import game.model.entities.items.Item;
import game.model.logging.Suttogo;
import game.model.main.Main;
import java.util.Map;

public class Use implements iCommand{
    @Override
    public void execute(String[] cmd) {
        if(cmd.length < 3) {
            Suttogo.error("Too few arguments!");
            return;
        }
        Map<String, Student> students = Main.gameEngine.getStudents();
        Student student = students.get(cmd[1]);
        Map<String, Item> items = student.getItems();
        Item item = items.get(cmd[2]);
        if(item == null) Suttogo.error("You don’t have an item named like that.");
        else student.useItem(item);
    }

    @Override
    public String getName() {
        return "use";
    }
}
