package game.model.commands.builders;

import game.model.commands.iCommand;
import game.model.entities.Student;
import game.model.main.Main;

public class AddStudent implements iCommand {
    @Override
    public void execute(String[] cmd) {
        boolean paralyzed = false;
        if(cmd.length>1){ paralyzed = Boolean.parseBoolean(cmd[1]); }
        Main.gameEngine.addStudent(new Student(paralyzed));
    }

    @Override
    public String getName() {
        return "student";
    }
}
