package game;

import game.model.entities.Professor;
import game.model.entities.Student;
import game.model.entities.Character;
import game.model.entities.building.BuildingAI;
import game.model.entities.building.Door;
import game.model.entities.building.Room;
import game.model.entities.items.Item;
import game.model.entities.items.SlideRule;
import game.model.entities.items.Transistor;

import java.util.ArrayList;

public class SplitTest {
    private BuildingAI buildingAI = new BuildingAI();
    private Room room1;
    private Room room2;
    private Door door;
    private Transistor trans = new Transistor();
    private Student student = new Student();
    private Professor prof = new Professor();
    private SlideRule slideRule = new SlideRule();
    private ArrayList<Character> r1characters = new ArrayList<>();
    private ArrayList<Item> r1items = new ArrayList<>();
    private ArrayList<Door> r1doors = new ArrayList<>();

    public void initialize(){
        r1characters.add(student);
        r1characters.add(prof);

        r1items.add(slideRule);
        r1items.add(trans);

        r1doors.add(door);

        door = new Door(room1,room2,true,true);
        room2 = new Room(2,false,false,r1doors,null,null);
    }

    public void splitNormal() {
        room1 = new Room(2,false,false,r1doors,r1items,r1characters);
        buildingAI.splitRoom(room1);
    }

    public void splitGassed(){
        room1 = new Room(2,true,false,r1doors,r1items,r1characters);
        buildingAI.splitRoom(room1);
    }

    public void splitCursed(){
        room1 = new Room(2,false,true,r1doors,r1items,r1characters);
        buildingAI.splitRoom(room1);
    }

    public void splitGassedCursed(){
        room1 = new Room(2,true,true,r1doors,r1items,r1characters);
        buildingAI.splitRoom(room1);
    }
}
