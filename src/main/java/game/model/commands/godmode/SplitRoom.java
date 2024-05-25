package game.model.commands.godmode;

import game.model.commands.iCommand;
import game.model.entities.building.BuildingAI;
import game.model.entities.building.Room;
import game.model.logging.Suttogo;

import static game.model.main.GameMain.gameEngine;

/**
 * Parancs osztály egy szoba spliteléséhez
 */
public class SplitRoom implements iCommand {/**Egy szobát splitel*/
    @Override
    public void execute(String[] cmd) {
        BuildingAI builder = gameEngine.getBuilder();
        if (cmd.length > 1) {
            Room r1 = builder.getLabyrinth().get(cmd[1]);
            if(r1 == null) {
                Suttogo.error("One or both rooms are not found!");
            }
            builder.splitRoom(r1);
        }
        else {
            Suttogo.error("Too few arguments!");
        }
        gameEngine.controlBuildingAI();
    }
}
