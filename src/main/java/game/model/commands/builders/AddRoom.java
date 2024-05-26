package game.model.commands.builders;

import game.model.commands.iCommand;
import game.model.entities.building.Room;

import static game.model.main.GameMain.gameEngine;

/**
 * Parancs osztály egy új szoba felvételére
 */
public class AddRoom implements iCommand {
    @Override
    public void execute(String[] cmd) {/**Létrehoz egy szobát*/
        int capacity = 1;
        if(cmd.length > 1) capacity = Integer.parseInt(cmd[1]);
        boolean isGassed = false;
        boolean isCursed = false;
        if(cmd.length>2){
            isGassed = Boolean.parseBoolean(cmd[2]);
        }
        if(cmd.length>3){
            isCursed = Boolean.parseBoolean(cmd[3]);
        }

        Room r  = new Room(capacity, isGassed, isCursed, null, null, null);
        gameEngine.getBuilder().addRoom(r);
    }

}
