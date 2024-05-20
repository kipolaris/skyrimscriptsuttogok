package game.model.main;

import game.model.entities.building.BuildingAI;
import game.model.logging.Suttogo;

import java.io.File;

/**Osztály, amely a játék elmentéséért és betöltéséért felelős*/
public class SaverLoader {
    private GameEngine g;
    private BuildingAI bai;

    /**Egy paraméteres konstruktor*/
    public SaverLoader(GameEngine _g){
        g = _g;
        bai = g.getBuilder();
    }

    /**A játékot elmenti a megadott helyre*/
    public boolean saveGame(String filePath){
        try{
            Suttogo.info("Mentes sikeres.");
            return true;
        }catch(Exception e){
            Suttogo.error("Hiba mentes kozben!");
            return false;
        }
    }

    /**A játékot betölti adott elérési útvonalról*/
    public void loadGame(String filePath){
        try{

        }catch(Exception e){
            Suttogo.error("Hiba betoltes kozben!");
            g = null;
        }
    }

}
