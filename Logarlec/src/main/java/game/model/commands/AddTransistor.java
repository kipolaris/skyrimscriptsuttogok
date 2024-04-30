package game.model.commands;

import game.model.entities.items.Cups;
import game.model.entities.items.Transistor;
import game.model.main.Main;

public class AddTransistor implements iCommand{
    @Override
    public void execute(String[] cmd) {
        Transistor t = new Transistor(false, false, null, null);
        Main.gameEngine.addItem(t);
    }

    @Override
    public String getName() {
        return "transistor";
    }
}
