package game.model.commands.builders;

import game.model.commands.iCommand;
import game.model.entities.Professor;

import static game.model.main.GameMain.gameEngine;

public class AddProfessor implements iCommand {
    @Override
    public void execute(String[] cmd) {
        boolean paralyzed = false;
        if(cmd.length>1) { paralyzed = Boolean.parseBoolean(cmd[1]); }
        gameEngine.addProfessor(new Professor(paralyzed));
    }

}
