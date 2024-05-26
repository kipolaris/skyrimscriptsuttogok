package game.model.commands.builders;

import static game.model.main.GameMain.gameEngine;

import game.model.commands.iCommand;
import game.model.entities.Character;
import game.model.entities.building.BuildingAI;
import game.model.entities.building.Room;
import game.model.logging.Suttogo;

/**
 * Parancs osztály egy karakter szobába való felvételére
 */
public class Roomaddchar implements iCommand {
    @Override
    public void execute(String[] cmd) {/**Hozzáad egy karaktert egy szobához*/
        if(cmd.length < 3) {
            gameEngine.getSuttogo().error("Too few arguments!");
            return;
        }
        Character c = gameEngine.findCharacter(cmd[1]);
        BuildingAI builder = gameEngine.getBuilder();
        Room r = builder.getLabyrinth().get(cmd[2]);

        if(c == null){
            gameEngine.getSuttogo().error("nem talalhato a keresett karakter!");
            return;
        }else if(r == null){
            gameEngine.getSuttogo().error("nem talalhato a keresett szoba!");
            return;
        }

        r.addCharacter(c);
    }

}
