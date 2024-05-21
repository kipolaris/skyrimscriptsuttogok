package game.model.commands;

import game.model.commands.base.Drop;
import game.model.entities.Student;

import static game.model.main.GameMain.gameEngine;

/**
 * Parancs osztály hallgatók általi tárgy eldobásra
 */
public class StudDrop implements iCommand{
    @Override
    public void execute(String[] cmd) {
        Drop c = new Drop();
        if(gameEngine.getCurrent() instanceof Student){
            Student s = (Student) gameEngine.getCurrent();

            if(gameEngine.areActionsLeft(s)) {
                String[] ns = new String[]{cmd[0], s.getId(), cmd[1]};
                c.execute(ns);
            }
        }
    }

}
