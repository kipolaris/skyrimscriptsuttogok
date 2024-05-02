package game.model.commands.builders;

import game.model.commands.iCommand;
import game.model.entities.items.FFP2;
import game.model.main.GameMain;

public class AddFFP2 implements iCommand {
    @Override
    public void execute(String[] cmd) {/**Létrehoz egy ffp2 maszkot*/
        int durability = 1;
        if(cmd.length > 2) durability = Integer.parseInt(cmd[2]);
        FFP2 ffp2;
        if(cmd.length > 1) {
            switch (cmd[1]) {
                case ("fake"):
                    ffp2 = new FFP2(false, false, durability, null, null, true);
                    break;
                default:
                    ffp2 = new FFP2(false, false, durability, null, null, false);
                    break;
            }
        }
        else ffp2 = new FFP2(false, false, durability, null, null, false);
        GameMain.gameEngine.addItem(ffp2);
    }

}
