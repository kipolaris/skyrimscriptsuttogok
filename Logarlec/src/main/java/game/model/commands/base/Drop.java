package game.model.commands.base;

import game.model.commands.iCommand;
import game.model.entities.Character;
import game.model.entities.items.Item;
import game.model.logging.Suttogo;

import static game.model.main.Main.gameEngine;

public class Drop implements iCommand {
    public void execute(String[] cmd) {
        if(cmd.length < 3) {
            Suttogo.error("Too few arguments!");
            return;
        }
        Character c = gameEngine.findCharacter(cmd[1]);
        Item i;

        if(c == null){
            Suttogo.error("There is no such character!");
            return;
        }else{
            i = c.getItems().get(cmd[2]);
            if(i == null){
                Suttogo.error("There is no such item!");
                return;
            }
        }

        c.dropItem(i);
    }

}
