package game.model.commands.builders;

import game.model.commands.iCommand;
import game.model.entities.items.TVSZ;
import game.model.main.GameMain;

/**
 * Parancs osztály egy új tvsz felvételére
 */
public class AddTvsz implements iCommand {
    @Override
    public void execute(String[] cmd) {/**Létrehoz egy tvsz-t*/
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
        GameMain.gameEngine.addItem(tvsz);
    }

}
