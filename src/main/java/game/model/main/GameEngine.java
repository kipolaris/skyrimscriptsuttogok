package game.model.main;

import game.controller.ModelListener;
import game.model.entities.Cleaner;
import game.model.entities.Professor;
import game.model.entities.Student;
import game.model.entities.building.BuildingAI;
import game.model.entities.building.Door;
import game.model.entities.building.Room;
import game.model.entities.items.Item;
import game.model.logging.Suttogo;
import game.model.entities.Character;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;
import java.util.function.Predicate;

/**Osztály, amely a játékot megvalósítja*/
@XmlRootElement
public class GameEngine {
    public List<ModelListener> listeners = new ArrayList<>();

    /**Visszaadja a current értékét*/
    public Character getCurrent() {
        return current;
    }

    private Character current = null;

    private Queue<Character> currentQueue = null;

    @XmlElement
    private Map<String, Student> students = null;
    @XmlElement
    private Map<String, Professor> professors = null;

    /**Visszaadja a takarítók egy kulccsal ellátott listáját*/
    public Map<String, Cleaner> getCleaners() {
        return cleaners;
    }

    @XmlElement
    private Map<String, Cleaner> cleaners = null;

    private Queue<Queue<Character>> turns = null;

    private Queue<Character> studentTurns = null;

    private Queue<Character> aiTurns = null;

    /**Visszaadja az isAInext értékét*/
    public boolean isAInext() {
        return isAInext;
    }

    private boolean isAInext = false;

    //iterators ---------------
    private Iterator<Queue<Character>> ct;
    private Iterator<Character> chart;
    @XmlElement
    private Map<String, Item> items = new HashMap<>();

    /**Visszaadja a tárgyak egy kulccsal ellátott listáját*/
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

    /**Visszaad egy egyedi tárgy azonosítót*/
    public static int getItemID() {
        return itemID++;
    }

    @XmlElement
    private static int itemID = 0;

    /**Visszaad egy egyedi takarító azonosítót*/
    public static int getCleanerID() {
        return cleanerID++;
    }

    @XmlElement
    private static int cleanerID = 0;
    @XmlElement
    private BuildingAI builder = null;

    //GETTERS - SETTERS -----------------------------

    /**Visszaadja a hallgatók egy kulccsal ellátott listáját*/
    public Map<String, Student> getStudents() {
        return students;
    }

    /**Beállítja a random értékét*/
    public void setRandom(boolean random) {
        this.random = random;
    }

    /**Visszaadja a random értékét*/
    public boolean getRandom() {
        return random;
    }

    /**Visszaad egy egyedi hallgató azonosítót*/
    public static int getStudentID() {
        return studentID++;
    }

    /**Visszaad egy egyedi oktató azonosítót*/
    public static int getProfessorID() {
        return professorID++;
    }

    /**Visszaadja az oktatók egy kulccsal ellátott listáját*/
    public Map<String, Professor> getProfessors() {
        return professors;
    }

    /**Megadja az oktatók kulccsal ellátott listáját*/
    public void setProf(Map<String, Professor> newProf) {
        professors = newProf;
    }

    /**Megadja a takarítók kulccsal ellátott listáját*/
    public void setCleaners(Map<String, Cleaner> newClean) { cleaners = newClean; }

    /**Visszaadja a BuildingAI egy példányát*/
    public BuildingAI getBuilder() {
        return builder;
    }

    /**
     * Ellenőrzi, hogy van-e még akciója a karakternek a körben.
     * Ha nincs, akkor meghívja a next() függvényt.
     *
     * @param c
     * @return
     */
    public boolean areActionsLeft(Character c) {
        if (c.getActions() > 0) {
            if (c.getActions() == 1 && c.isMoved()) {
                next();
                Suttogo.note("next has been called");
            }
            return true;
        } else {
            Suttogo.error("You have no more actions left!");
            return false;
        }
    }

    /**Visszaadja, hogy az adott karakter mozoghat-e még*/
    public boolean canIMove(Character c){
        if(!c.isMoved()){
            if(c.getActions() <= 0){
                next();
                Suttogo.note("next has been called");
            }
            return true;
        }
        return false;
    }

    /**
     * lépteti az aktuális queue következő karakterére az iteratort, beállítja
     * az aktuális karaktert, akinek a köre jön.
     */
    public void next() {
        if (chart.hasNext()) {
            current = chart.next();
            Suttogo.note("current: "+current.getId());
            if (currentQueue.equals(aiTurns)) {
                Suttogo.note("isAInext was set to true");
                isAInext = true;
                if (!random) {
                    Suttogo.note("Now you can step with" + current.getId() + "ai");
                }
            } else {
                isAInext = false;
            }
        } else {
            nextQueue();
            Suttogo.note("switched to next queue");
        }
    }

    /**
     * A Control flow-hoz kell. Átvált Studentekről ai-okra, ha ők jönnek,
     * majd a buildingai-t levezényli.
     */
    public void nextQueue() {
        if (ct.hasNext()) {
            currentQueue = ct.next();
            chart = currentQueue.iterator();
            next();
        } else {
            Suttogo.note("----Building AI comes ---------");
            if (random) {
                Random r = new Random();

                ArrayList<Room> allrooms = new ArrayList<>(builder.getLabyrinth().values());

                int n1 = r.nextInt(allrooms.size());
                int n2 = r.nextInt(allrooms.size());
                int n3 = r.nextInt(allrooms.size());

                Room r1 = allrooms.get(n1);
                Room r2 = allrooms.get(n2);

                Room r3 = allrooms.get(n3);

                Predicate<Boolean> p = (a) -> r.nextInt(2) == 1;

                if (p.test(true)) {
                    builder.mergeRooms(r1, r2);
                }
                if (p.test(true)) {
                    builder.splitRoom(r3);
                }
                ArrayList<Door> alldoors = new ArrayList<>();
                for (Room room : allrooms) {
                    ArrayList<Door> group = room.getDoors();
                    for (Door door : group) {
                        if (!alldoors.contains(door)) {
                            alldoors.add(door);
                            door.setVisible(r.nextInt(1) == 1);
                        }
                    }
                }
            } else {
                //#todo: manuális parancsok mergere és splitre!
            }
            playOnePhase();
        }
    }

    /**
     * inicializálja a játékot, pályaépítő parancsok segítségével, HA a random funkció be van kapcsolva.
     * FIGYELEM! Nem indítja el a játékot! Csak előkészíti / reseteli a változókat!
     * Ahhoz a startgame parancsot használd (vagyis a playOnePhase indítja).
     */
    public void initGame() {
        Suttogo.info("initGame()");
        students = new HashMap<>();
        professors = new HashMap<>();
        cleaners = new HashMap<>();
        builder = new BuildingAI();
        BuildingAI.setRoomID(0);
        studentID = 0;
        professorID = 0;
        itemID = 0;
        cleanerID = 0;

        if (random) {
            GameMain.perform("room 10");
            GameMain.perform("room 5");
            GameMain.perform("neighbour Room0 Room1");

            GameMain.perform("student");
            GameMain.perform("roomaddchar Student0 Room0");

            GameMain.perform("professor");
            GameMain.perform("roomaddchar Professor0 Room1");

            GameMain.perform("ffp2");
            GameMain.perform("roomadditem FFP20 Room0");
            GameMain.perform("sliderule");
            GameMain.perform("roomadditem SlideRule1 Room1");
        }

        GameMain.isGameInitialized = true;
    }

    /**
     * Ellenőrzi, hogy kihaltak-e a Studentek
     */
    public boolean studentsExtinct() {
        Suttogo.info("studentsExtinct()");
        Suttogo.info("\treturn boolean");
        return students.isEmpty();
    }

    /**
     * Leállítja a játékot
     */
    public void endGame() {
        Suttogo.info("endGame()");
        students.clear();
        professors.clear();
        items.clear();
        builder = null;

        GameMain.isGameStarted = false;
        GameMain.isGameInitialized = false;
    }

    /**
     * A játékot újból inicializálja
     */
    public void refresh() {
        Suttogo.info("refresh()");
        students = new HashMap<>();
        professors = new HashMap<>();
        builder = new BuildingAI();
    }

    /**
     * Lejátszik egy kört, inicializálja azt. Ehhez felépít 2 queue-t, egyet a Studentseknek, egyet
     * az aioknak. A 3. a 2 queue queue-je.
     * Ez ahhoz kell, hogy követni tudjuk, hogy mikor kell az ai-ok doRound() függvényét meghívni, ha a random flag
     * aktiválva van.
     */
    public void playOnePhase() {
        if (!studentsExtinct() || !GameMain.isGameStarted) {
            Suttogo.info("playOnePhase()");

            Suttogo.note("-------- new Phase initiated! ------------\n");

            for (Student s : students.values()) {
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
        } else {
            endGame();
            Suttogo.info("You lost!");
        }
    }

    /**
     * Eltávolít egy hallgatót a listából
     */
    public void studentDied(Student s) {
        Suttogo.info("studentDied(Student)");
        students.remove(s);
    }

    /**
     * Felvesz egy hallgatót a listára
     */
    public void addStudent(Student s) {

        students.put(s.getId(), s);
    }

    /**
     * Felvesz egy oktatót a listára
     */
    public void addProfessor(Professor p) {
        professors.put(p.getId(), p);
    }

    /**
     * Felvesz egy takarítót a listára
     */
    public void addCleaner(Cleaner c) {
        cleaners.put(c.getId(), c);
    }

    /**
     * Felvesz egy tárgyat a listára
     */
    public void addItem(Item i) {
        items.put(i.getId(), i);
    }

    /**
     * Visszaad egy karaktert annak megadott kulcsa alapján
     */
    public Character findCharacter(String key) {
        HashMap<String, Character> merged = new HashMap<>();

        merged.putAll(students);
        merged.putAll(professors);
        merged.putAll(cleaners);

        return merged.get(key);
    }

    public void addListener(ModelListener listener){
        listeners.add(listener);
    }

    public void notifyEveryone(){
        if (!listeners.isEmpty()) {
            for(ModelListener l : listeners){
                l.onModelChange();
            }
        }else{
            Suttogo.error("Gameengine: no listeners found");
        }
    }
}
