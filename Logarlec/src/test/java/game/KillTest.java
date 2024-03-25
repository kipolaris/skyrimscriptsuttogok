package game;

import game.model.entities.Professor;
import game.model.entities.Student;
import game.model.entities.building.*;
import game.model.entities.items.Cups;
import game.model.entities.items.FFP2;
import game.model.entities.items.TVSZ;
import game.model.logging.Suttogo;

public class KillTest {
    Room r1;
    Student s1;
    Professor p1;
    FFP2 f1;
    Cups c1;
    TVSZ t1;

    public void initialize() throws Exception {
        r1 = new Room(1, false, false, null, null, null);
        s1 = new Student();
        p1 = new Professor();
        f1 = new FFP2(true, true, 2, null, s1);

        r1.addCharacter(s1);
        r1.addCharacter(p1);
        s1.setLocation(r1);
        p1.setLocation(r1);
        s1.addItem(f1);
    }
    public void NoProtection() {
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: Professor attacks a defenseless student");
        p1.getLocation().killStudents();
        Suttogo.info("--------------------------------------------------------");
    }

    public void CupsDontBreak() {
        c1 = new Cups(true, true, 2, null, s1);
        s1.addItem(c1);
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: Professor attacks a student who has a cups that break");
        p1.getLocation().killStudents();
        Suttogo.info("--------------------------------------------------------");
    }

    public void CupsBreak() {
        c1 = new Cups(true, true, 1, null, s1);
        s1.addItem(c1);
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: Professor attacks a student who has a cups that don't break");
        p1.getLocation().killStudents();
        Suttogo.info("--------------------------------------------------------");
    }

    public void TVSZDontBreaks() {
        t1 = new TVSZ(true, true, 2, null, s1);
        s1.addItem(t1);
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: Professor attacks a student who has a TVSZ that doesn't break");
        p1.getLocation().killStudents();
        Suttogo.info("--------------------------------------------------------");
    }

    public void TVSZDBreaks() {
        t1 = new TVSZ(true, true, 1, null, s1);
        s1.addItem(t1);
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: Professor attacks a student who has a TVSZ that breaks");
        p1.getLocation().killStudents();
        Suttogo.info("--------------------------------------------------------");
    }

    public void CupsAndTVSZ() {
        c1 = new Cups(true, true, 2, null, s1);
        t1 = new TVSZ(true, true, 2, null, s1);
        s1.addItem(c1);
        s1.addItem(t1);
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: Professor attacks a student who has TVSZ and cups too");
        p1.getLocation().killStudents();
        Suttogo.info("--------------------------------------------------------");
    }
}
