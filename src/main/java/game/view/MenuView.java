package game.view;

import javax.swing.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuView extends JPanel {
    private JButton dropButton;
    private JButton pickupButton;
    private JButton useButton;
    private JButton moveButton;
    private JButton skipButton;

    private ItemListView itemListView;

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

    public ItemListView getItemListView() {
        return itemListView;
    }

    // Methods to add action listeners to the buttons
    public void addDropActionListener(ActionListener listener) {
        dropButton.addActionListener(listener);
    }

    public void addPickupActionListener(ActionListener listener) {
        pickupButton.addActionListener(listener);
    }

    public void addUseActionListener(ActionListener listener) {
        useButton.addActionListener(listener);
    }

    public void addMoveActionListener(ActionListener listener) {
        moveButton.addActionListener(listener);
    }

    public void addSkipActionListener(ActionListener listener) {
        skipButton.addActionListener(listener);
    }
}

