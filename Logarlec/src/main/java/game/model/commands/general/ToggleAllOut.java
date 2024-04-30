package game.model.commands.general;

import game.model.commands.iCommand;
import game.model.logging.Suttogo;
import game.model.main.Main;

/**
 * ToggleAllOut: az általános, automatikusan nyomtatott állapotfrissítést
 * lehet vele ki-be kapcsolni.
 */
public class ToggleAllOut implements iCommand {
    @Override
    public void execute(String[] cmd) {
        if(Main.allOut){
            Main.allOut = false;
            Suttogo.note("allOut funcion off");
        }else{
            Main.allOut = true;
            Suttogo.note("allOut function on");
        }
    }
}
