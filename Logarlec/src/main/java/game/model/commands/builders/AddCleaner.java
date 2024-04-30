package game.model.commands.builders;

import game.model.commands.iCommand;
import game.model.entities.Cleaner;
import game.model.entities.Professor;
import game.model.main.Main;

public class AddCleaner implements iCommand {
    @Override
    public void execute(String[] cmd) {
        boolean paralyzed = false;
        if(cmd.length>1) { paralyzed = Boolean.parseBoolean(cmd[1]); }
        Main.gameEngine.addCleaner(new Cleaner(paralyzed));
    }

    @Override
    public String getName() {
        return "cleaner";
    }
}
