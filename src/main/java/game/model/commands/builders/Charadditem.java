package game.model.commands.builders;

import game.model.commands.iCommand;
import game.model.entities.building.BuildingAI;
import game.model.entities.items.Item;
import game.model.logging.Suttogo;
import game.model.entities.Character;

import static game.model.main.GameMain.gameEngine;

public class Charadditem implements iCommand {
    public void execute(String[] cmd) {
        if(cmd.length < 3) {
            Suttogo.error("Too few arguments!");
            return;
        }
        Item i = gameEngine.getItems().get(cmd[1]);
        BuildingAI builder = gameEngine.getBuilder();
        Character c = gameEngine.findCharacter(cmd[2]);

        if(i == null){
            Suttogo.error("There is no such item!");
            return;
        }else if(c == null){
            Suttogo.error("There is no such character!");
            return;
        }

        c.addItem(i);
    }

}
