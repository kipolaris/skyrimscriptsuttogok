package game.model.main;

import game.model.entities.Professor;
import game.model.entities.Student;
import game.model.entities.building.BuildingAI;
import game.model.entities.items.SlideRule;

import java.util.ArrayList;

public class GameEngine {
    private ArrayList<Student> students;
    private ArrayList<Professor> professors;

    private BuildingAI builder;

    private SlideRule slideRule;

    public void initGame(){
        students = new ArrayList<>();
        professors = new ArrayList<>();
        builder = new BuildingAI();
        slideRule = new SlideRule();
    }

    public boolean studentsExtinct(){
        return students.isEmpty();
    }

    public void endGame(){
        students.clear();
        professors.clear();
        builder = null;
        slideRule = null;
    }

    public void refresh(){
        students = new ArrayList<>();
        professors = new ArrayList<>();
        builder = new BuildingAI();
        slideRule = new SlideRule();
    }

    public void playOnePhase(){
        //yet to be implemented
    }

    public void studentDied(Student s){
        students.remove(s);
    }
}
