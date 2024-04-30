package game.model.commands.builders;

import game.model.commands.iCommand;
import game.model.entities.items.Camembert;
import game.model.main.Main;

public class AddCamembert implements iCommand {
    @Override
    public void execute(String[] cmd) {
        int durability = 1;
        if(cmd.length > 1) durability = Integer.parseInt(cmd[2]);
        Camembert c = new Camembert(false, false, durability, null, null);
        Main.gameEngine.addItem(c);
    }

}
