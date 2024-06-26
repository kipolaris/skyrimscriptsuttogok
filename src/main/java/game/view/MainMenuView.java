package game.view;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;

/**
 * A főmenü nézetének megjelenítéséért felelős osztály.
 */
public class MainMenuView extends JPanel {
    private JLabel titleLabel;
    private JButton newButton;
    private JLabel playersLabel;
    private JTextField playersTextField;
    private JButton loadButton;
    private JComboBox<String> gameComboBox;

    /**
     * Konstruktor a MainMenuView osztályhoz.
     * Inicializálja a komponenseket és beállítja az elrendezést.
     */
    public MainMenuView() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        // Komponensek létrehozása
        titleLabel = new JLabel("SLIDERULE");
        newButton = new JButton("NEW");
        newButton.setBackground(Color.GREEN);
        newButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        newButton.setForeground(Color.WHITE);
        newButton.setOpaque(true);
        newButton.setFocusPainted(false);
        playersLabel = new JLabel("Players:");
        playersTextField = new JTextField(5);
        loadButton = new JButton("LOAD");
        loadButton.setBackground(Color.GREEN);
        loadButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        loadButton.setForeground(Color.WHITE);
        loadButton.setOpaque(true);
        loadButton.setFocusPainted(false);
        gameComboBox = new JComboBox<>();

        // Komponensek hozzáadása a panelhez
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

    /**
     * Visszaadja az új játék indítására szolgáló gombot.
     *
     * @return az új játék indítására szolgáló JButton
     */
    public JButton getNewButton() {
        return newButton;
    }

    /**
     * Visszaadja a játékosok számának bevitelére szolgáló szövegmezőt.
     *
     * @return a játékosok számának bevitelére szolgáló JTextField
     */
    public JTextField getPlayersTextField() {
        return playersTextField;
    }

    /**
     * Visszaadja a játék betöltésére szolgáló gombot.
     *
     * @return a játék betöltésére szolgáló JButton
     */
    public JButton getLoadButton() {
        return loadButton;
    }

    /**
     * Visszaadja a mentett játékok kiválasztására szolgáló kombóboxot.
     *
     * @return a mentett játékok kiválasztására szolgáló JComboBox
     */
    public JComboBox<String> getGameComboBox() {
        return gameComboBox;
    }

    public void setGameComboBox(String[] s) {
        // Létrehozzuk a JComboBox-ot a betöltött játékok listájával
        gameComboBox.removeAllItems(); // Eltávolítjuk a meglévő elemeket
        for (String item : s) {
            gameComboBox.addItem(item); // Hozzáadjuk az új elemeket
        }
        repaint();
    }
    /**
     * Hozzáad egy DocumentListener-t a playersTextField-hez.
     *
     * @param listener a hozzáadandó DocumentListener
     */
    public void addPlayersTextFieldListener(DocumentListener listener) {
        playersTextField.getDocument().addDocumentListener(listener);
    }
}
