package game.model.commands;

import game.model.entities.Character;
import game.model.entities.Professor;
import game.model.entities.Student;
import game.model.entities.building.Room;
import game.model.entities.items.Item;
import game.model.entities.items.Transistor;

import java.lang.ref.Cleaner;

public interface iCommand {
    public void execute();
    public void move(Student s);
    public void drop(Student s);
    public void pickup(Student s);
    public void use(Student s);
    public void skip(Character c);
    public void load(String filepath);
    public void save(String filename);
    public void newgame();
    public void runtests();
    public void help();
    public void randomGo();
    public void randomNogo();
    public void room(String name, int capacity, boolean gassed, boolean cursed);
    public void neighbour(Room r1, Room r2, boolean twoway, boolean invisible);
    public void ffp2(String name, boolean fake, int durability);
    public void tvsz(String name, boolean fake, int durability);
    public void sliderule(String name, boolean fake);
    public void cups(String name, boolean fake);
    public void transistor(String name);
    public void airfreshener(String name);
    public void rag(String name);
    public void camembert(String name);
    public void student(String name, boolean paralyzed);
    public void professor(String name, boolean paralyzed);
    public void cleaner(String name, boolean paralyzed);
    public void roomaddchar(Character c, Room r);
    public void roomadditem(Item i, Room r);
    public void charadditem(Item i, Character c);
    public void pair(Transistor t1, Transistor t2);
    public void unpair(Transistor t);
    public void cleanermove(Cleaner c);
    public void profmove(Professor p);
    public void profpickup(Professor p);
    public void profdrop(Professor p);
    public void cleanerpickup(Cleaner c);
    public void cleanerdrop(Cleaner c);
    public void kill(Professor p);
}
