package game.model.commands.general;

import game.model.commands.iCommand;
import game.model.logging.Suttogo;
import game.model.main.GameMain;

public class Newgame implements iCommand {
    @Override
    public void execute(String[] cmd) {
        GameMain.gameEngine.initGame();
        Suttogo.info("New Game loaded. Have fun!");
    }


}
