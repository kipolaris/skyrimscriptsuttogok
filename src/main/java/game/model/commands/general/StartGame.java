package game.model.commands.general;

import game.model.commands.iCommand;
import game.model.logging.Suttogo;
import game.model.main.GameMain;

import static game.model.main.GameMain.gameEngine;

/**
 * Parancs osztály a játék indításához
 */
public class StartGame implements iCommand {/**Elindítja a játékot*/
    @Override
    public void execute(String[] cmd) {
        if(!GameMain.gameEngine.getStudents().isEmpty() || !GameMain.gameEngine.getProfessors().isEmpty() || !GameMain.gameEngine.getCleaners().isEmpty()) {
            GameMain.gameEngine.playOnePhase();
            GameMain.isGameStarted = true;
        } else{
            gameEngine.getSuttogo().error("add some characters to the game!");
        }
    }
}
