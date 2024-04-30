package game.model.commands.general;

import game.model.commands.iCommand;
import game.model.logging.Suttogo;
import game.model.main.Main;

import static game.model.main.Main.gameEngine;

public class StartGame implements iCommand {
    @Override
    public void execute(String[] cmd) {
        Main.isGameStarted = true;

        gameEngine.playOnePhase();

        Suttogo.note("Game Started!");
    }

}
