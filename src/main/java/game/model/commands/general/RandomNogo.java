package game.model.commands.general;

import game.model.commands.iCommand;
import game.model.logging.Suttogo;
import game.model.main.GameMain;

import static game.model.main.GameMain.gameEngine;

/**
 * Parancs osztály a random algoritmusok tiltásához
 */
public class RandomNogo implements iCommand {
    @Override
    public void execute(String[] cmd) {/**Nem engedélyezi a random algoritmusokat*/

        GameMain.gameEngine.setRandom(false);

        Suttogo.getSuttogo().note("random turned off");
    }

}
