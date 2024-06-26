package game.model.main;

import game.model.AbstractObservableModel;
import game.model.entities.Cleaner;
import game.model.entities.Professor;
import game.model.entities.Student;
import game.model.entities.building.BuildingAI;
import game.model.entities.building.Door;
import game.model.entities.building.Room;
import game.model.entities.items.Item;
import game.model.logging.Suttogo;
import game.model.entities.Character;

import javax.swing.*;

import java.util.*;
import java.util.function.Predicate;

/**Osztály, amely a játékot megvalósítja*/
public class GameEngine extends AbstractObservableModel {
    private HashMap<String, Character> characters = null;

    /**Visszaadja a current értékét*/
    public Character getCurrent() {
        return current;
    }

    /**
     * Ezáltal lehet a current értékét beállítani
     */
    public void setCurrent(String key){
        current = characters.get(key);
    }
    private Character current = null;

    private Queue<Character> currentQueue = null;


    private Map<String, Student> students = null;

    private Map<String, Professor> professors = null;

    /**Visszaadja a takarítók egy kulccsal ellátott listáját*/
    public Map<String, Cleaner> getCleaners() {
        return cleaners;
    }

    private Map<String, Cleaner> cleaners = null;

    private Queue<Character> aiTurns = null;

    /**Visszaadja az isAInext értékét*/
    public boolean isAInext() {
        return isAInext;
    }

    private boolean isAInext = false;

    //iterators ---------------
    private Iterator<Queue<Character>> ct;
    private Iterator<Character> chart;

    private Map<String, Item> items = new HashMap<>();

    /**Visszaadja a tárgyak egy kulccsal ellátott listáját*/

    public Map<String, Item> getItems() {
        return items;
    }

    private boolean random = true; //<-------------------------------------------------------------------------HERE IS RANDOM

    private static int studentID = 0;

    private static int professorID = 0;

    /**Visszaad egy egyedi tárgy azonosítót*/
    public static int getItemID() {
        return itemID++;
    }

    private static int itemID = 0;

    /**Visszaad egy egyedi takarító azonosítót*/
    public static int getCleanerID() {
        return cleanerID++;
    }

    private static int cleanerID = 0;

    private BuildingAI builder = null;
    
    public static int numberOfPlayers = 1;
    public static int buildingAIcommandsDone = 0;

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

    public Item getItem(String key){
        return items.get(key);
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
                Suttogo.getSuttogo().note("next has been called");
            }
            return true;
        } else {
            Suttogo.getSuttogo().error("You have no more actions left!");
            return false;
        }
    }

    /**Visszaadja, hogy az adott karakter mozoghat-e még*/
    public boolean canIMove(Character c){
        if(!c.isMoved()){
            if(c.getActions() <= 0){
                next();
                Suttogo.getSuttogo().note("next has been called");
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
            Suttogo.getSuttogo().info("current: "+current.getId());
            if (currentQueue.equals(aiTurns)) {
                isAInext = true;
                if (!random) {
                    Suttogo.getSuttogo().note("Now you can step with " + current.getId() + " ai");
                }
            } else {
                isAInext = false;
            }
        } else {
            nextQueue();
            Suttogo.getSuttogo().note("switched to next queue");
        }
        notifyEveryone();
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
            Suttogo.getSuttogo().note("------- Building AI comes ---------");
            if (random) {

                ArrayList<Room> allrooms = new ArrayList<>(builder.getLabyrinth().values());

                if(allrooms.size() > 1) {
                    Random r = new Random();

                    int n1 = 1;
                    int n2 = 1;

                    while (n1 == n2) {
                        n1 = r.nextInt(allrooms.size());
                        n2 = r.nextInt(allrooms.size());
                    }

                    Room r1 = allrooms.get(n1);
                    Room r2 = allrooms.get(n2);

                    //random értétek meghatározására szolgáló predikátum
                    Predicate<Boolean> p = (a) -> r.nextInt(2) == 1;
                    //Predicate<Boolean> p = (a) -> true; determinisztikus lefutásért kommentezd vissza

                    if (p.test(true)) {
                        builder.mergeRooms(r1, r2);

                    }

                    //újra értéket adunk az allroomsnak, mert változott
                    allrooms = new ArrayList<>(builder.getLabyrinth().values());
                    int n3 = r.nextInt(allrooms.size());
                    Room r3 = allrooms.get(n3);

                    if (p.test(true)) {
                        builder.splitRoom(r3);
                    }

                    allrooms = new ArrayList<>(builder.getLabyrinth().values());

                    ArrayList<Door> alldoors = new ArrayList<>();

                    for (Room room : allrooms) {
                        ArrayList<Door> group = room.getDoors();
                        for (Door door : group) {
                            if (!alldoors.contains(door)) {
                                alldoors.add(door);
                                door.setVisible(p.test(true));
                            }
                        }
                    }
                }
            } else {
                buildingAIcommandsDone = 0;
                Suttogo.getSuttogo().info("now you MUST use buildingAI commands twice");
            }
        }
    }

    /**
     * A buildingAI parancsokat vezérli, ha a random ki van kapcsolva.
     */
    public void controlBuildingAI(){
        if(buildingAIcommandsDone < 2){
            Suttogo.getSuttogo().note("buildingAIcommandsDone: "+buildingAIcommandsDone);
            buildingAIcommandsDone++;
            if(buildingAIcommandsDone==2) playOnePhase();
        }else{
            Suttogo.getSuttogo().error("You have already used buildingAI commands twice!");
        }
    }

    /**
     * inicializálja a játékot, pályaépítő parancsok segítségével, HA a random funkció be van kapcsolva.
     * FIGYELEM! Nem indítja el a játékot! Csak előkészíti / reseteli a változókat!
     * Ahhoz a startgame parancsot használd (vagyis a playOnePhase indítja).
     */
    public void initGame() {
        students = new HashMap<>();
        professors = new HashMap<>();
        cleaners = new HashMap<>();
        characters = new HashMap<>();
        builder = new BuildingAI();
        BuildingAI.setRoomID(0);
        studentID = 0;
        professorID = 0;
        itemID = 0;
        cleanerID = 0;

        characters.putAll(students);
        characters.putAll(professors);
        characters.putAll(cleaners);

        if (GameMain.isInit()) {
            // Szobák felvétele
            GameMain.perform("room " + (numberOfPlayers+3)); // Room0
            GameMain.perform("room " + (numberOfPlayers)); // Room1
            GameMain.perform("room " + (numberOfPlayers+1) + " true false"); // Room2
            GameMain.perform("room " + (numberOfPlayers+3) + " false true"); // Room3
            GameMain.perform("room " + (numberOfPlayers)); // Room4
            GameMain.perform("room " + (numberOfPlayers)); // Room5
            GameMain.perform("room " + (numberOfPlayers)); // Room6
            GameMain.perform("room " + (numberOfPlayers+5)); // Room7
            GameMain.perform("room " + (numberOfPlayers+5)); // Room8

            // Szobák összekötése
            GameMain.perform("neighbour Room0 Room1");
            GameMain.perform("neighbour Room0 Room2 oneway_tofirst");
            GameMain.perform("neighbour Room0 Room3");
            GameMain.perform("neighbour Room3 Room2 oneway_tosecond");
            GameMain.perform("neighbour Room3 Room4 bothways invisible");
            GameMain.perform("neighbour Room3 Room5");
            GameMain.perform("neighbour Room6 Room5");

            // Tárgyak felvétele
            GameMain.perform("sliderule"); // SlideRule0
            GameMain.perform("sliderule fake"); // SlideRule1
            GameMain.perform("ffp2 4"); // FFP22
            GameMain.perform("ffp2 3"); // FFP23
            GameMain.perform("ffp2 3"); // FFP24
            GameMain.perform("ffp2 fake"); // FFP25
            GameMain.perform("airfreshener"); // Airfreshener6
            GameMain.perform("airfreshener"); // Airfreshener7
            GameMain.perform("camembert"); // Camembert8
            GameMain.perform("cups 2"); //Cups9
            GameMain.perform("rag"); //Rag10
            GameMain.perform("rag"); //Rag11
            GameMain.perform("rag"); //Rag12
            GameMain.perform("transistor"); //Transistor13
            GameMain.perform("transistor"); //Transistor14
            GameMain.perform("transistor"); //Transistor15
            GameMain.perform("transistor"); //Transistor16
            GameMain.perform("transistor"); //Transistor17
            GameMain.perform("tvsz 4"); //TVSZ18
            GameMain.perform("tvsz 3"); //TVSZ19
            GameMain.perform("tvsz 3"); //TVSZ20
            GameMain.perform("tvsz fake"); //TVSZ21

            // Tárgyak elhelyezése szobákban
            GameMain.perform("roomadditem FFP22 Room0");
            GameMain.perform("roomadditem FFP23 Room0");
            GameMain.perform("roomadditem Airfreshener6 Room0");
            GameMain.perform("roomadditem Cups9 Room0");
            GameMain.perform("roomadditem Rag10 Room0");
            GameMain.perform("roomadditem Transistor13 Room0");
            GameMain.perform("roomadditem TVSZ18 Room0");
            GameMain.perform("roomadditem Rag11 Room1");
            GameMain.perform("roomadditem Airfreshener7 Room1");
            GameMain.perform("roomadditem TVSZ21 Room1");
            GameMain.perform("roomadditem Camembert8 Room1");
            GameMain.perform("roomadditem SlideRule1 Room2");
            GameMain.perform("roomadditem Transistor14 Room2");
            GameMain.perform("roomadditem Rag12 Room2");
            GameMain.perform("roomadditem FFP25 Room3");
            GameMain.perform("roomadditem TVSZ19 Room3");
            GameMain.perform("roomadditem Transistor15 Room3");
            GameMain.perform("roomadditem TVSZ20 Room4");
            GameMain.perform("roomadditem FFP24 Room4");
            GameMain.perform("roomadditem Transistor16 Room4");
            GameMain.perform("roomadditem Transistor17 Room5");
            GameMain.perform("roomadditem SlideRule0 Room6");

            // Oktatók és takarítók felvétele és elhelyezése
            GameMain.perform("professor"); // Professor0
            GameMain.perform("roomaddchar Professor0 Room4");
            GameMain.perform("professor"); // Professor0
            GameMain.perform("roomaddchar Professor1 Room6");
            GameMain.perform("cleaner"); // Cleaner0
            GameMain.perform("roomaddchar Cleaner0 Room5");

            // Játékosok felvétele és elhelyezése
            for(int i = 0; i<numberOfPlayers; i++) {
                GameMain.perform("student");
                GameMain.perform("roomaddchar Student"+i+" Room0");
            }
        }

        characters = new HashMap<>();
        characters.putAll(students);        characters.putAll(professors);
        characters.putAll(cleaners);

        GameMain.isGameInitialized = true;
    }

    /**
     * Ellenőrzi, hogy kihaltak-e a Studentek
     */
    public boolean studentsExtinct() {
        return students.isEmpty();
    }

    /**
     * Leállítja a játékot
     */
    public void endGame() {
        students.clear();
        professors.clear();
        items.clear();
        builder = null;
        int duration = 3000;

        GameMain.isGameStarted = false;
        GameMain.isGameInitialized = false;
        javax.swing.Timer timer = new javax.swing.Timer(duration, e -> GameMain.gamePanel.closeWindow());
        timer.setRepeats(false);
        timer.start();
    }

    /**
     * A játékot újból inicializálja
     */
    public void refresh() {
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

            Suttogo.getSuttogo().note("-------- new Phase initiated! ------------\n");

            for (Character c : characters.values()) {
                c.resetActions();
            }

            Queue<Queue<Character>> turns = new ArrayDeque<>();
            Queue<Character> studentTurns = new ArrayDeque<>();
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
            Suttogo.getSuttogo().info("You lost!");
        }
    }

    /**
     * Eltávolít egy hallgatót a listából
     */
    public void studentDied(Student s) {
        students.remove(s.getId());
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
        merged.putAll(professors);

        return merged.get(key);
    }

    /**
     * Függvény egy tárgy megsemmisítésére.
     *
     * <p>Mikor egy tárgy elkopik, többé már nem használható.</p>
     */
    public void nullifyItem(Item item) {
        if(item.getLocation() != null) { item.getLocation().removeItem(item); }
        if(item.getOwner() != null) { item.getOwner().loseItem(item); }
        if(items.containsValue(item)) { items.remove(item.getId()); }
        notifyEveryone();
    }
}
