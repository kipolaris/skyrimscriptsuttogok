package game;

import game.model.entities.Character;
import game.model.entities.building.*;
import game.model.entities.items.FFP2;
import game.model.entities.items.TVSZ;

public class GasTest {
    Door d1;
    Character c1;
    FFP2 f1;
    Room r1, r2;

    void initialize() throws Exception {
        r1 = new Room(1, false, false, null, null, null);
        r2 = new Room(1, true, false, null, null, null);
        c1 = new Character();
        d1 = new Door(r1, r2, true, true);
        f1 = new FFP2(); //#todo

        r1.addDoor(d1);
        r2.addDoor(d1);
        r1.addCharacter(c1);
        c1.setLocation(r1);
        f1.setOwner(c1);
        c1.addItem(f1);
    }

    void MaskDoesntBreak() {
        c1.move(d1);
    }

    void MaskBreaks() {
        f1 = new FFP2(); //#todo
        c1.move(d1);
    }

    void NoMask() {
        c1.dropItem(f1);
        c1.addItem(new TVSZ());
        c1.move(d1);
    }
}
