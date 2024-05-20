package game.controller;

import game.view.CharacterView;

import java.util.List;

import game.model.entities.Character;

public class CharacterController implements ModelListener{
    private final CharacterView characterView;

    private final List<Character> characters;

    public CharacterController(List<Character> _characters, CharacterView _characterView){
        characterView = _characterView;

        characters = _characters;
    }

    @Override
    public void onModelChange() {
        characterView.setCharacters(characters);
    }
}
