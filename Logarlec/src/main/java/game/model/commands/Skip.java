package game.model.commands;

import game.model.entities.Character;
import game.model.entities.Student;
import game.model.logging.Suttogo;
import game.model.main.Main;

import java.util.Map;

public class Skip implements iCommand{
    @Override
    public void execute(String[] cmd) {
        Character c = Main.gameEngine.findCharacter(cmd[1]);
        if(c != null) {
            c.skipTurn();
            Suttogo.info("Your Round ended.");
        }
        else {
            Suttogo.info("There is no such character!");
        }
    }

    @Override
    public String getName() {
        return "skip";
    }
}
