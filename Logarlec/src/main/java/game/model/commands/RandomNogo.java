package game.model.commands;

import game.model.main.Main;

public class RandomNogo implements iCommand{
    @Override
    public void execute(String[] cmd) {
        Main.gameEngine.setRandom(false);
    }

    @Override
    public String getName() {
        return "randomnogo";
    }
}
