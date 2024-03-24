package game;

import game.model.entities.Professor;
import game.model.entities.Student;
import game.model.entities.building.*;
import game.model.entities.items.Cups;
import game.model.entities.items.FFP2;
import game.model.entities.items.TVSZ;

public class KillTest {
    Room r1;
    Student s1;
    Professor p1;
    FFP2 f1;
    Cups c1;
    TVSZ t1;

    void initialize() throws Exception {
        r1 = new Room(1, false, false, null, null, null);
        s1 = new Student();
        p1 = new Professor();
        f1 = new FFP2(); //#todo

        r1.addCharacter(s1);
        r1.addCharacter(p1);
        s1.setLocation(r1);
        p1.setLocation(r1);
    }
    void NoProtection() {
        s1.addItem(f1);
        f1.setOwner(s1);
        p1.getLocation().killStudents();
    }

    void CupsDontBreak() {
        c1 = new Cups(); //#todo
        s1.addItem(c1);
        f1.setOwner(s1);
        p1.getLocation().killStudents();
    }

    void CupsBreak() {
        c1 = new Cups(); //#todo
        s1.addItem(f1);
        f1.setOwner(s1);
        p1.getLocation().killStudents();
    }

    void TVSZDontBreaks() {
        t1 = new TVSZ(); //#todo
        s1.addItem(f1);
        f1.setOwner(s1);
        p1.getLocation().killStudents();
    }

    void TVSZDBreaks() {
        t1 = new TVSZ(); //#todo
        s1.addItem(f1);
        f1.setOwner(s1);
        p1.getLocation().killStudents();
    }

    void CupsAndTVSZ() {
        c1 = new Cups(); //#todo
        t1 = new TVSZ(); //#todo
        s1.addItem(f1);
        f1.setOwner(s1);
        p1.getLocation().killStudents();
    }
}
