package game.model.commands;

import game.model.entities.Student;
import game.model.logging.Suttogo;
import game.model.main.Main;

import java.util.Map;

public class Skip implements iCommand{
    @Override
    public void execute(String[] cmd) {
        Map<String, Student> students = Main.gameEngine.getStudents();
        Student student = students.get(cmd[1]);
        student.setActions(-3);
        Suttogo.info("Your Round ended.");
    }

}
