package game.model.commands.base;

import game.model.commands.iCommand;
import game.model.entities.Character;
import game.model.entities.items.Item;
import game.model.logging.Suttogo;

import static game.model.main.GameMain.gameEngine;

/**
 * Parancs osztály tárgy eldobására
 */
public class Drop implements iCommand {
    public void execute(String[] cmd) {/**Eldobat egy tárgyat az adott karaktertől*/
        if(cmd.length < 3) {
            Suttogo.getSuttogo().error("Too few arguments!");
            return;
        }
        Character c = gameEngine.findCharacter(cmd[1]);
        Item i;

        if(c == null){
            Suttogo.getSuttogo().error("There is no such character!");
            return;
        }else{
            i = c.getItems().get(cmd[2]);
            if(i == null){
                Suttogo.getSuttogo().error("There is no such item!");
                return;
            }
        }

        c.dropItem(i);
    }

}
