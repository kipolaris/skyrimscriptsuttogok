package game.model.commands.general;

import game.model.commands.iCommand;
import game.model.main.GameMain;

public class RandomNogo implements iCommand {
    @Override
    public void execute(String[] cmd) {
        GameMain.gameEngine.setRandom(false);
    }

}
