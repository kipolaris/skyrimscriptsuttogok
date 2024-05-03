package game.model.commands.general;

import game.model.commands.iCommand;
import game.model.logging.Suttogo;
import game.model.main.GameMain;

/**
 * Parancs osztály a random algoritmusok engedélyezéséhez
 */
public class RandomGo implements iCommand {
    @Override
    public void execute(String[] cmd) {/**Engedélyezi a random algoritmusokat*/

        GameMain.gameEngine.setRandom(true);
        Suttogo.note("Random was set to true");
    }

}
