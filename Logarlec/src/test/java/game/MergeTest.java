package game;

import game.model.entities.Student;
import game.model.entities.building.BuildingAI;
import game.model.entities.building.Door;
import game.model.entities.building.Room;
import game.model.entities.items.Transistor;

public class MergeTest {
    BuildingAI b1;
    Room r1, r2, r4;
    Student s1, s2, s3, s4, s5, s6;
    Transistor t1, t2;
    Door d1, d2;

    void initialize() throws Exception {
        b1 = new BuildingAI();
        r1 = new Room(5, false, false, null, null, null);
        r2 = new Room(3, false, false, null, null, null);
        r4 = new Room(1, false, false, null, null, null);
        s1 = new Student();
        s2 = new Student();
        s3 = new Student();
        s4 = new Student();
        s5 = new Student();
        s6 = new Student();
        t1 = new Transistor();
        t2 = new Transistor();
        d1 = new Door(r1, r2, true, true);
        d2 = new Door(r1, r4, true, true);

        r1.addCharacter(s1);
        r1.addCharacter(s2);
        r1.addCharacter(s3);
        r2.addCharacter(s4);
        r2.addCharacter(s5);
        s1.setLocation(r1);
        s2.setLocation(r1);
        s3.setLocation(r1);
        s4.setLocation(r2);
        s5.setLocation(r2);
        t1.setLocation(r1);
        r1.addItem(t1);
        t2.setLocation(r2);
        r2.addItem(t2);
        b1.addRoom(r1);
        b1.addRoom(r2);
        b1.addRoom(r4);
        r1.addDoor(d1);
        r2.addDoor(d1);
        r1.addDoor(d2);
        r4.addDoor(d2);
    }

    void CapacityOverload() {
        r2.addCharacter(s6);
        s6.setLocation(r2);
        b1.mergeRooms(r1, r2);
    }

    void TwoOrdinary() {
        b1.mergeRooms(r1, r2);
    }

    void OrdinaryAndGassed() {
        r2.setGassed(true);
        b1.mergeRooms(r1, r2);
    }

    void OrdinaryAndCursed() {
        r2.setCursed(true);
        b1.mergeRooms(r1, r2);
    }

    void OrdinaryAndGassedCursed() {
        r2.setGassed(true);
        r2.setCursed(true);
        b1.mergeRooms(r1, r2);
    }

    void TwoGassed() {
        r1.setGassed(true);
        r2.setGassed(true);
        b1.mergeRooms(r1, r2);
    }

    void GassedAndCursed() {
        r1.setGassed(true);
        r2.setCursed(true);
        b1.mergeRooms(r1, r2);
    }

    void GassedAndGassedCursed() {
        r1.setGassed(true);
        r2.setGassed(true);
        r2.setCursed(true);
        b1.mergeRooms(r1, r2);
    }

    void TwoCursed() {
        r1.setCursed(true);
        r2.setCursed(true);
        b1.mergeRooms(r1, r2);
    }

    void CursedAndGassedCursed() {
        r1.setCursed(true);
        r2.setGassed(true);
        r2.setCursed(true);
        b1.mergeRooms(r1, r2);
    }

    void TwoGassedCursed() {
        r1.setGassed(true);
        r1.setCursed(true);
        r2.setGassed(true);
        r2.setCursed(true);
        b1.mergeRooms(r1, r2);
    }
}
