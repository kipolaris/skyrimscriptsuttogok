package game;

import game.model.entities.Character;
import game.model.entities.building.*;
import game.model.entities.items.Cups;
import game.model.entities.items.FFP2;
import game.model.logging.Suttogo;

public class InventoryTest {
    Character c1;
    Room r1;
    FFP2 f1;

    void initialize() throws Exception {
        r1 = new Room(1, false, false, null, null, null);
        c1 = new Character();
        f1 = new FFP2(true, true, 2, r1, null);

        r1.addCharacter(c1);
        c1.setLocation(r1);
        r1.addItem(f1);
    }

    void PicksUp() {
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: Picking up an item");
        c1.addItem(f1);
        Suttogo.info("--------------------------------------------------------");
    }

    void CantPickUp() {
        c1.addItem(new Cups(true, true, 2, null, c1));
        c1.addItem(new Cups(true, true, 2, null, c1));
        c1.addItem(new Cups(true, true, 2, null, c1));
        c1.addItem(new Cups(true, true, 2, null, c1));
        c1.addItem(new Cups(true, true, 2, null, c1));
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: Inventory full");
        c1.addItem(f1);
        Suttogo.info("--------------------------------------------------------");
    }

    void Drops() {
        f1.setLocation(null);
        r1.removeItem(f1);
        f1.setOwner(c1);
        c1.addItem(f1);
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: Dropping an item");
        c1.dropItem(f1);
        Suttogo.info("--------------------------------------------------------");
    }
}
