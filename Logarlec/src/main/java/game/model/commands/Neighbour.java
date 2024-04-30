package game.model.commands;

import game.model.entities.building.BuildingAI;
import game.model.entities.building.Door;
import game.model.entities.building.Room;

import static game.model.main.Main.gameEngine;

public class Neighbour implements iCommand{
    @Override
    public void execute(String[] cmd) {
        BuildingAI builder = gameEngine.getBuilder();
        Room room1 = builder.getLabyrinth().get(cmd[1]);
        Room room2 = builder.getLabyrinth().get(cmd[2]);
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

    @Override
    public String getName() {
        return "neighbour";
    }
}
