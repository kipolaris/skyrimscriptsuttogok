package game.model.commands.builders;

import game.model.commands.iCommand;
import game.model.entities.Cleaner;
import game.model.main.GameMain;

public class AddCleaner implements iCommand {
    @Override
    public void execute(String[] cmd) {
        boolean paralyzed = false;
        if(cmd.length>1) { paralyzed = Boolean.parseBoolean(cmd[1]); }
        GameMain.gameEngine.addCleaner(new Cleaner(paralyzed));
    }

}
