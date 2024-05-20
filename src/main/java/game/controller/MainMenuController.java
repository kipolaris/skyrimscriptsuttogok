package game.controller;

import game.model.main.GameEngine;
import game.model.main.GameMain;
import game.model.main.SaverLoader;
import game.view.MainMenuView;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuController implements ModelListener{
    private MainMenuView view;

    private GameEngine gameEngine;

    private SaverLoader saverLoader = new SaverLoader(GameMain.gameEngine);

    public MainMenuController(MainMenuView view) {
        this.view = view;

        this.gameEngine = GameMain.gameEngine;

        // Add action listeners to buttons
        view.getNewButton().addActionListener(new NewButtonListener());
        view.getLoadButton().addActionListener(new LoadButtonListener());

        // Add document listener to playersTextField
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

        // Populate the game combo box and the textfield
        onModelChange();
    }

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

    @Override
    public void onResizeWindow() {

    }

    class NewButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String playersText = view.getPlayersTextField().getText();
            try {
                int numberOfPlayers = Integer.parseInt(playersText);
                //#todo: ez így tuti ok?
                GameMain.perform("newgame");
                GameMain.perform("startgame");
                System.out.println("New game started with " + numberOfPlayers + " players.");
            } catch (NumberFormatException ex) {
                System.err.println("Invalid number of players: " + playersText);
            }
        }
    }

    class LoadButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedGame = (String) view.getGameComboBox().getSelectedItem();
            if (selectedGame != null) {
                saverLoader.loadGame(selectedGame); // Assume loadGame(String gameName) loads a game
                System.out.println("Game " + selectedGame + " loaded.");
            }
        }
    }

}
