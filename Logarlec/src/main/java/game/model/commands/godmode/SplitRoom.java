package game.model.commands.godmode;

import game.model.commands.iCommand;
import game.model.entities.building.BuildingAI;
import game.model.entities.building.Room;
import game.model.logging.Suttogo;

import static game.model.main.Main.gameEngine;

public class SplitRoom implements iCommand {

    @Override
    public void execute(String[] cmd) {
        BuildingAI builder = gameEngine.getBuilder();
        if (cmd.length > 2) {
            Room r1 = builder.getLabyrinth().get(cmd[1]);

            if(r1 == null) {
                Suttogo.error("One or both rooms are not found!");
            }

            builder.splitRoom(r1);

            /**
             * ez már nem kell

            Room roomOriginal = builder.getLabyrinth().get(cmd[1]);

            Room room1 = new Room(roomOriginal.getCapacity(), false, false, null, null, null);
            Room room2 = new Room(roomOriginal.getCapacity(), false, false, null, null, null);

            room1.setGassed(roomOriginal.getGassed());
            room2.setGassed(roomOriginal.getGassed());

            room1.setCursed(roomOriginal.getCursed());
            room2.setCursed(roomOriginal.getCursed());

            room1.setSticky(roomOriginal.getSticky());
            room2.setSticky(roomOriginal.getSticky());


            ArrayList charactersToMove = roomOriginal.getCharacters();
            for (charactersToMove:) {
                
            }
             **/
            
            

        }
        else {
            Suttogo.error("Too few arguments!");
        }
    }

}
