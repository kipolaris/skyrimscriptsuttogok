package game.controller;

import game.model.entities.items.Item;
import game.view.ItemListView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class ItemListController implements ModelListener{
    private ItemListView view;
    private Map<String, Item> model;

    public ItemListController(ItemListView view, Map<String, Item> model) {
        this.view = view;
        this.model = model;

        // Add an action listener to handle selection changes
        view.getComboBox().addActionListener(e -> {
            String selectedItem = view.getSelectedItem();
            System.out.println("Selected item: " + selectedItem);
            // Additional logic to handle the selected item
        });
    }

    // Method to refresh the item list view
    public void onModelChange() {
        view.setItems((List<Item>) model.values());
    }

    public Item getSelectedItem(){
        return model.get(view.getSelectedItem());
    }
}

