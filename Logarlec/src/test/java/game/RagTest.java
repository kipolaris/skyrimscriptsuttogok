package game;

import game.model.entities.*;
import game.model.entities.Character;
import game.model.entities.building.*;
import game.model.entities.items.*;
import game.model.main.*;
//import org.junit.*;

import java.util.ArrayList;

public class RagTest {
    private GameEngine gameEngine = new GameEngine();
    private Room room;
    private Student student = new Student();
    private Professor professor = new Professor();
    private Rag rag = new Rag();
    ArrayList<Character> characters = new ArrayList<>();


    /*@Before
    void BeforeTest() throws Exception {
        student = new Student();
        student.addItem(rag);
        professor = new Professor();
        room = new Room(2,false,false,null,null,characters);
    }*/

    void initialize() throws Exception {
        characters.add(student);
        characters.add(professor);

        student.addItem(rag);

        room = new Room(2,false,false,null,null,characters);
    }

    //@Test
    void ActivateRag() {
        characters.add(student);
        student.useItem(rag);
        //assertEquals(true,rag.getActivated());

    }
}
