package game.model.commands.godmode;

import game.model.commands.base.Move;
import game.model.commands.iCommand;
import game.model.entities.Cleaner;
import game.model.logging.Suttogo;

import static game.model.main.GameMain.gameEngine;

/**
 * Parancs osztály takarítók mozgatására
 */
public class Cleanermove implements iCommand {
    @Override
    public void execute(String[] cmd) {/**Mozgat egy takarítót*/
        Move c = new Move();
        if(gameEngine.getCurrent().getId().startsWith("Cleaner") && !gameEngine.getRandom()){
            Cleaner s = (Cleaner) gameEngine.getCurrent();

            //megnézzük, hogy a user valóban ezt a cleanert gépelte-e be
            if(cmd.length < 2) {
                Suttogo.getSuttogo().error("Too few arguments!");
                return;
            }
            Cleaner chosen = gameEngine.getCleaners().get(cmd[1]);

            if(chosen != null && chosen.equals(s) && gameEngine.areActionsLeft(s)) {
                String[] ns = new String[]{s.getId() + cmd};
                c.execute(ns);
            }
        }
    }

}
