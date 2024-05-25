package game.view;

import javax.swing.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * View osztály a felhasználói interfész (avagy menü) megjelenítésére.
 */
public class MenuView extends JPanel {
    private JLabel currentStudent = new JLabel("ERROR");

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
        JComboBox<String> itemBox = itemListView.getComboBox();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        addAlignedComponent(currentStudent);
        addAlignedComponent(dropButton);
        addAlignedComponent(pickupButton);
        addAlignedComponent(useButton);
        addAlignedComponent(moveButton);
        addAlignedComponent(skipButton);
        addAlignedComponent(itemBox);

        setComponentsHeight(this);
        setComponentsWidth(this);

    }

    public void setCurrentStudent(String studentName) {
        currentStudent.setText(studentName);
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

    /**
     * Privát függvény, ami beállítja a komponensek magasságát.
     */
    private void setComponentsHeight(Container container) {
        int maxHeight = 0;

        // Maxméret meghatározása
        for (Component comp : container.getComponents()) {
            Dimension preferredSize = comp.getPreferredSize();
            maxHeight = Math.max(maxHeight, preferredSize.height);
        }

        // Méret beállítása minden komponensre
        for (Component comp : container.getComponents()) {
            Dimension size = comp.getPreferredSize();
            size.height = maxHeight;

            comp.setMinimumSize(size);
            comp.setPreferredSize(size);
            comp.setMaximumSize(size);
        }
    }

    /**
     * Privát függvény, ami beállítja a komponensek szélességét.
     */
    private void setComponentsWidth(Container container) {
        int maxWidth = 0;

        // Maxméret meghatározása
        for (Component comp : container.getComponents()) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                int width = button.getFontMetrics(button.getFont()).stringWidth(button.getText()) + 20; // Add padding
                maxWidth = Math.max(maxWidth, width);
            }
        }

        // Méret beállítása minden komponensre
        for (Component comp : container.getComponents()) {
            Dimension size = comp.getPreferredSize();
            size.width = maxWidth+25;

            comp.setMinimumSize(size);
            comp.setPreferredSize(size);
            comp.setMaximumSize(size);
        }
    }

    /**
     * Függvény komponensek elrendezésére
     */
    private void addAlignedComponent(JComponent component) {
        component.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(component);
    }
}

