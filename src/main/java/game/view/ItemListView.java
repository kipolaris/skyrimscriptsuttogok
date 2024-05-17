package game.view;

import game.model.entities.items.Item;

import javax.swing.*;
import java.util.List;

public class ItemListView {
    private JComboBox<String> itemComboBox;

    public ItemListView() {
        itemComboBox = new JComboBox<>();
    }

    // Method to populate the JComboBox with items
    public void setItems(List<Item> items) {
        itemComboBox.removeAllItems();
        for (Item item : items) {
            itemComboBox.addItem(item.getId());
        }
    }

    // Method to get the selected item
    public String getSelectedItem() {
        return (String) itemComboBox.getSelectedItem();
    }

    // Method to get the JComboBox for adding to the UI
    public JComboBox<String> getComboBox() {
        return itemComboBox;
    }
}

