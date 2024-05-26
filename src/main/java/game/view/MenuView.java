package game.view;

import game.model.logging.Suttogo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * View osztály a felhasználói interfész (avagy menü) megjelenítésére.
 */
public class MenuView extends JPanel {    
    private JPanel info;
    private JLabel currentCharacter = new JLabel("ERROR");

    private JButton dropButton;
    private JButton pickupButton;
    private JButton useButton;
    private JButton moveButton;
    private JButton skipButton;
    private ItemListView itemListView;
    private JLabel actions;

    /**
     * Paraméter nélküli konstruktor.
     *
     * <p>Beállítja a gombokat, amivel a felhasználó tud majd parancsokat közölni a játéknak.</p>
     */
    public MenuView() {
        setLayout(new BorderLayout());

        dropButton = new JButton("Drop");
        pickupButton = new JButton("Pickup");
        useButton = new JButton("Use");
        moveButton = new JButton("Move");
        skipButton = new JButton("Skip");

        itemListView = new ItemListView();
        JComboBox<String> itemBox = itemListView.getComboBox();

        info = new JPanel(new BorderLayout());
        actions = new JLabel();

        JScrollPane scrollPane = new JScrollPane(info);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getViewport().setPreferredSize(new Dimension(100, 60));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        addAlignedComponent(mainPanel, currentCharacter);
        addAlignedComponent(mainPanel, dropButton);
        addAlignedComponent(mainPanel, pickupButton);
        addAlignedComponent(mainPanel, useButton);
        addAlignedComponent(mainPanel, moveButton);
        addAlignedComponent(mainPanel, skipButton);
        addAlignedComponent(mainPanel, actions);
        addAlignedComponent(mainPanel, itemBox);

        setComponentsHeight(mainPanel);
        setComponentsWidth(mainPanel);

        add(scrollPane, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }

    public void setCurrentStudent(String studentName) {
        currentCharacter.setText(studentName);
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
        // Méret beállítása minden komponensre
        for (Component comp : container.getComponents()) {
            Dimension size = comp.getPreferredSize();
            size.width = 100;

            comp.setMinimumSize(size);
            comp.setPreferredSize(size);
            comp.setMaximumSize(size);
        }
    }

    /**
     * Függvény komponensek elrendezésére
     */
    private void addAlignedComponent(JPanel panel, JComponent component) {
        component.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(component);
    }


    /**
     * Függvény akciópontok kiírásához.
     */
    public void setActionPoints(String s) {
        actions.setText(s);
    }
    public void setInfo(JPanel in){
        // Remove the old info panel if it exists
        remove(info);

        // Set the new info panel
        info = in;
        info.setMaximumSize(new Dimension(Integer.MAX_VALUE, info.getPreferredSize().height));

        JScrollPane scrollPane = new JScrollPane(info);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getViewport().setPreferredSize(new Dimension(100, 60));

        // Add the new info panel to the MenuView
        add(scrollPane, BorderLayout.NORTH);

        // Refresh the layout
        revalidate();
        repaint();
    }
}

