package game.model.commands;

import game.model.main.SaverLoader;

import static game.model.main.Main.gameEngine;

public class Load implements iCommand{
    @Override
    public void execute(String[] cmd) {
        SaverLoader parser = new SaverLoader(gameEngine);

        parser.loadGame(cmd[1]);
    }

    @Override
    public String getName() {
        return "load";
    }
}
