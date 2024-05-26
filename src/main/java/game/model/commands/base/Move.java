package game.model.commands.base;

import game.model.commands.iCommand;
import game.model.entities.Character;
import game.model.entities.building.BuildingAI;
import game.model.entities.building.Door;
import game.model.entities.building.Room;
import game.model.logging.Suttogo;

import static game.model.main.GameMain.gameEngine;

/**
 * Parancs osztály egy karakter mozgatására
 */
public class Move implements iCommand {
    public void execute(String[] cmd) {/**Mozgatja az adott karaktert*/
        if(cmd.length < 3) {
            Suttogo.getSuttogo().error("Too few arguments!");
            return;
        }
        BuildingAI builder = gameEngine.getBuilder();
        Character c = gameEngine.findCharacter(cmd[1]);

        Room r;

        if(c == null){
            Suttogo.getSuttogo().error("There is no such character!");
        }else{
            if(gameEngine.canIMove(c)) {
                r = builder.getLabyrinth().get(cmd[2]);
                if (r == null) {
                    Suttogo.getSuttogo().error("There is no such room!");
                } else if (c.getLocation().getNeighbours().contains(r)) {
                    Door d = r.getDoorOf(c.getLocation());

                    c.move(d);
                }
            }else{
                Suttogo.getSuttogo().error("You have already moved!");
            }
        }
    }

}
