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
     * @param path a megadott útvonal a köv felépítésben pl.: "/src/pics/standard_door.png",
     * a kód a / karaktereket az oprendszer által használt elválasztóra cseréli. De csak azokat!
     * Egyébként az a legjobb, ha a getDirectorySeparator() metódust használod.
     *
     * @Warning A metódus nem ellenőrzi, hogy a kép létezik-e a megadott helyen!
     * @Warning A metódus nem ellenőrzi, hogy a megadott útvonal helyes-e!
     * @Warning A metódus nem ellenőrzi, hogy a munkakkönyvtárban van-e a kép! Figyelj hogy honnan indulsz ki!
     * @return A kép abszolút elérési útvonala.
     */
    public static String appendAbsolutePath(String path) {
        path = path.replace("/", getDirectorySeparator()); //a biztonság kedvéért
        String absolutePath = System.getProperty("user.dir") + getDirectorySeparator() + path;
        return absolutePath;
    }

    public static String getDirectorySeparator() {
        return File.separator;
    }
}
