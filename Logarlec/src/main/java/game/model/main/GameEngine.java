package game.model.main;

import game.model.entities.Professor;
import game.model.entities.Student;
import game.model.entities.building.BuildingAI;
import game.model.entities.items.SlideRule;

import java.util.ArrayList;

//#todo: implement class
public class GameEngine {
    private ArrayList<Student> students;
    private ArrayList<Professor> professors;

    private BuildingAI builder;

    private SlideRule slideRule;

    public void initGame(){
        //implement
    }

    public boolean studentsExtinct(){
        return false;
    }

    public void endGame(){
        //implement
    }

    public void refresh(){
        //implement
    }

    public void playOnePhase(){
        //implement
    }

    public void studentDied(Student s){ //?
        //implement
    }
}
