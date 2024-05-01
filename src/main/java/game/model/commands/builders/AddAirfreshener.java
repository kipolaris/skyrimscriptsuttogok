package game.model.commands.builders;

import game.model.commands.iCommand;
import game.model.entities.items.AirFreshener;
import game.model.main.GameMain;

public class AddAirfreshener implements iCommand {
    @Override
    public void execute(String[] cmd) {
        AirFreshener af = new AirFreshener(false, false, 1, null, null);
        GameMain.gameEngine.addItem(af);
    }

}
