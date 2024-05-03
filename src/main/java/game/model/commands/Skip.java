package game.model.commands;

import game.model.entities.Cleaner;
import game.model.entities.Professor;
import game.model.entities.Student;
import game.model.logging.Suttogo;
import game.model.main.GameMain;

import java.util.Map;

/**
 * Parancs osztály karakterek körének passzolására
 */
public class Skip implements iCommand{
    @Override
    public void execute(String[] cmd) {
        Map<String, Student> students = GameMain.gameEngine.getStudents();
        Map<String, Professor> professors = GameMain.gameEngine.getProfessors();
        Map<String, Cleaner> cleaners = GameMain.gameEngine.getCleaners();
        Student student = null;
        Professor professor = null;
        Cleaner cleaner = null;
        if(cmd.length > 1) {
            student = students.get(cmd[1]);
            if(student!=null) student.skipTurn();
            else {
                professor = professors.get(cmd[1]);
                if(professor!=null) professor.skipTurn();
                else {
                    cleaner = cleaners.get(cmd[1]);
                    if(cleaner!=null) cleaner.skipTurn();
                    else Suttogo.error("Character not found!");
                }
            }
        }
        else Suttogo.error("Too few arguments");
        Suttogo.info("Your Round ended.");
    }
}
