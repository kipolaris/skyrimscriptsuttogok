package game.model.commands;

import game.model.logging.Suttogo;
import game.model.main.SaverLoader;

import static game.model.main.Main.gameEngine;

public class Save implements iCommand{
    @Override
    public void execute(String[] cmd) {
        if(cmd.length < 2) {
            Suttogo.error("Too few arguments!");
            return;
        }
        SaverLoader parser = new SaverLoader(gameEngine);

        parser.saveGame(cmd[1]);
    }

    @Override
    public String getName() {
        return "save";
    }
}
