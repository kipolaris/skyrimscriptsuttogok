package game.model.commands.general;

import game.model.commands.iCommand;
import game.model.logging.Suttogo;
import game.model.main.GameMain;

import static game.model.main.GameMain.gameEngine;

public class StartGame implements iCommand {
    @Override
    public void execute(String[] cmd) {
        GameMain.isGameStarted = true;

        gameEngine.playOnePhase();

        Suttogo.note("Game Started!");
    }

}
