package game.model.commands.godmode;

import game.model.commands.iCommand;
import game.model.entities.Professor;
import game.model.entities.items.Item;
import game.model.logging.Suttogo;
import game.model.main.Main;
import game.model.entities.building.Room;


import java.io.Console;
import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;

public class Profpickup implements iCommand {
    @Override
    public void execute(String[] cmd) {
        if(cmd.length < 2) {
            Suttogo.error("Too few arguments!");
            return;
        }
        Map<String, Professor> prof = Main.gameEngine.getProf();
        Room where = prof.get(cmd[1]).getLocation();
        ArrayList<Item> inRoom = where.getItems();
        int i = 0;
        if (Main.gameEngine.getRandom()){
            Random random = new Random();
            i = random.nextInt(inRoom.size());

        }else{
            i = inRoom.size()/2;
        }
        Item picked = inRoom.get(i);
        prof.get(cmd[1]).addItem(picked);
        where.removeItem(picked);
        Main.gameEngine.setProf(prof);
    }

    @Override
    public String getName() {
        return "profpickup";
    }
}
