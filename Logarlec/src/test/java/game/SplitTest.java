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
import game.model.logging.Suttogo;

import java.util.ArrayList;

public class SplitTest {
    private BuildingAI buildingAI = new BuildingAI();
    private Room room1;
    private Room room2;
    private Door door;
    private Transistor trans = new Transistor(false, false, 1, room1, null);
    private Student student = new Student();
    private Professor prof = new Professor();
    private SlideRule slideRule = new SlideRule(false, false, 1, room1, null);
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
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: Splitting an ordinary room");
        buildingAI.splitRoom(room1);
        Suttogo.info("--------------------------------------------------------");
    }

    public void splitGassed(){
        room1 = new Room(2,true,false,r1doors,r1items,r1characters);
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: Splitting a gassed room");
        buildingAI.splitRoom(room1);
        Suttogo.info("--------------------------------------------------------");
    }

    public void splitCursed(){
        room1 = new Room(2,false,true,r1doors,r1items,r1characters);
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: Splitting a cursed room");
        buildingAI.splitRoom(room1);
        Suttogo.info("--------------------------------------------------------");
    }

    public void splitGassedCursed(){
        room1 = new Room(2,true,true,r1doors,r1items,r1characters);
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: Splitting a gassed and cursed room");
        buildingAI.splitRoom(room1);
        Suttogo.info("--------------------------------------------------------");
    }
}
