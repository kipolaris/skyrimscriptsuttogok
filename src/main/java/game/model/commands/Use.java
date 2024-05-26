package game.model.commands;

import game.model.entities.Cleaner;
import game.model.entities.Professor;
import game.model.entities.Student;
import game.model.entities.items.Item;
import game.model.logging.Suttogo;
import game.model.main.GameMain;
import java.util.Map;

import static game.model.main.GameMain.gameEngine;

/**
 * Parancs osztály tárgyak használatára
 */
public class Use implements iCommand{
    @Override
    public void execute(String[] cmd) {
        if(cmd.length < 3) {
            gameEngine.getSuttogo().error("Too few arguments!");
            return;
        }
        /**
         * Hallgatók esetén
         */
        if (cmd[1].charAt(0) =='S') {
            Map<String, Student> students = gameEngine.getStudents();
            Student student = students.get(cmd[1]);
            Map<String, Item> items = student.getItems();
            Item item = items.get(cmd[2]);
            if (item == null) gameEngine.getSuttogo().error("You don’t have an item named like that.");
            else student.useItem(item);
        } else{
            /**
             * Takarítók esetén
             */
            if (cmd[1].charAt(0) =='C') {
                Map<String, Cleaner> cleaners = gameEngine.getCleaners();
                Cleaner cleaner = cleaners.get(cmd[1]);
                Map<String, Item> items = cleaner.getItems();
                Item item = items.get(cmd[2]);
                if (item == null) gameEngine.getSuttogo().error("You don’t have an item named like that.");
                else cleaner.useItem(item);
            } else{
                /**
                 * Oktatók esetén
                 */
                Map<String, Professor> professors = gameEngine.getProfessors();
                Professor professor = professors.get(cmd[1]);
                Map<String, Item> items = professor.getItems();
                Item item = items.get(cmd[2]);
                if (item == null) gameEngine.getSuttogo().error("You don’t have an item named like that.");
                else professor.useItem(item);
            }
        }
    }

}