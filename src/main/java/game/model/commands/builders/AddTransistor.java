package game.model.commands.builders;

import game.model.commands.iCommand;
import game.model.entities.items.Transistor;
import game.model.main.GameMain;

/**
 * Parancs osztály egy új tranzisztor felvételére
 */
public class AddTransistor implements iCommand {
    @Override
    public void execute(String[] cmd) {/**Létrehoz egy tranzisztort*/
        Transistor t = new Transistor(false, false, null, null);
        GameMain.gameEngine.addItem(t);
    }

}
