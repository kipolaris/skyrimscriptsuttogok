package game.model.commands;

import game.model.entities.items.AirFreshener;
import game.model.entities.items.FFP2;
import game.model.main.Main;

public class AddAirfreshener implements iCommand{
    @Override
    public void execute(String[] cmd) {
        AirFreshener af = new AirFreshener(false, false, 1, null, null);
        Main.gameEngine.addItem(af);
    }

    @Override
    public String getName() {
        return "airfreshener";
    }
}
