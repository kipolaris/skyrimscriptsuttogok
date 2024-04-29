package game.model.main;

import game.*;
import game.model.commands.Help;
import game.model.commands.iCommand;

import java.util.Scanner;

public class Main {
        public static void main(String[] args) throws Exception {
            //static Deque<Integer> stack = new ArrayDeque<>();
                Scanner sc = new Scanner(System.in);

                HashMap<String, iCommand> map = new HashMap<>();
                map.put("help", new Help());
                /*map.put("list", new List());
                map.put("push", new Push());
                map.put("pop", new Pop());
                map.put("dup", new Dup());
                map.put("read", new Read());
                map.put("write", new Write());
                map.put("+", new Add());
                map.put("-", new Sub());
                map.put("*", new Mult());
                map.put("/", new Div());*/

                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    String[] cmd = line.split(" ");
                    Command command = map.get(cmd[0]);
                    if (command != null) {
                        command.execute(cmd);
                    }
                }
        }
    }
