package game.model.commands;

import game.model.commands.base.Move;
import game.model.entities.Student;
import game.model.logging.Suttogo;

import static game.model.main.GameMain.gameEngine;

/**
 * Parancs osztály hallgatók általi mozgásra
 */
public class StudMove implements iCommand{
    @Override
    public void execute(String[] cmd) {
        Move c = new Move();
        if(gameEngine.getCurrent() instanceof Student){
            Student s = (Student) gameEngine.getCurrent();

            if(gameEngine.areActionsLeft(s)) {
                String[] ns = new String[]{cmd[0], s.getId(), cmd[1]};
                c.execute(ns);
            }
        }else{
            Suttogo.getSuttogo().error("This is not your round!");
        }
    }

}
