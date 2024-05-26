package game.model.commands.general;

import game.model.commands.iCommand;
import game.model.logging.Suttogo;
import game.model.main.GameMain;

import static game.model.main.GameMain.gameEngine;

/**
 * Parancs osztály a random algoritmusok engedélyezéséhez
 */
public class RandomGo implements iCommand {
    @Override
    public void execute(String[] cmd) {/**Engedélyezi a random algoritmusokat*/

        GameMain.gameEngine.setRandom(true);
        gameEngine.getSuttogo().note("Random was set to true");
    }

}
