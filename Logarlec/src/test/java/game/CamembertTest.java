package game;

import game.model.entities.*;
import game.model.entities.Character;
import game.model.entities.building.Room;
import game.model.entities.items.Camembert;
import game.model.logging.Suttogo;

import java.util.ArrayList;

public class CamembertTest {
    private Room room;
    private Student student = new Student();
    private Camembert camembert = new Camembert(true, true, 2, null, student);
    private ArrayList<Character> characters = new ArrayList<Character>();

    public void initialize() {
        characters.add(student);
        room = new Room(2,false,false,null,null,characters);
        room.addCharacter(student);
        student.addItem(camembert);
    }

    public void useCamembert() {
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: Camembert");
        student.useItem(camembert);
        Suttogo.info("--------------------------------------------------------");
    }
}
