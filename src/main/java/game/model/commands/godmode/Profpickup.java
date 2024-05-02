package game.model.commands.godmode;

import game.model.commands.iCommand;
import game.model.entities.Professor;
import game.model.entities.items.Item;
import game.model.logging.Suttogo;
import game.model.main.GameMain;
import game.model.entities.building.Room;


import java.util.ArrayList;
import java.util.Random;
import java.util.Map;

public class Profpickup implements iCommand {
    @Override
    public void execute(String[] cmd) {/**Egy professzort egy tárgy felvételére késztet*/
        if(cmd.length < 2) {
            Suttogo.error("Too few arguments!");
            return;
        }
        Map<String, Professor> prof = GameMain.gameEngine.getProfessors();
        Room where = prof.get(cmd[1]).getLocation();
        ArrayList<Item> inRoom = where.getItems();
        int i = 0;
        if (GameMain.gameEngine.getRandom()){
            Random random = new Random();
            i = random.nextInt(inRoom.size());

        }else{
            i = inRoom.size()/2;
        }
        Item picked = inRoom.get(i);
        prof.get(cmd[1]).addItem(picked);
        where.removeItem(picked);
        GameMain.gameEngine.setProf(prof);
    }
}
