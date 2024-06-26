package game.model.commands.general;

import game.model.commands.iCommand;
import game.model.logging.Suttogo;
import game.model.main.GameMain;

import static game.model.main.GameMain.gameEngine;

/**
 * Parancs osztály új játék inicializálásához
 */
public class Newgame implements iCommand {/**Új játékot inicializál*/

    @Override
    public void execute(String[] cmd) {
        if (cmd.length < 2) {
            Suttogo.getSuttogo().error("Invalid command. Please provide the number of players.");
            return;
        }

        int numberOfPlayers;
        try {
            numberOfPlayers = Integer.parseInt(cmd[1]);
        } catch (NumberFormatException e) {
            Suttogo.getSuttogo().error("Invalid number of players. Please provide a valid integer.");
            return;
        }

        GameMain.last_character_value=numberOfPlayers;

        GameMain.gameEngine.numberOfPlayers = numberOfPlayers;
        GameMain.gameEngine.initGame();
        Suttogo.getSuttogo().note("New Game loaded with " + numberOfPlayers + " players. Have fun!");
    }


}
