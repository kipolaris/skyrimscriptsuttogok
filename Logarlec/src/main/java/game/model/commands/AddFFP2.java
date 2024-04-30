package game.model.commands;

import game.model.entities.items.FFP2;
import game.model.main.Main;

public class AddFFP2 implements iCommand{
    @Override
    public void execute(String[] cmd) {
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
        Main.gameEngine.addItem(ffp2);
    }

    @Override
    public String getName() {
        return "ffp2";
    }
}
