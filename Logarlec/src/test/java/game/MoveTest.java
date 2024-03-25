package game;

import game.model.entities.Character;
import game.model.entities.building.*;
import game.model.logging.Suttogo;

public class MoveTest {
    Door d1;
    Character c1;
    Room r1, r2;

    void initialize() throws Exception {
        r1 = new Room(1, false, false, null, null, null);
        r2 = new Room(1, false, false, null, null, null);
        c1 = new Character();
        d1 = new Door(r1, r2, true, true);

        r1.addDoor(d1);
        r2.addDoor(d1);
        r1.addCharacter(c1);
        c1.setLocation(r1);
    }

    void WorksBothWays() {
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: Using a door from both ways");
        c1.move(d1);
        c1.move(d1);
        Suttogo.info("--------------------------------------------------------");
    }

    void OneWayOnly() {
        d1 = new Door(r1, r2, false, true);
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: Trying to use a oneway door both ways");
        c1.move(d1);
        c1.move(d1);
        Suttogo.info("--------------------------------------------------------");
    }

    void FullRoom() {
        r2.addCharacter(new Character());
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: Trying to get into a room which is full");
        c1.move(d1);
        Suttogo.info("--------------------------------------------------------");
    }
}