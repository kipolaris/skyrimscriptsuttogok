package game.model.main;

import game.controller.RoomController;
import game.model.commands.*;
import game.model.commands.builders.*;
import game.model.commands.general.*;
import game.model.commands.godmode.*;
import game.model.commands.iCommand;
import game.model.entities.Cleaner;
import game.model.entities.Professor;
import game.model.entities.Student;
import game.model.entities.building.BuildingAI;
import game.model.entities.building.Room;
import game.model.entities.items.Item;
import game.model.logging.Suttogo;

import game.model.entities.Character;
import game.view.GamePanel;

import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**Osztály, amely főmenüként szolgál és a parancsokat kezeli*/
public class GameMain {
    public static int last_character_value = 5;
    public static HashMap<String, iCommand> commandMap = new HashMap<>();

    public static boolean isGameStarted = false;

    public static boolean isGameInitialized = false;

    public static GameEngine gameEngine = new GameEngine();

    public static boolean allOut = true;
    public static GamePanel gamePanel = new GamePanel();

    /**Beállítja az areWeTesting értékét*/
    public static void setAreWeTesting(boolean areWeTesting) {
        GameMain.areWeTesting = areWeTesting;
    }

    public static boolean areWeTesting = false;

    public static String lastOutput = "";

    /**
     * A fejlesztői módot jelző bit
     */
    public static boolean developermode = true;

    /**
     * Azt jelzi grafikus felület esetén, hogy a listenereket beállítottuk-e már.
     * Van egy kezdeti szakasz, amikor errort dob, holott még nincs is hozzáadva listener!
     */
    private static boolean init = true;

    public static boolean isInit() {
        return init;
    }

    public static void setInit(boolean init) {
        GameMain.init = init;
    }

    /**Felveszi a parancsokat egy mapra*/
    public static void addAllCommands(){
        commandMap.put("use", new Use());
        commandMap.put("skip", new Skip());
        commandMap.put("load", new Load());
        commandMap.put("save", new Save());
        commandMap.put("newgame", new Newgame()); //implementálva
        commandMap.put("help", new Help());    //implementálva
        commandMap.put("random-go", new RandomGo());   //kész
        commandMap.put("random-nogo", new RandomNogo());   //kész
        commandMap.put("profpickup", new Profpickup());  //elvileg kész, gyakorlatilag nem biztos

        commandMap.put("airfreshener", new AddAirfreshener());
        commandMap.put("camembert", new AddCamembert());
        commandMap.put("cups", new AddCups());
        commandMap.put("ffp2", new AddFFP2());
        commandMap.put("professor", new AddProfessor());
        commandMap.put("rag", new AddRag());
        commandMap.put("room", new AddRoom());
        commandMap.put("sliderule", new AddSliderule());
        commandMap.put("student", new AddStudent());
        commandMap.put("transistor", new AddTransistor());
        commandMap.put("tvsz", new AddTvsz());
        commandMap.put("charadditem", new Charadditem());
        commandMap.put("cleanermove", new Cleanermove());
        commandMap.put("cleanerpickup", new Cleanerpickup());
        commandMap.put("kill", new Kill());
        commandMap.put("neighbour", new Neighbour());
        commandMap.put("pair", new Pair());
        commandMap.put("pairany", new PairAny());
        commandMap.put("profmove", new Profmove());
        commandMap.put("roomaddchar", new Roomaddchar());
        commandMap.put("roomadditem", new Roomadditem());
        commandMap.put("cleaner", new AddCleaner());

        commandMap.put("drop", new StudDrop());
        commandMap.put("move", new StudMove());
        commandMap.put("pickup", new StudPickup());
        commandMap.put("unpair", new Unpair());
        commandMap.put("out", new Out());
        commandMap.put("startgame", new StartGame());
        commandMap.put("merge", new MergeRooms());
        commandMap.put("split", new SplitRoom());
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Játék mód: 0");
        System.out.println("Fejlesztői mód: 1");
        System.out.println("Játékpálya betöltése scriptből: 2");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        addAllCommands();
        if(input == 1) {
            developermode = true;
            mainLoop();
        }
        else if(input == 0){
            setInit(true); //beállítjuk, hogy az elején ne jelezzen hibát, hogy nincs listener, amíg amúgyse gond
            gamePanel.menu();
            developermode = false;
        }else if(input == 2){
            developermode = true;
            executeScript();
            printOut();
            mainLoop();
        }else{
            System.out.println("Nem megfelelő bemenet!");
        }
    }

    private static void mainLoop(){
        Suttogo.getSuttogo().info("SYSTEM game CLI started, start typing commands");

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] cmd = line.split(" ");
            iCommand command = commandMap.get(cmd[0]);
            if (command != null) {
                command.execute(cmd);
            } else {
                Suttogo.getSuttogo().note("command " + cmd[0] + " does not exist!");
            }

            if(allOut && isGameInitialized){
                printOut();
                GameMain.lastOutput = "";
            }else{
                if(!allOut) Suttogo.getSuttogo().note("Please call newgame command!");
            }

            if (gameEngine.isAInext()) {
                Character current = gameEngine.getCurrent();

                current.doRound();
                Suttogo.getSuttogo().note("Now " + current.getId() + "makes steps");
            }
        }
    }

    /**Kiírja a játék státuszát*/
    public static void printOut() {
        StringBuilder sb = new StringBuilder();
        if(isGameStarted) {
            sb.append("--------------------\n");
            sb.append("Rooms:\n");
            BuildingAI builder = gameEngine.getBuilder();
            if(builder!=null) {
                for (Room r : builder.getLabyrinth().values()) {
                    sb.append("\t").append(r.getId()).append("\n\t\tCharacters:\n");
                    for (Character c : r.getCharacters()) {
                        if (c != null) {
                            sb.append("\t\t\t").append(c.getId()).append('\n');
                            sb.append("\t\t\t\tparalyzed: ").append(c.getParalyzed()).append('\n');
                            if (c instanceof Student)
                                sb.append("\t\t\t\tactions: ").append(c.getActions()).append('\n');
                            sb.append("\t\t\t\tItems:\n");
                            for (Item i : c.getItems().values()) {
                                sb.append("\t\t\t\t\t").append(i.getId()).append('\n');
                            }
                        }
                    }
                    sb.append("\t\tItems:\n");
                    for (Item i : r.getItems()) {
                        if (i != null) {
                            sb.append("\t\t\t").append(i.getId()).append('\n');
                        }
                    }
                }
            }
            else Suttogo.getSuttogo().error("A builder null!!!");

            Character c = gameEngine.getCurrent();

            if (c == null) {
                Suttogo.getSuttogo().error("current is null!");
                return;
            }

            sb.append("Your (").append(c.getId()).append(") Neighbours:\n");

            for (Room r : c.getLocation().getNeighbours()) {
                if (r != null) {
                    sb.append("\t").append(r.getId()).append('\n');
                }
            }

        }else {
            sb.append("Students:\n");
            for (Student st : gameEngine.getStudents().values()) {
                sb.append('\t').append(st.getId()).append('\n');
            }
            sb.append("Professors:\n");
            for (Professor st : gameEngine.getProfessors().values()) {
                sb.append('\t').append(st.getId()).append('\n');
            }
            sb.append("Cleaners:\n");
            for (Cleaner st : gameEngine.getCleaners().values()) {
                sb.append('\t').append(st.getId()).append('\n');
            }
            sb.append("Items:\n");
            for (Item st : gameEngine.getItems().values()) {
                sb.append('\t').append(st.getId()).append('\n');
            }
            sb.append("Rooms:\n");
            for (Room st : gameEngine.getBuilder().getLabyrinth().values()) {
                sb.append('\t').append(st.getId()).append('\n');
            }
        }
        sb.append("--------------------\n");
        lastOutput = lastOutput + sb.toString();
        if(!areWeTesting) System.out.print(lastOutput);
    }

    /**Végrehajt egy parancsot*/
    public static void perform(String c) {
        String[] cmd = c.split(" ");
        iCommand command = commandMap.get(cmd[0]);
        if (command != null) {
            command.execute(cmd);
        }
    }

    /**
     * A scriptes végrehajtás függvénye. A scriptet a src/main/resources/gamebuilder_scripts mappában kell elhelyezni, és a
     * scriptPath változóban kell megadni a fájl nevét.
     * A fájl használata: helyezz bele parancsokat úgy, mint ha a szabványos inputra írnád be őket, minden sor egy parancs.
     * Vigyázz, mert a kezdetektől kell specifikálnod a parancsokat (szóval kell a newgame és a startgame is!)
     * Note: alapértelmezésben a random ki van kapcsolva.
     */

    public static void executeScript(){
        Suttogo.getSuttogo().info("SYSTEM started processing script, please wait...");

        String scriptPath = "src/main/resources/gamebuilder_scripts/script1.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(scriptPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                perform(line);
            }
        } catch (IOException e) {
            Suttogo.getSuttogo().error("Error reading file: " + e.getMessage());
        }

        Suttogo.getSuttogo().info("SYSTEM script processed sucessfully.");
    }
}
