package game.model.commands.godmode;

import game.model.commands.base.Move;
import game.model.commands.iCommand;
import game.model.entities.Professor;
import game.model.logging.Suttogo;

import static game.model.main.GameMain.gameEngine;

/**
 * Parancs osztály oktatók mozgatásához
 */
public class Profmove implements iCommand {
    @Override
    public void execute(String[] cmd) {/**Mozgat egy professzort*/
        if(cmd.length < 2) {
            Suttogo.getSuttogo().error("Too few arguments!");
            return;
        }
        Move c = new Move();
        if(gameEngine.getCurrent().getId().startsWith("Professor") && !gameEngine.getRandom()){
            Professor s = (Professor) gameEngine.getCurrent();

            String[] ns = new String[]{s.getId() + cmd};

            c.execute(ns);
        }
    }

}
