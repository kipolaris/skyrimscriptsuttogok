package game.controller;

import game.model.entities.building.Door;
import game.model.entities.items.Item;

/**
 * Control osztály az RoomPanelView osztályhoz.
 */
public class RoomController implements ModelListener{
    @Override
    public void onModelChange() {

    }

    @Override
    public void onResizeWindow() {
        //todo: valósítsuk meg, vagy szedjük ki, ha nem kell
    }

    /**
     * Visszaad egy kiválasztott ajtót
     *
     * @return kiválasztott ajtó
     */
    public Door getChosenDoor(){
        return null;
    } //todo

    /**
     * Visszadja a kiválaszott tárgyat.
     *
     * @return kiválaszott tárgy
     */
    public Item getChosenItem(){return null;} //todo
}
