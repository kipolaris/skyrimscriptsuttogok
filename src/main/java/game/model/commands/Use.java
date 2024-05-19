package game.model.commands;

import game.model.entities.Cleaner;
import game.model.entities.Professor;
import game.model.entities.Student;
import game.model.entities.items.Item;
import game.model.logging.Suttogo;
import game.model.main.GameMain;
import java.util.Map;

/**
 * Parancs osztály tárgyak használatára
 */
public class Use implements iCommand{
    @Override
    public void execute(String[] cmd) {
        if(cmd.length < 3) {
            Suttogo.error("Too few arguments!");
            return;
        }
        if (cmd[1].charAt(0) =='S') {
            Map<String, Student> students = GameMain.gameEngine.getStudents();
            Student student = students.get(cmd[1]);
            Map<String, Item> items = student.getItems();
            Item item = items.get(cmd[2]);
            if (item == null) Suttogo.error("You don’t have an item named like that.");
            else student.useItem(item);
        } else{
            if (cmd[1].charAt(0) =='C') {
                Map<String, Cleaner> cleaners = GameMain.gameEngine.getCleaners();
                Cleaner cleaner = cleaners.get(cmd[1]);
                Map<String, Item> items = cleaner.getItems();
                Item item = items.get(cmd[2]);
                if (item == null) Suttogo.error("You don’t have an item named like that.");
                else cleaner.useItem(item);
            } else{
                Map<String, Professor> professors = GameMain.gameEngine.getProfessors();
                Professor professor = professors.get(cmd[1]);
                Map<String, Item> items = professor.getItems();
                Item item = items.get(cmd[2]);
                if (item == null) Suttogo.error("You don’t have an item named like that.");
                else professor.useItem(item);
            }
        }
    }

}
