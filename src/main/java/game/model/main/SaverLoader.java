package game.model.main;

import game.controller.ModelListener;
import game.model.AbstractObservableModel;
import game.model.entities.building.BuildingAI;
import game.model.logging.Suttogo;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

/**Osztály, amely a játék elmentéséért és betöltéséért felelős*/
public class SaverLoader extends AbstractObservableModel {
    private GameEngine g;
    private BuildingAI bai;

    private String path = "./saved/";


    /**Egy paraméteres konstruktor*/
    public SaverLoader(GameEngine _g){
        g = _g;
        bai = g.getBuilder();
    }

    /**A játékot elmenti a megadott helyre*/
    public boolean saveGame(String chosen){
        try{
            JAXBContext context = JAXBContext.newInstance(GameEngine.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(g, new File(path+chosen));

            Suttogo.info("Mentes sikeres.");
            notifyEveryone();
            return true;
        }catch(Exception e){
            Suttogo.error("Hiba mentes kozben!");
            return false;
        }
    }

    /**A játékot betölti adott elérési útvonalról*/
    public void loadGame(String chosen){
        try{
            JAXBContext context = JAXBContext.newInstance(GameEngine.class);
            Unmarshaller m = context.createUnmarshaller();
            g = (GameEngine) m.unmarshal(new File(path+chosen));
        }catch(Exception e){
            Suttogo.error("Hiba betoltes kozben!");
            g = null;
        }
    }

    public List<String> getSavedGames() {
        List<String> savedGames = new ArrayList<>();
        File directory = new File(path);

        // Check if the directory exists and is indeed a directory
        if (directory.exists() && directory.isDirectory()) {
            // List all XML files in the directory
            File[] files = directory.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".xml");
                }
            });

            // Add the names of the XML files to the list
            if (files != null) {
                for (File file : files) {
                    savedGames.add(file.getName());
                }
            }
        }

        return savedGames;
    }
}
