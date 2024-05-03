package game.model.commands.builders;

import game.model.commands.iCommand;
import game.model.entities.Student;
import game.model.main.GameMain;

/**
 * Parancs osztály egy új hallgató felvételére
 */
public class AddStudent implements iCommand {
    @Override
    public void execute(String[] cmd) {/**Létrehoz egy hallgatót*/
        boolean paralyzed = false;
        if(cmd.length>1){ paralyzed = Boolean.parseBoolean(cmd[1]); }
        GameMain.gameEngine.addStudent(new Student(paralyzed));
    }

}
