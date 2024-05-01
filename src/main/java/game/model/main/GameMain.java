package game.model.main;

import game.model.commands.*;
import game.model.commands.builders.*;
import game.model.commands.general.*;
import game.model.commands.godmode.*;
import game.model.commands.iCommand;
import game.model.entities.Cleaner;
import game.model.entities.Professor;
import game.model.entities.Student;
import game.model.entities.building.Room;
import game.model.entities.items.Item;
import game.model.logging.Suttogo;

import game.model.entities.Character;

import java.util.Scanner;
import java.util.HashMap;

public class GameMain {
    //#todo: megoldani, hogy a pályaépítő parancsok ne keveredjenek a fő parancsokkal

    public static HashMap<String, iCommand> commandMap = new HashMap<>();

    public static boolean isGameStarted = false;

    public static GameEngine gameEngine = new GameEngine();

    public static boolean allOut = true;

    public static String lastOutput = "";

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        commandMap.put("use", new Use());
        commandMap.put("skip", new Skip());
        commandMap.put("load", new Load());
        commandMap.put("save", new Save());
        commandMap.put("newgame", new Newgame()); //implementálva
        commandMap.put("runtests", new Runtests());
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
        commandMap.put("profmove", new Profmove());
        commandMap.put("roomaddchar", new Roomaddchar());
        commandMap.put("roomadditem", new Roomadditem());

        commandMap.put("drop", new StudDrop());
        commandMap.put("move", new StudMove());
        commandMap.put("pickup", new StudPickup());
        commandMap.put("unpair", new Unpair());
        commandMap.put("out", new Out());
        commandMap.put("startgame", new StartGame());


        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] cmd = line.split(" ");
            iCommand command = commandMap.get(cmd[0]);
            if (command != null) {
                command.execute(cmd);
            } else {
                Suttogo.error("command " + cmd[0] + " does not exist!");
            }

            if(allOut) printOut();

            if (gameEngine.isAInext()) {
                Character current = gameEngine.getCurrent();

                current.doRound();
                Suttogo.note("Now " + current.getId() + "makes steps");
            }

        }
    }

    public static void printOut() {
        StringBuilder sb = new StringBuilder();
        if(isGameStarted) {
            sb.append("Rooms:\n");
            //#todo ezt a nullpointerexceptiönt megoldani
            for (Room r : gameEngine.getBuilder().getLabyrinth().values()) {
                sb.append("\t").append(r.getId()).append("\n\t\tCharacters:\n");
                for (Character c : r.getCharacters()) {
                    if (c != null) {
                        sb.append("\t\t\t").append(c.getId()).append('\n');
                        sb.append("\t\t\t\tparalyzed: ").append(c.getParalyzed()).append('\n');
                        if (c instanceof Student) sb.append("\t\t\t\tactions: ").append(c.getActions()).append('\n');
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

            Character c = gameEngine.getCurrent();

            if (c == null) {
                Suttogo.error("current is null!");
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
                sb.append('\r').append(st.getId()).append('\n');
            }
            sb.append("Professors:\n");
            for (Professor st : gameEngine.getProf().values()) {
                sb.append('\r').append(st.getId()).append('\n');
            }
            sb.append("Cleaners:\n");
            for (Cleaner st : gameEngine.getCleaners().values()) {
                sb.append('\r').append(st.getId()).append('\n');
            }
            sb.append("Items:\n");
            for (Item st : gameEngine.getItems().values()) {
                sb.append('\r').append(st.getId()).append('\n');
            }
        }
        lastOutput = sb.toString();
        System.out.print(lastOutput);
    }
    //end PrintOut

    public static void perform(String c) {
        String[] cmd = c.split(" ");
        iCommand command = commandMap.get(cmd[0]);
        if (command != null) {
            command.execute(cmd);
        }
    }
}
