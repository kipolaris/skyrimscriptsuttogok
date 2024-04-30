package game.model.commands;

import game.model.entities.Character;
import game.model.entities.building.BuildingAI;
import game.model.entities.building.Room;
import game.model.entities.items.Item;
import game.model.logging.Suttogo;

import static game.model.main.Main.gameEngine;

public class Roomadditem implements iCommand{
    @Override
    public void execute(String[] cmd) {
        if(cmd.length < 3) {
            Suttogo.error("Too few arguments!");
            return;
        }
        Item i = gameEngine.getItems().get(cmd[1]);
        BuildingAI builder = gameEngine.getBuilder();
        Room r = builder.getLabyrinth().get(cmd[2]);

        if(i == null){
            Suttogo.error("nem talalhato a keresett targy!");
            return;
        }else if(r == null){
            Suttogo.error("nem talalhato a keresett szoba!");
            return;
        }

        r.addItem(i);
    }

    @Override
    public String getName() {
        return "roomadditem";
    }
}
