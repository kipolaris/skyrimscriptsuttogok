package game.model.commands.general;

import game.model.commands.iCommand;
import game.model.logging.Suttogo;
import game.model.main.GameMain;

public class RandomGo implements iCommand {
    @Override
    public void execute(String[] cmd) {

        GameMain.gameEngine.setRandom(true);
        Suttogo.note("Random was set to true");
    }

}
