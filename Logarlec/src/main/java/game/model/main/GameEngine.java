package game.model.main;

import game.model.entities.Professor;
import game.model.entities.Student;
import game.model.entities.building.BuildingAI;
import game.model.entities.items.SlideRule;

import java.util.ArrayList;

import game.model.logging.Suttogo;
import java.util.ArrayList;

public class GameEngine {
    private ArrayList<Student> students;
    private ArrayList<Professor> professors;

    private BuildingAI builder;

    private SlideRule slideRule;

    public void initGame() {
        Suttogo.info("initGame()");
        students = new ArrayList<>();
        professors = new ArrayList<>();
        builder = new BuildingAI();
        slideRule = new SlideRule();
    }

    public boolean studentsExtinct() {
        Suttogo.info("studentsExtinct()");
        Suttogo.info("\teturn " + students.isEmpty());
        return students.isEmpty();
    }

    public void endGame() {
        Suttogo.info("endGame()");
        students.clear();
        professors.clear();
        builder = null;
        slideRule = null;
    }

    public void refresh() {
        Suttogo.info("refresh()");
        students = new ArrayList<>();
        professors = new ArrayList<>();
        builder = new BuildingAI();
        slideRule = new SlideRule();
    }

    public void playOnePhase() {
        Suttogo.info("playOnePhase()");
        // Implementation details here
    }

    public void studentDied(Student s) {
        Suttogo.info("studentDied(Student)");
        students.remove(s);
    }
}
