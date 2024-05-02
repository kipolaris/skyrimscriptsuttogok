package game.model.commands.general;

import game.model.commands.iCommand;
import game.model.logging.Suttogo;
import game.model.main.GameMain;

public class RandomNogo implements iCommand {
    @Override
    public void execute(String[] cmd) {/**Nem engedélyezi a random algoritmusokat*/

        GameMain.gameEngine.setRandom(false);

        Suttogo.note("random turned off");
    }

}
