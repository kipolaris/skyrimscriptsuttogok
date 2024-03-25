package game;

import game.model.entities.*;
import game.model.entities.Character;
import game.model.entities.building.*;
import game.model.entities.items.*;
import game.model.logging.Suttogo;

import java.util.ArrayList;

public class TransistorTest {
    private Student student = new Student();
    private Room room1;
    private Room room2;
    private Transistor trans1 = new Transistor(false, false, 1, room1, null);
    private Transistor trans2 = new Transistor(false, false, 1, room2, null);
    private Door door;
    private FFP2 ffp2 = new FFP2(false, false, 1, room1, null);
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
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: Pairing two transistors");
        student.useItem(trans1);
        Suttogo.info("--------------------------------------------------------");
    }

    void transistorPlace(){
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: Placing a transistor");
        student.useItem(trans1);
        student.dropItem(trans1);
        Suttogo.info("--------------------------------------------------------");
    }

    void transistorUnpair(){
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: ... ");
        student.useItem(trans1);
        student.dropItem(trans1);
        student.dropItem(trans2);
        Suttogo.info("--------------------------------------------------------");
    }

    void transistorUnsuccessful(){
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: ... ");
        student.useItem(trans1);
        student.useItem(trans1);
        Suttogo.info("--------------------------------------------------------");
    }

    void transistorSuccesful(){
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: ... ");
        student.useItem(trans1);
        student.dropItem(trans1);

        student.move(door);
        student.useItem(trans2);
        Suttogo.info("--------------------------------------------------------");
    }

    void transToGasParalyze() {
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: ... ");
        student.useItem(trans1);
        student.dropItem(trans1);

        student.move(door);
        room1.setGassed(true);

        student.useItem(trans2);
        Suttogo.info("--------------------------------------------------------");
    }

    void transToGasProt() {
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: ... ");
        student.addItem(ffp2);
        student.useItem(trans1);
        student.dropItem(trans1);

        student.move(door);
        room1.setGassed(true);

        student.useItem(trans2);
        Suttogo.info("--------------------------------------------------------");
    }
}
