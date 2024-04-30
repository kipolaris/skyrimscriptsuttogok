package game.model.commands.godmode;

import game.model.commands.iCommand;
import game.model.entities.Professor;
import game.model.logging.Suttogo;

import static game.model.main.Main.gameEngine;

public class Kill implements iCommand {
    @Override
    public void execute(String[] cmd) {

        if(gameEngine.getCurrent() instanceof Professor && !gameEngine.getRandom()){
            Professor s = (Professor) gameEngine.getCurrent();

            //megnézzük, hogy a user valóban ezt a professort gépelte-e be
            if(cmd.length < 2) {
                Suttogo.error("Too few arguments!");
                return;
            }
            Professor chosen = gameEngine.getProf().get(cmd[1]);

            if(chosen != null && chosen.equals(s) && gameEngine.areActionsLeft(s)) {
                s.getLocation().killStudents();
            }
        }

    }

    @Override
    public String getName() {
        return "kill";
    }
}
