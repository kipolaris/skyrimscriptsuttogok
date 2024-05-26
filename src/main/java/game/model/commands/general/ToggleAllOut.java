package game.model.commands.general;

import game.model.commands.iCommand;
import game.model.logging.Suttogo;
import game.model.main.GameMain;

import static game.model.main.GameMain.gameEngine;

/**
 * ToggleAllOut: az általános, automatikusan nyomtatott állapotfrissítést
 * lehet vele ki-be kapcsolni.
 */
public class ToggleAllOut implements iCommand {
    @Override
    public void execute(String[] cmd) {
        if(GameMain.allOut){
            GameMain.allOut = false;
            gameEngine.getSuttogo().note("allOut funcion off");
        }else{
            GameMain.allOut = true;
            gameEngine.getSuttogo().note("allOut function on");
        }
    }
}
