package game.view;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class MainMenuView extends JPanel {
    private JButton newButton;
    private JLabel playersLabel;
    private JTextField playersTextField;
    private JButton loadButton;
    private JComboBox<String> gameComboBox;

    public MainMenuView() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        // Create components
        newButton = new JButton("NEW");
        playersLabel = new JLabel("Players:");
        playersTextField = new JTextField(5);
        loadButton = new JButton("LOAD");
        gameComboBox = new JComboBox<>();

        // Add components to panel
        gbc.gridy = 1;
        add(newButton, gbc);

        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(playersLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(playersTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(loadButton, gbc);

        gbc.gridy = 4;
        add(gameComboBox, gbc);
    }

    public JButton getNewButton() {
        return newButton;
    }

    public JTextField getPlayersTextField() {
        return playersTextField;
    }

    public JButton getLoadButton() {
        return loadButton;
    }

    public JComboBox<String> getGameComboBox() {
        return gameComboBox;
    }

    // Method to add a DocumentListener to the playersTextField
    public void addPlayersTextFieldListener(DocumentListener listener) {
        playersTextField.getDocument().addDocumentListener(listener);
    }
}
