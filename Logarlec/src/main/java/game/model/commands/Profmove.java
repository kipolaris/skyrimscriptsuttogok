package game.model.commands;

import game.model.entities.Professor;
import game.model.entities.Student;
import game.model.logging.Suttogo;

import static game.model.main.Main.gameEngine;

public class Profmove implements iCommand{
    @Override
    public void execute(String[] cmd) {
        if(cmd.length < 2) {
            Suttogo.error("Too few arguments!");
            return;
        }
        Move c = new Move();
        if(gameEngine.getCurrent() instanceof Professor && !gameEngine.getRandom()){
            Professor s = (Professor) gameEngine.getCurrent();

            //megnézzük, hogy a user valóban ezt a professort gépelte-e be
            Professor chosen = gameEngine.getProf().get(cmd[1]);

            if(chosen != null && chosen.equals(s) && gameEngine.areActionsLeft(s)) {

                String[] ns = new String[]{s.getId() + cmd};

                c.execute(ns);
            }
        }
    }

    @Override
    public String getName() {
        return "profmove";
    }
}
