package game.model.commands.general;

import game.model.commands.iCommand;
import game.model.logging.Suttogo;
import game.model.main.Main;

public class RandomGo implements iCommand {
    @Override
    public void execute(String[] cmd) {

        Main.gameEngine.setRandom(true);
        Suttogo.note("Random was set to true");
    }

}
