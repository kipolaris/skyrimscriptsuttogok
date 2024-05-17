package game.view;

import game.controller.ModelListener;
import game.model.logging.Suttogo;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import game.model.entities.Character;

public class CharacterView {
    private final List<ModelListener> listeners = new ArrayList<>();

    private final JComboBox<String> characterComboBox;

    public CharacterView() {
        characterComboBox = new JComboBox<>();
        characterComboBox.setEnabled(false); // Disable the combo box to prevent selection changes
    }

    // Method to populate the JComboBox with characters
    public void setCharacters(List<Character> characters) {
        characterComboBox.removeAllItems();
        for (Character character : characters) {
            characterComboBox.addItem(character.getId());
        }
    }

    // Method to get the JComboBox for adding to the UI
    public JComboBox<String> getComboBox() {
        return characterComboBox;
    }

    public void display(){
        //nem kell

        throw new UnsupportedOperationException();
    }

    public void addListener(ModelListener listener){
        if(listener!=null){
            listeners.add(listener);
        }else{
            Suttogo.error("A kapott listener null : CharacterView addListener()");
        }
    }
}
