package game.model.main;

import game.model.entities.building.BuildingAI;
import game.model.logging.Suttogo;

import javax.xml.bind.*;
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
    public boolean saveGame(String name){
        try {
            JAXBContext context = JAXBContext.newInstance(GameEngine.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            String filePath = "src/main/java/game/model/main/games/" + name;
            Suttogo.note(filePath);
            m.marshal(g, new File(filePath));

            Suttogo.info("Mentes sikeres.");
            return true;
        }catch (JAXBException e) {
            Suttogo.error("JAXB hiba betöltés közben: " + e.getMessage());
            e.printStackTrace(); // hozzáadva
            g = null;
            return false;
        }catch(Exception e){
            Suttogo.error("Hiba mentes kozben!");
            return false;
        }
    }

    /**A játékot betölti adott elérési útvonalról*/
    public void loadGame(String name){
        try{
            JAXBContext context = JAXBContext.newInstance(GameEngine.class);
            Unmarshaller m = context.createUnmarshaller();
            String filePath = "src/main/java/game/model/main/games/" + name;
            g = (GameEngine) m.unmarshal(new File(filePath));
        }catch(Exception e){
            Suttogo.error("Hiba betoltes kozben!");
            g = null;
        }
    }

}
