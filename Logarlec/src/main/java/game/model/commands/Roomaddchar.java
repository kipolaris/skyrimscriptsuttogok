package game.model.commands;

import static game.model.main.Main.gameEngine;
import game.model.entities.Character;
import game.model.entities.building.BuildingAI;
import game.model.entities.building.Room;
import game.model.logging.Suttogo;

public class Roomaddchar implements iCommand{
    @Override
    public void execute(String[] cmd) {
        Character c = gameEngine.findCharacter(cmd[1]);
        BuildingAI builder = gameEngine.getBuilder();
        Room r = builder.getLabyrinth().get(cmd[2]);

        if(c == null){
            Suttogo.error("nem talalhato a keresett karakter!");
            return;
        }else if(r == null){
            Suttogo.error("nem talalhato a keresett szoba!");
            return;
        }

        r.addCharacter(c);
    }

    @Override
    public String getName() {
        return "roomaddchar";
    }
}
