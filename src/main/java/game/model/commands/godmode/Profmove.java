package game.model.commands.godmode;

import game.model.commands.base.Move;
import game.model.commands.iCommand;
import game.model.entities.Professor;
import game.model.logging.Suttogo;

import static game.model.main.GameMain.gameEngine;

public class Profmove implements iCommand {
    @Override
    public void execute(String[] cmd) {/**Mozgat egy professzort*/
        if(cmd.length < 2) {
            Suttogo.error("Too few arguments!");
            return;
        }
        Move c = new Move();
        if(gameEngine.getCurrent() instanceof Professor && !gameEngine.getRandom()){
            Professor s = (Professor) gameEngine.getCurrent();

            //megnézzük, hogy a user valóban ezt a professort gépelte-e be
            Professor chosen = gameEngine.getProfessors().get(cmd[1]);

            if(chosen != null && chosen.equals(s) && gameEngine.areActionsLeft(s)) {

                String[] ns = new String[]{s.getId() + cmd};

                c.execute(ns);
            }
        }
    }

}
