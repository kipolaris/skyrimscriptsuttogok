package game.model.commands.godmode;

import game.model.commands.iCommand;
import game.model.entities.Professor;
import game.model.logging.Suttogo;

import static game.model.main.GameMain.gameEngine;

/**
 * Parancs osztály oktatók támadásának kezdeményezésére
 */
public class Kill implements iCommand {
    @Override
    public void execute(String[] cmd) {/**Kezdeményezi egy professzor támadását*/

        if(gameEngine.getCurrent().getId().startsWith("Professor") && !gameEngine.getRandom()){
            Professor s = (Professor) gameEngine.getCurrent();

            //megnézzük, hogy a user valóban ezt a professort gépelte-e be
            if(cmd.length < 2) {
                Suttogo.getSuttogo().error("Too few arguments!");
                return;
            }
            Professor chosen = gameEngine.getProfessors().get(cmd[1]);

            if(chosen != null && chosen.equals(s) && gameEngine.areActionsLeft(s)) {
                s.getLocation().killStudents();
            }
        }

    }

}
