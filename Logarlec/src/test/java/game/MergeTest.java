package game;

import game.model.entities.Student;
import game.model.entities.building.BuildingAI;
import game.model.entities.building.Door;
import game.model.entities.building.Room;
import game.model.entities.items.Transistor;
import game.model.logging.Suttogo;

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
        t1 = new Transistor(false, false, 1, null, null);
        t2 = new Transistor(false, false, 1, null, null);
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
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: Trying to merge two rooms but fail because of exceeding capacity");
        b1.mergeRooms(r1, r2);
        Suttogo.info("--------------------------------------------------------");
    }

    void TwoOrdinary() {
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: Merging two ordinary rooms");
        b1.mergeRooms(r1, r2);
        Suttogo.info("--------------------------------------------------------");
    }

    void OrdinaryAndGassed() {
        r2.setGassed(true);
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: Merging a gassed room with an ordinary room");
        b1.mergeRooms(r1, r2);
        Suttogo.info("--------------------------------------------------------");
    }

    void OrdinaryAndCursed() {
        r2.setCursed(true);
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: Merging a cursed room with an ordinary room");
        b1.mergeRooms(r1, r2);
        Suttogo.info("--------------------------------------------------------");
    }

    void OrdinaryAndGassedCursed() {
        r2.setGassed(true);
        r2.setCursed(true);
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: Merging an ordinary room with a gassed and cursed room");
        b1.mergeRooms(r1, r2);
        Suttogo.info("--------------------------------------------------------");
    }

    void TwoGassed() {
        r1.setGassed(true);
        r2.setGassed(true);
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: Merging two gassed rooms");
        b1.mergeRooms(r1, r2);
        Suttogo.info("--------------------------------------------------------");
    }

    void GassedAndCursed() {
        r1.setGassed(true);
        r2.setCursed(true);
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: Merging a gassed room with a cursed room");
        b1.mergeRooms(r1, r2);
        Suttogo.info("--------------------------------------------------------");
    }

    void GassedAndGassedCursed() {
        r1.setGassed(true);
        r2.setGassed(true);
        r2.setCursed(true);
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: Merging a gassed room with a gassed and cursed room");
        b1.mergeRooms(r1, r2);
        Suttogo.info("--------------------------------------------------------");
    }

    void TwoCursed() {
        r1.setCursed(true);
        r2.setCursed(true);
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: Merging two cursed rooms");
        b1.mergeRooms(r1, r2);
        Suttogo.info("--------------------------------------------------------");
    }

    void CursedAndGassedCursed() {
        r1.setCursed(true);
        r2.setGassed(true);
        r2.setCursed(true);
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: Merging a cursed room with a gassed and cursed room");
        b1.mergeRooms(r1, r2);
        Suttogo.info("--------------------------------------------------------");
    }

    void TwoGassedCursed() {
        r1.setGassed(true);
        r1.setCursed(true);
        r2.setGassed(true);
        r2.setCursed(true);
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: Merging two gassed and cursed rooms");
        b1.mergeRooms(r1, r2);
        Suttogo.info("--------------------------------------------------------");
    }
}
