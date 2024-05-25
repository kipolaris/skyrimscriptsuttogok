package game.view;

import javax.swing.*;
import java.awt.*;

/**
 * Osztály hibaüzenetek közlésére (felhasználó tájékoztató)
 */
public class InfoView extends JPanel {
    private JLabel infoLabel;

    /**
     * Paraméter nélküli konstruktor.
     */
    public InfoView() {
        setLayout(new BorderLayout());
        setBackground(Color.GRAY);

        infoLabel = new JLabel("", SwingConstants.CENTER);
        infoLabel.setForeground(Color.WHITE);
        infoLabel.setFont(new Font("Arial", Font.BOLD, 14));

        add(infoLabel, BorderLayout.CENTER);

        // Inicializáláskor elrejtjük az értesítést
        setVisible(false);
    }

    /**
     * Értesítés megjelenítése a megadott szöveggel.
     *
     * @param message Az értesítés szövege
     */
    public void showInfo(String message) {
        infoLabel.setText(message);
        setVisible(true);
    }

    /**
     * Értesítés elrejtése.
     */
    public void hideInfo() {
        setVisible(false);
    }

    /**
     * Értesítés megjelenítése adott ideig.
     *
     * @param message Az értesítés szövege
     * @param duration Az értesítés megjelenítési ideje milliszekundumban
     */
    public void showInfo(String message, int duration) {
        showInfo(message);
        Timer timer = new Timer(duration, e -> hideInfo());
        timer.setRepeats(false);
        timer.start();
    }
}
