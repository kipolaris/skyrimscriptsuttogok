package game.model.commands.godmode;

import game.model.commands.iCommand;
import game.model.entities.building.BuildingAI;
import game.model.entities.building.Room;
import game.model.logging.Suttogo;

import static game.model.main.GameMain.gameEngine;

/**
 * Parancs osztály két szoba mergeléséhez
 */
public class MergeRooms implements iCommand {

    //#todo: ez még nincs tesztelve
    @Override
    public void execute(String[] cmd) {
        if (cmd.length < 3) {
            Suttogo.error("Invalid command. Please provide the IDs of the two rooms to be merged.");
            return;
        }

        BuildingAI builder = gameEngine.getBuilder();
        Room room1 = builder.getLabyrinth().get(cmd[1]);
        Room room2 = builder.getLabyrinth().get(cmd[2]);

        if (room1 == null || room2 == null) {
            Suttogo.error("One or both rooms not found!");
        }

        builder.mergeRooms(room1, room2);

        gameEngine.controlBuildingAI();
    }

}
