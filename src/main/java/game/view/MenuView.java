package game.view;

import javax.swing.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * View osztály a felhasználói interfész (avagy menü) megjelenítésére.
 */
public class MenuView extends JPanel {
    private JButton dropButton;
    private JButton pickupButton;
    private JButton useButton;
    private JButton moveButton;
    private JButton skipButton;
    private ItemListView itemListView;

    /**
     * Paraméter nélküli konstruktor.
     *
     * <p>Beállítja a gombokat, amivel a felhasználó tud majd parancsokat közölni a játéknak.</p>
     */
    public MenuView() {
        dropButton = new JButton("Drop");
        pickupButton = new JButton("Pickup");
        useButton = new JButton("Use");
        moveButton = new JButton("Move");
        skipButton = new JButton("Skip");

        itemListView = new ItemListView();

        // Layout the buttons in a vertical box layout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(dropButton);
        add(pickupButton);
        add(useButton);
        add(moveButton);
        add(skipButton);
    }

    /**
     * Függvény, amely visszaadja a tárgyakat megjelenítő osztályt.
     *
     * @return ItemListView
     */
    public ItemListView getItemListView() {
        return itemListView;
    }

    /**
     * Függvény, amely felvesz egy új kontrollert a dropButton-nak
     *
     * @param listener egy ActionListener interfészt implementáló kontroller.
     */
    public void addDropActionListener(ActionListener listener) {
        dropButton.addActionListener(listener);
    }

    /**
     * Függvény, amely felvesz egy új kontrollert a pickupButton-nak
     *
     * @param listener egy ActionListener interfészt implementáló kontroller.
     */
    public void addPickupActionListener(ActionListener listener) {
        pickupButton.addActionListener(listener);
    }

    /**
     * Függvény, amely felvesz egy új kontrollert a useButton-nak
     *
     * @param listener egy ActionListener interfészt implementáló kontroller.
     */
    public void addUseActionListener(ActionListener listener) {
        useButton.addActionListener(listener);
    }

    /**
     * Függvény, amely felvesz egy új kontrollert a moveButton-nak
     *
     * @param listener egy ActionListener interfészt implementáló kontroller.
     */
    public void addMoveActionListener(ActionListener listener) {
        moveButton.addActionListener(listener);
    }

    /**
     * Függvény, amely felvesz egy új kontrollert a skipButton-nak
     *
     * @param listener egy ActionListener interfészt implementáló kontroller.
     */
    public void addSkipActionListener(ActionListener listener) {
        skipButton.addActionListener(listener);
    }
}

