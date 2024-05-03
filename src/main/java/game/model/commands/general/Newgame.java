package game.model.commands.general;

import game.model.commands.iCommand;
import game.model.logging.Suttogo;
import game.model.main.GameMain;

/**
 * Parancs osztály új játék inicializálásához
 */
public class Newgame implements iCommand {/**Új játékot inicializál*/
    @Override
    public void execute(String[] cmd) {
        GameMain.gameEngine.initGame();
        Suttogo.note("New Game loaded. Have fun!");
    }


}
