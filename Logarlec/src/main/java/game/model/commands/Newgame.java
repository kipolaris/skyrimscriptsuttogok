package game.model.commands;

import game.model.logging.Suttogo;
import game.model.main.Main;

public class Newgame implements iCommand{
    @Override
    public void execute(String[] cmd) {
        Main.gameEngine.initGame();
        Suttogo.info("New Game loaded. Have fun!");
    }


    @Override
    public String getName() {
        return "newgame";
    }
}
