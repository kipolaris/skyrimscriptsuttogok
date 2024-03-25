package game.model.main;

import game.model.entities.Professor;
import game.model.entities.Student;
import game.model.entities.building.BuildingAI;
import game.model.entities.items.SlideRule;
import game.model.logging.Suttogo;

import java.util.ArrayList;

public class GameEngine {
    private ArrayList<Student> students;
    private ArrayList<Professor> professors;

    private BuildingAI builder;

    private SlideRule slideRule;

    public void initGame(){
        Suttogo.info("initGame()");
        students = new ArrayList<>();
        professors = new ArrayList<>();
        builder = new BuildingAI();
        slideRule = new SlideRule(false, false, 1, null, null);
    }

    public boolean studentsExtinct(){
        Suttogo.info("studentsExtinct()");
        Suttogo.info("\treturn boolean");
        return students.isEmpty();
    }

    public void endGame(){
        Suttogo.info("endGame()");
        students.clear();
        professors.clear();
        builder = null;
        slideRule = null;
    }

    public void refresh(){
        Suttogo.info("refresh()");
        students = new ArrayList<>();
        professors = new ArrayList<>();
        builder = new BuildingAI();
        slideRule = new SlideRule(false, false, 1, slideRule.getLocation(), null);
    }

    public void playOnePhase(){
        Suttogo.info("playOnePhase()");
        //yet to be implemented
    }

    public void studentDied(Student s){
        Suttogo.info("studentDied(Student)");
        students.remove(s);
    }
}
