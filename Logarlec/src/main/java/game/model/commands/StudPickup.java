package game.model.commands;

import game.model.entities.Student;

import static game.model.main.Main.gameEngine;

public class StudPickup implements iCommand{
    @Override
    public void execute(String[] cmd) {
        Pickup c = new Pickup();
        if(gameEngine.getCurrent() instanceof Student){
            Student s = (Student) gameEngine.getCurrent();

            if(gameEngine.areActionsLeft(s)) {

                String[] ns = new String[]{cmd[0],cmd[1], s.getId()};

                c.execute(ns);
            }
        }

    }

    @Override
    public String getName() {
        return null;
    }
}
