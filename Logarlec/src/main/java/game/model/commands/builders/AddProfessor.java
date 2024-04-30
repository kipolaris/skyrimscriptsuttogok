package game.model.commands.builders;

import game.model.commands.iCommand;
import game.model.entities.Professor;
import game.model.main.Main;

import static game.model.main.Main.gameEngine;

public class AddProfessor implements iCommand {
    @Override
    public void execute(String[] cmd) {
        boolean paralyzed = false;
        if(cmd.length>1) { paralyzed = Boolean.parseBoolean(cmd[1]); }
        gameEngine.addProfessor(new Professor(paralyzed));
    }

    @Override
    public String getName() {
        return "professor";
    }
}
