package game.model.commands.general;

import game.model.commands.iCommand;
import game.model.main.Main;

/**
 * Ez a parancs kinyomtatja a konzolra
 * az általános állapotfrissítést.
 */
public class Out implements iCommand {
    @Override
    public void execute(String[] cmd) {
        Main.printOut();
    }
}
