package game.controller;

import game.model.logging.Suttogo;
import game.model.main.GameEngine;
import game.model.main.GameMain;
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
    private final GameEngine gameEngine = GameMain.gameEngine;

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
        /*if(characters.isEmpty()){
            Suttogo.getSuttogo().note("CharacterController: characters list is empty");
        }*/
        this.characters = characters;
    }

    public CharacterView getCharacterView() { return characterView; }
}
