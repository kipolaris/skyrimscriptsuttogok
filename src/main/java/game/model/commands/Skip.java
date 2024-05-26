package game.model.commands;

import game.model.entities.Cleaner;
import game.model.entities.Professor;
import game.model.entities.Student;
import game.model.logging.Suttogo;
import game.model.main.GameEngine;
import game.model.main.GameMain;

import java.util.Map;

/**
 * Parancs osztály karakterek körének passzolására
 */
public class Skip implements iCommand{
    @Override
    public void execute(String[] cmd) {
        GameMain.gameEngine.getCurrent().skipTurn();
    }
}
