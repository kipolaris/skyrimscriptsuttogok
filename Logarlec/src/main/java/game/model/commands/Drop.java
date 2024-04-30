package game.model.commands;

import game.model.entities.Character;
import game.model.entities.building.BuildingAI;
import game.model.entities.items.Item;
import game.model.logging.Suttogo;

import static game.model.main.Main.gameEngine;

public class Drop implements iCommand{
    public void execute(String[] cmd) {
        Character c = gameEngine.findCharacter(cmd[1]);
        Item i;

        if(c == null){
            Suttogo.error("nem talalhato a keresett karakter!");
            return;
        }else{
            i = c.getItems().get(cmd[2]);
            if(i == null){
                Suttogo.error("nem talalhato a keresett targy!");
                return;
            }
        }

        c.dropItem(i);
    }

    @Override
    public String getName() {
        return "----";
    }
}
