package game.view;

import game.controller.ModelListener;
import game.model.logging.Suttogo;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import game.model.entities.Character;

/**
 * View osztály egy adott szobában lévő karakterek megjelenítésére.
 */
public class CharacterView {
    private final List<ModelListener> listeners = new ArrayList<>();
    private final JComboBox<String> characterComboBox = new JComboBox<>();

    /**
     * Paraméter nélküli konstruktor.
     *
     * <p>Létrehoz egy üres JComboBox-ot (ebbe kerül majd bele a karakterek egy listája)</p>
     */
    public CharacterView() {
    }

    /**
     * Függvény ami frissíti a characterComboBox tartalmát.
     *
     * @param characters karakterek egy listája
     */
    public void setCharacters(List<Character> characters) {
        characterComboBox.removeAllItems();
        for (Character character : characters) {
            System.out.println("CharacterView: " + character.getId());
            characterComboBox.addItem(character.getId());
        }
        characterComboBox.revalidate();
        characterComboBox.repaint();
        System.out.println("JComboBox item count: " + characterComboBox.getItemCount());
    }

    /**
     * Függvény, amely visszaadja a karaktereket tartalmazó JComboBox-ot.
     *
     * @return JComboBox<String>
     */
    public JComboBox<String> getComboBox() {
        return characterComboBox;
    }

    /**
     * Függvény, amely felvesz egy új kontrollert.
     *
     * @param listener egy ModelListener interfészt implementáló kontroller.
     */
    public void addListener(ModelListener listener){
        if(listener!=null){
            listeners.add(listener);
        }else{
            Suttogo.error("A kapott listener null : CharacterView addListener()");
        }
    }
}
