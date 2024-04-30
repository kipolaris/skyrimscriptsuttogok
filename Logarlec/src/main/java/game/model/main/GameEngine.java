package game.model.main;

import game.model.entities.Cleaner;
import game.model.entities.Professor;
import game.model.entities.Student;
import game.model.entities.building.BuildingAI;
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

    private boolean isStudentsTurn;

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
    private boolean random = false;
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
    private BuildingAI builder;
    @XmlElement
    private SlideRule slideRule;

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
                //#todo: eltűnő ajtók...
            }else{
                //#todo: manuális parancsok mergere és splitre!
            }
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
        slideRule = new SlideRule(false, false, 1, null, null, false);
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
        slideRule = null;
    }

    public void refresh(){
        Suttogo.info("refresh()");
        students = new HashMap<>();
        professors = new HashMap<>();
        builder = new BuildingAI();
        slideRule = new SlideRule(false, false, 1, slideRule.getLocation(), null, false);
    }

    public void playOnePhase(){
        //Suttogo.info("playOnePhase()");
        turns = new ArrayDeque<>();

        turns.add(studentTurns);
        turns.add(aiTurns);

        studentTurns.addAll(students.values());
        aiTurns.addAll(cleaners.values());
        aiTurns.addAll(professors.values());

        ct = turns.iterator();

        chart = studentTurns.iterator();

        nextQueue();
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
