package game.model.commands.base;

import game.model.commands.iCommand;
import game.model.entities.Character;
import game.model.entities.building.BuildingAI;
import game.model.entities.items.Item;
import game.model.logging.Suttogo;

import static game.model.main.Main.gameEngine;

//pickup <item> <character>
public class Pickup implements iCommand {
    public void execute(String[] cmd) {
        if(cmd.length < 3) {
            Suttogo.error("Too few arguments!");
            return;
        }
        BuildingAI builder = gameEngine.getBuilder();
        Character c = gameEngine.findCharacter(cmd[2]);

        Item i;

        if(c == null){
            Suttogo.error("nem talalhato a keresett karakter!");
            return;
        }else{
            i = gameEngine.getItems().get(cmd[1]);
            if(i == null){
                Suttogo.error("nem talalhato a keresett targy!");
                return;
            }else if(c.getLocation().getItems().contains(i)){
                c.addItem(i);
            }
        }

        Suttogo.info("A szobaban nincs a targy!");
    }

    @Override
    public String getName() {
        return "-----";
    }
}
