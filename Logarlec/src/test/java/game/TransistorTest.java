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
    private TVSZ t = new TVSZ();
    private FFP2 ffp2 = new FFP2();
    private ArrayList<Character> r1characters = new ArrayList<Character>();


    void initialize() {
        r1characters.add(student);
        student.addItem(trans1);
        student.addItem(trans2);
        student.addItem(t);
        student.addItem(ffp2);

        room1 = new Room(2,false,false,null,null,r1characters);
    }

    void transistorPair(){
        student.useItem(trans1);
        System.out.println("Trans1 párja: " + trans1.getPair());
    }

    void transistorPlace(){
        student.useItem(trans1);
        student.dropItem(trans1);
        System.out.println("Trans1 lokációja: " + trans1.getLocation());
        System.out.println("Trans1 párja: " + trans1.getPair());
    }

    void transistorUnpair(){
        student.useItem(trans1);
        student.dropItem(trans1);
        student.dropItem(trans2);
        System.out.println("Trans1 párja: " + trans1.getPair());
        System.out.println("Trans2 párja: " + trans1.getPair());
    }

    void transistorUnsuccessful(){
        student.useItem(trans1);
        student.useItem(trans1);
    }


}
