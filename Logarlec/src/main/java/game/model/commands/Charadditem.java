package game.model.commands;

import game.model.entities.building.BuildingAI;
import game.model.entities.building.Room;
import game.model.entities.items.Item;
import game.model.logging.Suttogo;
import game.model.entities.Character;

import static game.model.main.Main.gameEngine;

public class Charadditem implements iCommand{
    public void execute(String[] cmd) {
        Item i = gameEngine.getItems().get(cmd[1]);
        BuildingAI builder = gameEngine.getBuilder();
        Character c = gameEngine.findCharacter(cmd[2]);

        if(i == null){
            Suttogo.error("nem talalhato a keresett targy!");
            return;
        }else if(c == null){
            Suttogo.error("nem talalhato a keresett karakter!");
            return;
        }

        c.addItem(i);
    }

    @Override
    public String getName() {
        return "charadditem";
    }
}
