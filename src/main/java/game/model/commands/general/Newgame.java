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
        if (cmd.length < 2) {
            Suttogo.error("Invalid command. Please provide the number of players.");
            return;
        }

        int numberOfPlayers;
        try {
            numberOfPlayers = Integer.parseInt(cmd[1]);
        } catch (NumberFormatException e) {
            Suttogo.error("Invalid number of players. Please provide a valid integer.");
            return;
        }

        GameMain.gameEngine.numberOfPlayers = numberOfPlayers;
        GameMain.gameEngine.initGame();
        Suttogo.note("New Game loaded with " + numberOfPlayers + " players. Have fun!");
    }


}
