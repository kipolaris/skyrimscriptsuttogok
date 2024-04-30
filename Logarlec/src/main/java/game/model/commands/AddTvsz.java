package game.model.commands;

import game.model.entities.items.TVSZ;
import game.model.main.Main;

public class AddTvsz implements iCommand{
    @Override
    public void execute(String[] cmd) {
        int durability = 1;
        if(cmd.length > 2) durability = Integer.parseInt(cmd[2]);
        TVSZ tvsz;
        if(cmd.length > 1) {
            switch (cmd[1]) {
                case ("fake"):
                    tvsz = new TVSZ(false, false, durability, null, null, true);
                    break;
                default:
                    tvsz = new TVSZ(false, false, durability, null, null, false);
                    break;
            }
        }
        tvsz = new TVSZ(false, false, durability, null, null, false);
        Main.gameEngine.addItem(tvsz);
    }

    @Override
    public String getName() {
        return "tvsz";
    }
}
