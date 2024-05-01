package game.model.commands.builders;

import game.model.commands.iCommand;
import game.model.entities.items.SlideRule;
import game.model.main.Main;

public class AddSliderule implements iCommand {
    @Override
    public void execute(String[] cmd) {
        SlideRule sliderule;
        if(cmd.length > 1) {
            switch (cmd[1]) {
                case ("fake"):
                    sliderule = new SlideRule(false, false, 1, null, null, true);
                    Main.gameEngine.addItem(sliderule);
                    break;
                default:
                    sliderule = new SlideRule(false, false, 1, null, null, false);
                    Main.gameEngine.addItem(sliderule);
                    break;
            }
        }
    }

}
