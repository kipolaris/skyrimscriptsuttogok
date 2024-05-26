package game.controller;

import game.model.entities.items.Item;
import game.model.logging.Suttogo;
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
    }

    @Override
    public void onModelChange() {
        view.setItems(model);
    }

    public ItemListView getItemListView() { return view; }

    /**
     * Függvény, amely kulcs alapján visszaadja a kiválasztott tárgyat.
     *
     * @return Item
     */
    public Item getSelectedItem(){
        return GameMain.gameEngine.getItem(view.getSelectedItem());
    }

    public void setItems(List<Item> items){
        this.model = items;
    }
}

