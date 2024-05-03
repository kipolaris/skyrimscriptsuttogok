package game.model.commands.base;

import game.model.commands.iCommand;
import game.model.entities.Character;
import game.model.entities.building.BuildingAI;
import game.model.entities.items.Item;
import game.model.logging.Suttogo;

import static game.model.main.GameMain.gameEngine;

/**
 * Parancs osztály egy tárgy felvételére
 */
public class Pickup implements iCommand {
    /**
     * Az adott karaktert egy tárgy felvételére készteti
     */
    public void execute(String[] cmd) {
        if (cmd.length < 3) {
            Suttogo.error("Too few arguments!");
            return;
        }
        BuildingAI builder = gameEngine.getBuilder();
        Character c = gameEngine.findCharacter(cmd[2]);

        Item i;

        if (c == null) {
            Suttogo.error("There is no such character!");
        } else {
            i = gameEngine.getItems().get(cmd[1]);
            if (i == null) {
                Suttogo.error("There is no such item!");
            } else if (c.getLocation().getItems().contains(i)) {
                if (c.getActions() > 0) {
                    c.addItem(i);
                }

            }
        }


    }

}
