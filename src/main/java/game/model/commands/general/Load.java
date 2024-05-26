package game.model.commands.general;

import game.model.commands.iCommand;
import game.model.logging.Suttogo;
import game.model.main.SaverLoader;

import static game.model.main.GameMain.gameEngine;

/**
 * Parancs osztály játék betöltéséhez
 */
public class Load implements iCommand {/**Betölt egy játékmenetet*/
    @Override
    public void execute(String[] cmd) {
        SaverLoader parser = new SaverLoader(gameEngine);
        if(cmd.length < 2) {
            gameEngine.getSuttogo().error("Too few arguments!");
            return;
        }
        parser.loadGame(cmd[1]);
    }

}
