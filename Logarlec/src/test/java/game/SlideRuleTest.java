package game;

import game.model.entities.Character;
import game.model.entities.Student;
import game.model.entities.building.Room;
import game.model.entities.items.Item;
import game.model.entities.items.SlideRule;

import java.util.ArrayList;

public class SlideRuleTest {
    private Student student = new Student();
    private Room room;
    private SlideRule slideRule = new SlideRule();
    private ArrayList<Character> characters = new ArrayList<Character>();
    private ArrayList<Item> items = new ArrayList<Item>();

    public void initialize() {
        characters.add(student);
        items.add(slideRule);
        room = new Room(2,false,false,null,items,characters);
    }

    public void slideRuleFound() {
        student.addItem(slideRule);
    }
}
