package game.model.commands.builders;

import game.model.commands.iCommand;
import game.model.entities.items.SlideRule;
import game.model.main.GameMain;

public class AddSliderule implements iCommand {
    @Override
    public void execute(String[] cmd) {
        SlideRule sliderule;
        if(cmd.length > 1) {
            switch (cmd[1]) {
                case ("fake"):
                    sliderule = new SlideRule(false, false, 1, null, null, true);
                    break;
                default:
                    sliderule = new SlideRule(false, false, 1, null, null, false);
                    break;
            }
        }
        else sliderule = new SlideRule(false, false, 1, null, null, false);
        GameMain.gameEngine.addItem(sliderule);
    }

}
