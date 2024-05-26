package game.model.commands.general;

import game.model.commands.iCommand;
import game.model.logging.Suttogo;
import game.model.main.SaverLoader;

import static game.model.main.GameMain.gameEngine;

/**
 * Parancs osztály a játék elmentéséhez
 */
public class Save implements iCommand {/**Elmenti a játékmenetet*/
    @Override
    public void execute(String[] cmd) {
        if(cmd.length < 2) {
            gameEngine.getSuttogo().error("Too few arguments!");
            return;
        }
        SaverLoader parser = new SaverLoader(gameEngine);

        parser.saveGame(cmd[1]);
    }

}
