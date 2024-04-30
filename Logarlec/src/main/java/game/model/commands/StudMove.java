package game.model.commands;

import game.model.entities.Student;

import static game.model.main.Main.gameEngine;

public class StudMove implements iCommand{
    @Override
    public void execute(String[] cmd) {
        Move c = new Move();
        if(gameEngine.getCurrent() instanceof Student){
            Student s = (Student) gameEngine.getCurrent();

            if(gameEngine.areActionsLeft(s)) {

                String[] ns = new String[]{s.getId() + cmd};

                c.execute(ns);
            }
        }

    }

    @Override
    public String getName() {
        return null;
    }
}
