package game.model.commands;

import game.model.entities.Cleaner;
import game.model.entities.Professor;
import game.model.logging.Suttogo;

import static game.model.main.Main.gameEngine;

public class Cleanermove implements iCommand{
    @Override
    public void execute(String[] cmd) {
        Move c = new Move();
        if(gameEngine.getCurrent() instanceof Cleaner && !gameEngine.getRandom()){
            Cleaner s = (Cleaner) gameEngine.getCurrent();

            //megnézzük, hogy a user valóban ezt a professort gépelte-e be
            if(cmd.length < 2) {
                Suttogo.error("Too few arguments!");
                return;
            }
            Cleaner chosen = gameEngine.getCleaners().get(cmd[1]);

            if(chosen != null && chosen.equals(s) && gameEngine.areActionsLeft(s)) {
                String[] ns = new String[]{s.getId() + cmd};
                c.execute(ns);
            }
        }
    }

    @Override
    public String getName() {
        return "cleanermove";
    }
}
