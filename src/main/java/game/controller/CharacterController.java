package game.controller;

import game.view.CharacterView;

import java.util.List;

public class CharacterController implements ModelListener{
    private CharacterView characterView;

    private List<Character> characters;

    public CharacterController(List<Character> characters){
        characterView = new CharacterView();

    }

    @Override
    public void onModelChange() {

    }

    @Override
    public void onResizeWindow() {

    }
}
