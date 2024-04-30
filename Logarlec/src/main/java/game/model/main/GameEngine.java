package game.model.main;

import game.model.commands.*;
import game.model.entities.Cleaner;
import game.model.entities.Professor;
import game.model.entities.Student;
import game.model.entities.building.BuildingAI;
import game.model.entities.building.Door;
import game.model.entities.building.Room;
import game.model.entities.items.Item;
import game.model.entities.items.SlideRule;
import game.model.logging.Suttogo;
import game.model.entities.Character;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;
import java.util.function.Predicate;

@XmlRootElement
public class GameEngine {
    public Character getCurrent() {
        return current;
    }

    private Character current;

    private Queue<Character> currentQueue;

    @XmlElement
    private Map<String, Student> students = new HashMap<>();
    @XmlElement
    private Map<String, Professor> professors = new HashMap<>();

    public Map<String, Cleaner> getCleaners() {
        return cleaners;
    }

    @XmlElement
    private Map<String, Cleaner> cleaners = new HashMap<>();

    private Queue<Queue<Character>> turns;

    private Queue<Character> studentTurns;

    private Queue<Character> aiTurns;

    //iterators ---------------
    private Iterator<Queue<Character>> ct;
    private Iterator<Character> chart;
    @XmlElement
    private Map<String, Item> items = new HashMap<>();
    @XmlElement
    public Map<String, Item> getItems() {
        return items;
    }

    @XmlElement
    private boolean random = false; //<-------------------------------------------------------------------------HERE IS RANDOM
    @XmlElement
    private static int studentID = 0;
    @XmlElement
    private static int professorID = 0;

    public static int getItemID() {
        return itemID++;
    }

    @XmlElement
    private static int itemID = 0;

    public static int getCleanerID() {
        return cleanerID++;
    }
    @XmlElement
    private static int cleanerID = 0;
    @XmlElement
    private BuildingAI builder = new BuildingAI();

    //GETTERS - SETTERS -----------------------------

    public Map<String, Student> getStudents() {
        return students;
    }

    public void setRandom(boolean random) {
        this.random = random;
    }

    public boolean getRandom() {
        return random;
    }

    public static int getStudentID() {
        return studentID++;
    }

    public static int getProfessorID() {
        return professorID++;
    }

    public Map<String, Professor> getProf() {
        return professors;
    }

    public void setProf(Map<String, Professor> newProf) {
        professors = newProf;
    }

    public BuildingAI getBuilder() {
        return builder;
    }

    public boolean isMyTurn(Character c){
        return c.equals(current);
    }

    public boolean areActionsLeft(Character c){
        if(c.getActions() > 0){
            return true;
        }else{
            next();
            return false;
        }
    }

    public void next(){
        if(chart.hasNext()){
            current = chart.next();
            if(currentQueue.equals(aiTurns) && random){
                current.doRound();
            }
        }else{
            nextQueue();
        }
    }

    public void nextQueue(){
        if(ct.hasNext()){
            currentQueue = ct.next();
            chart = currentQueue.iterator();
            next();
        }else{
            if(random){
                Random r = new Random();

                ArrayList<Room> allrooms = new ArrayList<>(builder.getLabyrinth().values());

                int n1 = r.nextInt(allrooms.size())-1;
                int n2 = r.nextInt(allrooms.size())-1;
                int n3 = r.nextInt(allrooms.size())-1;

                Room r1 = allrooms.get(n1);
                Room r2 = allrooms.get(n2);

                Room r3 = allrooms.get(n3);

                Predicate<Boolean> p = (a) -> r.nextInt(2)==1;

                if(p.test(true)){
                    builder.mergeRooms(r1, r2);
                }
                if(p.test(true)){
                    builder.splitRoom(r3);
                }
                ArrayList<Door> alldoors = new ArrayList<>();
                for(Room room : allrooms) {
                    ArrayList<Door> group = room.getDoors();
                    for(Door door : group) {
                        if(!alldoors.contains(door)) {
                            alldoors.add(door);
                            door.setVisible(r.nextInt(1) == 1);
                        }
                    }
                }
            }else{
                //#todo: manuális parancsok mergere és splitre!
            }
            playOnePhase();
        }
    }

    /**
     * inicializálja a játékot
     */
    public void initGame(){
        Suttogo.info("initGame()");
        students = new HashMap<>();
        professors = new HashMap<>();
        builder = new BuildingAI();
        if(random) {
            Main.perform("room 10");
            Main.perform("room 5");
            Main.perform("neighbour Room0 Room1");

            Main.perform("student");
            Main.perform("roomaddchar Student0 Room0");

            Main.perform("professor");
            Main.perform("roomaddchar Professor0 Room1");

            Main.perform("ffp2");
            Main.perform("roomadditem FFP20 Room0");
            Main.perform("sliderule");
            Main.perform("roomadditem SlideRule1 Room1");

            Main.perform("startGame");

            Main.printOut();
        }
    }

    /**
     * Ellenőrzi, hogy kihaltak-e a Studentek
     */
    public boolean studentsExtinct(){
        Suttogo.info("studentsExtinct()");
        Suttogo.info("\treturn boolean");
        return students.isEmpty();
    }

    public void endGame(){
        Suttogo.info("endGame()");
        students.clear();
        professors.clear();
        builder = null;
    }

    public void refresh(){
        Suttogo.info("refresh()");
        students = new HashMap<>();
        professors = new HashMap<>();
        builder = new BuildingAI();
    }

    public void playOnePhase(){
        if(!studentsExtinct()) {
            Suttogo.info("playOnePhase()");

            for(Student s : students.values()){
                s.resetActions();
            }

            turns = new ArrayDeque<>();

            studentTurns = new ArrayDeque<>();
            aiTurns = new ArrayDeque<>();

            turns.add(studentTurns);
            turns.add(aiTurns);

            studentTurns.addAll(students.values());
            aiTurns.addAll(cleaners.values());
            aiTurns.addAll(professors.values());

            ct = turns.iterator();

            chart = studentTurns.iterator();

            nextQueue();
        }else{
            endGame();
            Suttogo.info("You lost!");
        }
    }

    public void studentDied(Student s){
        Suttogo.info("studentDied(Student)");
        students.remove(s);
    }

    public void addStudent(Student s){
        students.put(s.getId(), s);
    }

    public void addProfessor(Professor p){
        professors.put(p.getId(), p);
    }

    public void addCleaner(Cleaner c){cleaners.put(c.getId(), c);}

    public void addItem(Item i){items.put(i.getId(), i);}

    public Character findCharacter(String key){
        HashMap<String, Character> merged = new HashMap<>();

        merged.putAll(students);
        merged.putAll(professors);
        merged.putAll(cleaners);

        return merged.get(key);
    }
}
