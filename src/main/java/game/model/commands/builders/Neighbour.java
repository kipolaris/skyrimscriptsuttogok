package game.model.commands.builders;

import game.model.commands.iCommand;
import game.model.entities.building.BuildingAI;
import game.model.entities.building.Door;
import game.model.entities.building.Room;
import game.model.logging.Suttogo;

import static game.model.main.GameMain.gameEngine;

/**
 * Parancs osztály két szoba ajtóval való összekötésére
 */
public class Neighbour implements iCommand {
    @Override
    public void execute(String[] cmd) {/**Két szobát összeköt egy ajtóval*/
        if(cmd.length < 3) {
            Suttogo.error("Too few arguments!");
            return;
        }
        BuildingAI builder = gameEngine.getBuilder();
        Room room1 = builder.getLabyrinth().get(cmd[1]);
        Room room2 = builder.getLabyrinth().get(cmd[2]);

        if(room1 == null){
            Suttogo.note("room1 az null!");
            return;
        }else if(room2 == null){
            Suttogo.note("room2 az null!");
            return;
        }
        boolean visible = true;
        if(cmd.length > 4)  visible = !cmd[4].equals("invisible");
        Door door;
        if(cmd.length > 3) {
            switch (cmd[3]) {
                case ("oneway_tosecond"):
                    door = new Door(room1, room2, false, visible);
                    break;
                case ("oneway_tofirst"):
                    door = new Door(room2, room1, false, visible);
                    break;
                default:
                    door = new Door(room1, room2, true, visible);
                    break;
            }
        }
        else door = new Door(room1, room2, true, visible);
        room1.addDoor(door);
        room2.addDoor(door);
    }

}
