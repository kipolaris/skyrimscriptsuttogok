package game.controller;

import game.model.entities.building.Door;
import game.model.entities.items.Item;

public class RoomController implements ModelListener{
    @Override
    public void onModelChange() {

    }

    /**
     * Visszaad egy kiválasztott ajtót
     * @return kiválasztott ajtó
     */
    public Door getChosenDoor(){
        return null;
    }

    /**
     * visszadja a kiválaszott tárgyat.
     * @return kiválaszott tárgy
     */
    public Item getChosenItem(){return null;}
}
