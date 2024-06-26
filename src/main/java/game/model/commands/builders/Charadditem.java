package game.model.commands.builders;

import game.model.commands.iCommand;
import game.model.entities.building.BuildingAI;
import game.model.entities.items.Item;
import game.model.logging.Suttogo;
import game.model.entities.Character;

import static game.model.main.GameMain.gameEngine;

/**
 * Parancs osztály egy tárgy karakterhez adására
 */
public class Charadditem implements iCommand {
    public void execute(String[] cmd) {/**Hozzáad egy tárgyat egy karakterhez*/
        if(cmd.length < 3) {
            Suttogo.getSuttogo().error("Too few arguments!");
            return;
        }
        Item i = gameEngine.getItems().get(cmd[1]);
        BuildingAI builder = gameEngine.getBuilder();
        Character c = gameEngine.findCharacter(cmd[2]);

        if(i == null){
            Suttogo.getSuttogo().error("There is no such item!");
            return;
        }else if(c == null){
            Suttogo.getSuttogo().error("There is no such character!");
            return;
        }

        c.addItem(i);
        c.addActions(1);
    }

}
