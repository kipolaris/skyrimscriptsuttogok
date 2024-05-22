package game.controller;

import game.model.entities.building.Door;
import game.model.entities.building.Room;
import game.model.entities.items.Item;
import game.view.RoomView;

import java.util.ArrayList;
import java.util.List;

/**
 * Control osztály az RoomPanelView osztályhoz.
 */
public class RoomController implements ModelListener{

    private Room r;

    private RoomView roomView;

    private final String standardDoorPic = "src/pics/standard_door.png";

    private final String invisibleDoorPic = "src/pics/invisible_door.png";

    private final String onewayOutDoorPic = "src/pics/oneway_out_door.png";

    private final String onewayInDoorPic = "src/pics/oneway_in_door.png";

    public RoomController(Room r, RoomView roomView){
        this.r = r;
        this.roomView = roomView;
    }

    @Override
    public void onModelChange() {
        // Átfedések hozzáadása a kör körüli pozíciókban:
        List<String> overlayImages = new ArrayList<>();

        List<Door> doors = r.getDoors();

        for(Door d : doors){
            if(!d.getVisible()){
                overlayImages.add(invisibleDoorPic);
            }else{
                if(d.isBothWays()){
                    overlayImages.add(standardDoorPic);
                }else if(d.isItInwards(r)){
                    overlayImages.add(onewayInDoorPic);
                }else{
                    overlayImages.add(onewayOutDoorPic);
                }
            }
        }

        roomView.setDoors(overlayImages);
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
