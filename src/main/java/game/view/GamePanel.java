package game.view;

import game.controller.CharacterController;
import game.controller.ItemListController;
import game.controller.MenuController;
import game.controller.RoomController;
import game.model.entities.Character;
import game.model.entities.items.Item;
import game.model.main.GameEngine;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * View osztály, amely a játék fő paneljét jeleníti meg.
 */
public class GamePanel extends JPanel {
    private MenuView menuView;
    private CharacterView characterView;
    private ItemListView itemListView;
    private InfoView infoView;

    private MenuController menuController;
    private CharacterController characterController;
    private ItemListController itemListController;

    /**
     * Két paraméteres konstruktor.
     *
     * @param gameEngine a GameEngine osztály egy példánya
     * @param roomController a RoomController osztály egy példánya
     */
    public GamePanel(GameEngine gameEngine, RoomController roomController) {
        // Set layout
        setLayout(new BorderLayout());

        // Create views
        menuView = new MenuView();
        characterView = new CharacterView();
        itemListView = new ItemListView();
        infoView = new InfoView();

        // Add views to the panel
        add(menuView, BorderLayout.WEST);
        add(characterView.getComboBox(), BorderLayout.CENTER);
        add(itemListView.getComboBox(), BorderLayout.EAST);
        add(infoView, BorderLayout.SOUTH);


        // Initialize controllers
        menuController = new MenuController(menuView, gameEngine, roomController);

        // Combine all characters into one map
        ArrayList<Character> allCharacters = new ArrayList<Character>();
        allCharacters.addAll(gameEngine.getStudents().values());
        allCharacters.addAll(gameEngine.getProfessors().values());
        allCharacters.addAll(gameEngine.getCleaners().values());

        characterController = new CharacterController(allCharacters, characterView);
        itemListController = new ItemListController(itemListView, gameEngine.getItems());

        // Update views
        menuController.onModelChange();
        characterController.onModelChange();
        itemListController.onModelChange();
    }

    /**
     * Függvény egy kép betöltésére a megadott fájlútvonalról.
     *
     * @param path a kép fájlútvonala
     * @return a betöltött Image objektum, vagy null, ha a betöltés sikertelen
     */
    private Image loadImage(String path) {
        try {
            return ImageIO.read(Paths.get(path).toFile());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public InfoView getInfoView() {
        return infoView;
    }
}
