package game.controller;

import game.view.CharacterView;

import java.util.ArrayList;
import java.util.List;

import game.model.entities.Character;

/**
 * Control osztály a CharacterView osztályhoz.
 */
public class CharacterController implements ModelListener{
    private final CharacterView characterView;
    private List<Character> characters;

    /**
     * Két paraméteres konstruktor.
     *
     * @param _characters karakterek egy listája
     * @param _characterView CharacterView osztály egy példánya
     */
    public CharacterController(List<Character> _characters, CharacterView _characterView){
        characterView = _characterView;
        characters = _characters;
    }

    @Override
    public void onModelChange() {
        characterView.setCharacters(characters);
    }

    public void setCharacters(List<Character> characters){
        if(characters.isEmpty()){
            System.out.println("CharacterController: characters list is empty");
        }
        this.characters = characters;
        System.out.println("CharacterController list of chars size: " + characters.size());
    }
}
