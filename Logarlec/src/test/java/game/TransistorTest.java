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
    private Transistor trans1;
    private Transistor trans2;
    private Door door;
    private FFP2 ffp2 = new FFP2(false, false, 1, room1, null);
    private ArrayList<Character> r1characters = new ArrayList<>();
    private ArrayList<Door> doors = new ArrayList<>();


    public void initialize() {
        room1 = new Room(2,false,false,doors,null,r1characters);
        room2 = new Room(2,false,false,doors,null,null);

        trans1 = new Transistor(false, false, 1, room1, null);
        trans2 = new Transistor(false, false, 1, room1, null);


        doors.add(door);
        door = new Door(room1, room2,true,true);
        r1characters.add(student);

        student.setLocation(room1);
        student.addItem(trans1);
        student.addItem(trans2);
    }

    public void transistorPair(){
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: Pairing two transistors");
        student.useItem(trans1);
        Suttogo.info("--------------------------------------------------------");
    }

    public void transistorPlace(){
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: Placing a transistor");
        student.useItem(trans1);
        student.dropItem(trans1);

        Suttogo.info("--------------------------------------------------------");
    }

    public void transistorUnpair(){
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: ... ");
        student.useItem(trans1);
        student.dropItem(trans1);
        student.dropItem(trans2);
        Suttogo.info("--------------------------------------------------------");
    }

    public void transistorUnsuccessful(){
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: ... ");
        student.useItem(trans1);
        student.useItem(trans1);
        Suttogo.info("--------------------------------------------------------");
    }

    public void transistorSuccesful(){
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: ... ");
        student.useItem(trans1);
        student.dropItem(trans1);

        student.move(door);
        student.useItem(trans2);
        Suttogo.info("--------------------------------------------------------");
    }

    public void transToGasParalyze() {
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: ... ");
        student.useItem(trans1);
        student.dropItem(trans1);

        student.move(door);
        room1.setGassed(true);

        student.useItem(trans2);
        Suttogo.info("--------------------------------------------------------");
    }

    public void transToGasProt() {
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
