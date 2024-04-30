package game.model.main;

import game.model.commands.*;
import game.model.commands.iCommand;

import java.util.Scanner;
import java.util.HashMap;

public class Main {
        //#todo: megoldani, hogy a pályaépítő parancsok ne keveredjenek a fő parancsokkal

        public static GameEngine gameEngine = new GameEngine();
        public static void main(String[] args) throws Exception {
                Scanner sc = new Scanner(System.in);

                HashMap<String, iCommand> map = new HashMap<>();
                map.put("move", new Move());
                map.put("drop", new Drop());
                map.put("pickup", new Pickup());
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

                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    String[] cmd = line.split(" ");
                    iCommand command = map.get(cmd[0]);
                    if (command != null) {
                        command.execute(cmd);
                    }
                }
        }
    }
