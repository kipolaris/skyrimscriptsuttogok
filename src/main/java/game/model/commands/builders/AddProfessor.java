package game.model.commands.builders;

import game.model.commands.iCommand;
import game.model.entities.Professor;

import static game.model.main.GameMain.gameEngine;

/**
 * Parancs osztály egy új oktató felvételére
 */
public class AddProfessor implements iCommand {
    @Override
    public void execute(String[] cmd) {/**Létrehoz egy professzort*/
        boolean paralyzed = false;
        if(cmd.length>1) { paralyzed = Boolean.parseBoolean(cmd[1]); }
        gameEngine.addProfessor(new Professor(paralyzed));
    }

}
