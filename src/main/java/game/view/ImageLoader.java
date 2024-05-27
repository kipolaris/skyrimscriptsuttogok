package game.view;

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
        return System.getProperty("user.dir") + path;
    }
}
