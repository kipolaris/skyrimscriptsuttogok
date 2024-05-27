package game.view;

import game.model.logging.Suttogo;

import java.io.File;

/**
 * A képek betöltéséért felelős util osztály.
 */
public class ImageLoader {

    private ImageLoader() {}

    /**
     * Visszatér a kép abszolút elérési útvonalával.
     * @param path a megadott útvonal a köv felépítésben pl.: "/src/pics/standard_door.png" A '/' fontos!
     * @return
     */
    public static String appendAbsolutePath(String path) {
        path = path.replace("/", getDirectorySeparator()); //a biztonság kedvéért
        String absolutePath = System.getProperty("user.dir") + getDirectorySeparator() + path;
        Suttogo.getSuttogo().note("ImageLoader: appendAbsolutePath: " + absolutePath);
        return absolutePath;
    }

    public static String getDirectorySeparator() {
        return File.separator;
    }
}
