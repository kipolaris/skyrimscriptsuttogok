package game.view;

import game.model.entities.items.Item;

import javax.swing.*;
import java.util.List;

/**
 * View osztály egy adott tárgyhalmaz megjelenítésére.
 */
public class ItemListView {
    private JComboBox<String> itemComboBox;

    /**
     * Paraméter nélküli konstruktor.
     *
     * <p>Létrehoz egy üres JComboBox-ot (ebbe kerül majd bele a tárgyak egy listája).</p>
     */
    public ItemListView() {
        itemComboBox = new JComboBox<>();
    }

    /**
     * Függvény ami frissíti a itemComboBox tartalmát.
     *
     * @param items tárgyak egy listája
     */
    public void setItems(List<Item> items) {
        itemComboBox.removeAllItems();
        for (Item item : items) {
            itemComboBox.addItem(item.getId());
        }
    }

    /**
     * Függvény, amely visszaadja az itemComboBox kiválasztott elemét.
     *
     * @return String
     */
    public String getSelectedItem() {
        return (String) itemComboBox.getSelectedItem();
    }

    /**
     * Függvény, amely visszaadja a tárgyakat tartalmazó JComboBox-ot.
     *
     * @return JComboBox<String>
     */
    public JComboBox<String> getComboBox() {
        return itemComboBox;
    }
}

