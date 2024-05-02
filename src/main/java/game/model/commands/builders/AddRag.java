package game.model.commands.builders;

import game.model.commands.iCommand;
import game.model.entities.items.Rag;
import game.model.main.GameMain;

public class AddRag implements iCommand {
    @Override
    public void execute(String[] cmd) {/**Létrehoz egy táblatörlő rongyot*/
        int durability = 1;
        if(cmd.length > 1) durability = Integer.parseInt(cmd[1]);
        Rag r = new Rag(false, false, durability, null, null);
        GameMain.gameEngine.addItem(r);
    }

}
