package game.controller;

import game.model.entities.items.Item;
import game.model.main.GameMain;
import game.view.ItemListView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

/**
 * Control osztály az ItemListView osztályhoz.
 */
public class ItemListController implements ModelListener{
    private ItemListView view;
    private List<Item> model;

    /**
     * Két paraméteres konstruktor.
     *
     * @param view az ItemListView osztály egy példánya
     * @param model egy tárgyakat tartalmazó Map String kulccsal
     */
    public ItemListController(ItemListView view, List<Item> model) {
        this.view = view;
        this.model = model;

        // Add an action listener to handle selection changes
        view.getComboBox().addActionListener(e -> {
            String selectedItem = view.getSelectedItem();
            System.out.println("Selected item: " + selectedItem);
            // Additional logic to handle the selected item
        });
    }

    @Override
    public void onModelChange() {
        view.setItems(model);
    }

    /**
     * Függvény, amely kulcs alapján visszaadja a kiválasztott tárgyat.
     *
     * @return Item
     */
    public Item getSelectedItem(){
        return GameMain.gameEngine.getItem(view.getSelectedItem());
    }
}

