package game.model.commands.builders;

import game.model.commands.iCommand;
import game.model.entities.items.Cups;
import game.model.main.Main;

public class AddCups implements iCommand {
    @Override
    public void execute(String[] cmd) {
        int durability = 1;
        if(cmd.length > 1) durability = Integer.parseInt(cmd[1]);
        Cups cups = new Cups(false, false, durability, null, null);
        Main.gameEngine.addItem(cups);
    }

}
