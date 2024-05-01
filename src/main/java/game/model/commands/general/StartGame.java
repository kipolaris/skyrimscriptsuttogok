package game.model.commands.general;

import game.model.commands.iCommand;
import game.model.main.GameMain;

public class StartGame implements iCommand {
    @Override
    public void execute(String[] cmd) {
        GameMain.gameEngine.playOnePhase();
        GameMain.isGameStarted = true;
    }
}
