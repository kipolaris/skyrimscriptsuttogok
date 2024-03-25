package game;

import game.model.entities.*;
import game.model.entities.Character;
import game.model.entities.building.*;
import game.model.entities.items.*;
import game.model.logging.Suttogo;

import java.util.ArrayList;

public class RagTest {
    private Room room;
    private Student student = new Student();
    private Professor professor = new Professor();
    private Rag rag = new Rag(false, false, 2, null, student);
    ArrayList<Character> characters = new ArrayList<>();

    void initialize() {
        characters.add(student);
        student.addItem(rag);

        room = new Room(2,false,false,null,null,characters);
    }

    void ActivateRag() {
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: Placing a rag");
        student.useItem(rag);
        Suttogo.info("--------------------------------------------------------");
    }

    void decRagParProf() {
        characters.add(professor);
        rag.setDurability(1);
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: A rag paralyses a professor then breaks");
        student.useItem(rag);
        Suttogo.info("--------------------------------------------------------");
    }

    void destroyRag() {
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: A rag breaks");
        while(0 < rag.getDurability()) {
            rag.decreaseDurability();
        }
        Suttogo.info("--------------------------------------------------------");
    }

    void parProf() {
        characters.add(professor);
        Suttogo.info("--------------------------------------------------------");
        Suttogo.info("TEST: A rag paralyses a professor but doesn't break");
        student.useItem(rag);
        Suttogo.info("--------------------------------------------------------");
    }
}
