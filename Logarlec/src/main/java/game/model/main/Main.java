package game.model.main;

import game.model.commands.*;
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

public class Main {
        //#todo: megoldani, hogy a pályaépítő parancsok ne keveredjenek a fő parancsokkal

        public static HashMap<String, iCommand> map = new HashMap<>();

        public static boolean isGameStarted = false;

        public static GameEngine gameEngine = new GameEngine();
        public static void main(String[] args) throws Exception {
                Scanner sc = new Scanner(System.in);

                map.put("use", new Use());
                map.put("skip", new Skip());
                map.put("load", new Load());
                map.put("save", new Save());
                map.put("newgame", new Newgame()); //implementálva
                map.put("runtests", new Runtests());
                map.put("help", new Help());    //implementálva
                map.put("random-go", new RandomGo());   //kész
                map.put("random-nogo", new RandomNogo());   //kész
                map.put("profpickup", new Profpickup());  //elvileg kész, gyakorlatilag nem biztos

                map.put("airfreshener", new AddAirfreshener());
                map.put("camembert", new AddCamembert());
                map.put("cups", new AddCups());
                map.put("ffp2", new AddFFP2());
                map.put("professor", new AddProfessor());
                map.put("rag", new AddRag());
                map.put("room", new AddRoom());
                map.put("sliderule", new AddSliderule());
                map.put("student", new AddStudent());
                map.put("transistor", new AddTransistor());
                map.put("tvsz", new AddTvsz());
                map.put("charadditem", new Charadditem());
                map.put("cleanermove", new Cleanermove());
                map.put("cleanerpickup", new Cleanerpickup());
                map.put("kill", new Kill());
                map.put("neighbour", new Neighbour());
                map.put("pair", new Pair());
                map.put("profmove", new Profmove());
                map.put("roomaddchar", new Roomaddchar());
                map.put("roomadditem", new Roomadditem());

                map.put("drop", new StudDrop());
                map.put("move", new StudMove());
                map.put("pickup", new StudPickup());
                map.put("unpair", new Unpair());
                map.put("startGame", new StartGame());


                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    String[] cmd = line.split(" ");
                    iCommand command = map.get(cmd[0]);
                    if (command != null) {
                        command.execute(cmd);
                    }else{
                            Suttogo.error("command "+ cmd[0] + " does not exist!");
                    }

                    printOut();

                }
        }

        public static void printOut(){

                System.out.println("Rooms:");
                for (Room r : gameEngine.getBuilder().getLabyrinth().values()){
                        System.out.println("\t"+r.getId()+"\n\t\tCharacters:");
                        for(Character c : r.getCharacters()){
                                if(c!=null) {
                                        System.out.println("\t\t\t"+c.getId());
                                        System.out.println("\t\t\t\tparalyzed: " + c.getParalyzed());
                                        System.out.println("\t\t\t\tItems:");
                                        for (Item i : c.getItems().values()) {
                                                System.out.println("\t\t\t\t\t" + i.getId());
                                        }
                                }
                        }
                        System.out.println("\tItems:");
                        for(Item i : r.getItems()){
                                if(i != null) {
                                        System.out.println("\t\t" + i.getId());
                                }
                        }
                }

                //csak ha fut a játék
                if(isGameStarted) {

                        Character c = gameEngine.getCurrent();

                        if (c == null) {
                                Suttogo.error("current is null!");
                                return;
                        }

                        System.out.println("Your (" + c.getId() + ") Neighbours:");

                        for (Room r : c.getLocation().getNeighbours()) {
                                if (r != null) {
                                        System.out.println("\t" + r.getId());
                                }
                        }
                }else{
                        System.out.println("Students:");
                        for(Student c : gameEngine.getStudents().values()){
                                System.out.println('\r'+c.getId());
                        }
                        System.out.println("Professors:");
                        for(Professor c : gameEngine.getProf().values()){
                                System.out.println('\r'+c.getId());
                        }
                        System.out.println("Cleaners:");
                        for(Cleaner c : gameEngine.getCleaners().values()){
                                System.out.println('\r'+c.getId());
                        }
                        System.out.println("Items:");
                        for(Item c : gameEngine.getItems().values()){
                                System.out.println('\r'+c.getId());
                        }
                }
        }

        public static void perform(String c){
                String[] cmd = c.split(" ");
                iCommand command = map.get(cmd[0]);
                if (command != null) {
                        command.execute(cmd);
                }
        }
    }
