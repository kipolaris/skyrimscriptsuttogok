package game.model.commands;

import game.model.entities.building.Room;

import static game.model.main.Main.gameEngine;

public class AddRoom implements iCommand{
    @Override
    public void execute(String[] cmd) {
        int capacity = Integer.parseInt(cmd[1]);
        boolean isGassed = false;
        boolean isCursed = false;


        if(cmd.length>2){
            isGassed = Boolean.parseBoolean(cmd[2]);
        }
        if(cmd.length>3){
            isCursed = Boolean.parseBoolean(cmd[3]);
        }

        new Room(capacity, isGassed, isCursed, null, null, null);
    }

    @Override
    public String getName() {
        return "room";
    }
}
