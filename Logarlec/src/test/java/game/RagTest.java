package game;

import game.model.entities.*;
import game.model.entities.Character;
import game.model.entities.building.*;
import game.model.entities.items.*;
import java.util.ArrayList;

public class RagTest {
    private Room room;
    private Student student = new Student();
    private Professor professor = new Professor();
    private Rag rag = new Rag();
    ArrayList<Character> characters = new ArrayList<>();

    void initialize() {
        characters.add(student);
        student.addItem(rag);

        room = new Room(2,false,false,null,null,characters);
    }

    void ActivateRag() {
        student.useItem(rag);
        System.out.println("Aktivált rag: " + rag.getActivated());
        System.out.println("Tárgyak a szobában: ");
        for(Item i: room.getItems()) {
            System.out.println(i);
        }
    }

    void decRagParProf() {
        characters.add(professor);
        student.useItem(rag);
        System.out.println("Használhatóság: " + rag.getDurability());
        System.out.println("Oktató bénítva: " + professor.getParalyzed());
    }

    void destroyRag() {
        while(rag.getDurability() > 0) {
            rag.decreaseDurability();
            System.out.println("Használhatóság: " + rag.getDurability());
        }
        System.out.println("Rag létezik: " + rag.decreaseDurability());
    }

    void parProf() {
        characters.add(professor);
        student.useItem(rag);
        System.out.println("Oktató bénítva: "+ professor.getParalyzed());
    }
}
