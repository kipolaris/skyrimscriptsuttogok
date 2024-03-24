package game;

import game.model.entities.*;
import game.model.entities.Character;
import game.model.entities.building.*;
import game.model.entities.items.*;
import java.util.ArrayList;

public class TransistorTest {
    private Student student = new Student();
    private Transistor trans1 = new Transistor();
    private Transistor trans2 = new Transistor();
    private Room room1;
    private Room room2;
    private Door door;
    private FFP2 ffp2 = new FFP2();
    private ArrayList<Character> r1characters = new ArrayList<>();
    private ArrayList<Door> doors = new ArrayList<>();


    void initialize() {
        doors.add(door);
        door = new Door(room1, room2,true,true);
        r1characters.add(student);
        student.addItem(trans1);
        student.addItem(trans2);

        room1 = new Room(2,false,false,doors,null,r1characters);
        room2 = new Room(2,false,false,doors,null,null);
    }

    void transistorPair(){
        student.useItem(trans1);
    }

    void transistorPlace(){
        student.useItem(trans1);
        student.dropItem(trans1);
    }

    void transistorUnpair(){
        student.useItem(trans1);
        student.dropItem(trans1);
        student.dropItem(trans2);
    }

    void transistorUnsuccessful(){
        student.useItem(trans1);
        student.useItem(trans1);
    }

    void transistorSuccesful(){
        student.useItem(trans1);
        student.dropItem(trans1);

        student.move(door);
        student.useItem(trans2);
    }

    void transToGasParalyze() {
        student.useItem(trans1);
        student.dropItem(trans1);

        student.move(door);
        room1.setGassed(true);

        student.useItem(trans2);
    }

    void transToGasProt() {
        student.addItem(ffp2);
        student.useItem(trans1);
        student.dropItem(trans1);

        student.move(door);
        room1.setGassed(true);

        student.useItem(trans2);
    }
}
