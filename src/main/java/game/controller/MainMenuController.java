package game.controller;

import game.model.main.GameEngine;
import game.model.main.GameMain;
import game.model.main.SaverLoader;
import game.view.GamePanel;
import game.view.MainMenuView;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A főmenü vezérlője, amely kezeli a nézet és a modell közötti kommunikációt.
 */
public class MainMenuController implements ModelListener{
    private MainMenuView view;

    private GameEngine gameEngine;

    private SaverLoader saverLoader = new SaverLoader(GameMain.gameEngine);

    /**
     * Létrehozza a MainMenuController példányát a megadott nézettel.
     *
     * @param view a főmenü nézete
     */
    public MainMenuController(MainMenuView view) {
        this.view = view;

        this.gameEngine = GameMain.gameEngine;

        // Akciófigyelők hozzáadása a gombokhoz
        view.getNewButton().addActionListener(new NewButtonListener());
        view.getLoadButton().addActionListener(new LoadButtonListener());

        // Dokumentumfigyelő hozzáadása a playersTextField-hez
        view.addPlayersTextFieldListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updatePlayers();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updatePlayers();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updatePlayers();
            }
        });

        // A játék választó lista és a szövegmező feltöltése
        onModelChange();
    }

    /**
     * Frissíti a játékosok számát a nézet alapján.
     */
    private void updatePlayers() {
        String playersText = view.getPlayersTextField().getText();
        try {
            int numberOfPlayers = Integer.parseInt(playersText);
            gameEngine.numberOfPlayers = numberOfPlayers;
            System.out.println("Number of players set to " + numberOfPlayers);
        } catch (NumberFormatException ex) {
            System.err.println("Invalid number of players: " + playersText);
        }
    }

    /**
     * Feltölti a játék választó listát a mentett játékokkal.
     */
    private void populateGameComboBox() {
        view.getGameComboBox().removeAllItems();

        for (String game : saverLoader.getSavedGames()) { // Assume getSavedGames() returns a list of saved game names
            view.getGameComboBox().addItem(game);
        }
    }

    @Override
    public void onModelChange() {
        populateGameComboBox();
        view.getPlayersTextField().setText(String.valueOf(gameEngine.numberOfPlayers));
    }

    /**
     * Az új játék gombhoz tartozó akciófigyelő osztály.
     */
    class NewButtonListener implements ActionListener {
        /**
         * Az új játék indítását kezeli.
         *
         * @param e az esemény
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String playersText = view.getPlayersTextField().getText();
            try {
                int numberOfPlayers = Integer.parseInt(playersText);
                GameMain.perform("newgame");
                GameMain.perform("startgame");
                GameMain.gamePanel.gaming();
                GamePanel.getCardLayout().show(GamePanel.getCardPanel(), "play");
                System.out.println("New game started with " + numberOfPlayers + " players.");
            } catch (NumberFormatException ex) {
                System.err.println("Invalid number of players: " + playersText);
            }
        }
    }

    /**
     * A betöltés gombhoz tartozó akciófigyelő osztály.
     */
    class LoadButtonListener implements ActionListener {
        /**
         * A játék betöltését kezeli.
         *
         * @param e az esemény
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedGame = (String) view.getGameComboBox().getSelectedItem();
            if (selectedGame != null) {
                saverLoader.loadGame(selectedGame);
                System.out.println("Game " + selectedGame + " loaded.");
                GameMain.gamePanel.gaming();
            }
        }
    }

}
