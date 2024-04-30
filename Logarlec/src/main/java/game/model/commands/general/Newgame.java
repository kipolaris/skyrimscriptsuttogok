package game.model.commands.general;

import game.model.commands.iCommand;
import game.model.logging.Suttogo;
import game.model.main.Main;

public class Newgame implements iCommand {
    @Override
    public void execute(String[] cmd) {
        Main.gameEngine.initGame();
        Suttogo.info("New Game loaded. Have fun!");
    }


}
