package game.model.commands.general;

import game.model.commands.iCommand;
import game.model.logging.Suttogo;
import game.model.main.GameMain;

public class StartGame implements iCommand {/**Elindítja a játékot*/
    @Override
    public void execute(String[] cmd) {
        if(!GameMain.gameEngine.getStudents().isEmpty() || !GameMain.gameEngine.getProfessors().isEmpty() || !GameMain.gameEngine.getCleaners().isEmpty()) {
            GameMain.gameEngine.playOnePhase();
            GameMain.isGameStarted = true;
        } else{
            Suttogo.error("add some characters to the game!");
        }
    }
}
