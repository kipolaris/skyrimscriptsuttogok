package game.controller;

import game.model.entities.building.Door;
import game.model.entities.building.Room;
import game.model.entities.items.Item;
import game.model.main.GameMain;
import game.view.CharacterView;
import game.view.GamePanel;
import game.view.ItemListView;
import game.view.RoomView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Control osztály az RoomPanelView osztályhoz.
 */
public class RoomController implements ModelListener{

    private Room r;

    private RoomView roomView;

    private ItemListController itemListController;

    private CharacterController characterController;

    private final String standardDoorPic = "src/pics/standard_door.png";

    private final String invisibleDoorPic = "src/pics/invisible_door.png";

    private final String onewayOutDoorPic = "src/pics/oneway_out_door.png";

    private final String onewayInDoorPic = "src/pics/oneway_in_door.png";

    /***
     * számon tartja, hogy melyik ajtóhoz melyik jcombobox string tartozik.
     */
    private HashMap<String, Door> doorsMap = new HashMap<>();

    /**
     * Két paraméteres konstruktor.
     *
     * @param r megadott szoba
     * @param roomView a szobához tartozó megjelenítő osztálypéldány
     */
    public RoomController(Room r, RoomView roomView, ItemListController itemListController, CharacterController characterController){
        this.r = r;
        this.roomView = roomView;
        this.itemListController = itemListController;
        this.characterController = characterController;

        onModelChange();
    }

    @Override
    public void onModelChange() {
        r = GameMain.gameEngine.getCurrent().getLocation();

        roomView.clearDoorsComboBox();

        // Átfedések hozzáadása a kör körüli pozíciókban:
        List<String> overlayImages = new ArrayList<>();

        List<Door> doors = r.getDoors();

        int i = 0;

        for(Door d : doors){
            if(!d.getVisible()){
                overlayImages.add(invisibleDoorPic);
                String key = "Door"+i+" (invisible)";
                doorsMap.put(key, d);
                roomView.addToDoorsJCombobox(key);
            }else{
                if(d.isBothWays()){
                    overlayImages.add(standardDoorPic);
                    String key = "Door"+i+" (standard)";
                    doorsMap.put(key, d);
                    roomView.addToDoorsJCombobox(key);
                }else if(d.isItInwards(r)){
                    overlayImages.add(onewayInDoorPic);
                    String key = "Door"+i+" (one way in)";
                    doorsMap.put(key, d);
                    roomView.addToDoorsJCombobox(key);
                }else{
                    overlayImages.add(onewayOutDoorPic);
                    String key = "Door"+i+" (one way out)";
                    doorsMap.put(key, d);
                    roomView.addToDoorsJCombobox(key);
                }
            }
            i++;
        }

        characterController.setCharacters(r.getCharacters());

        characterController.onModelChange();

        itemListController.setItems(r.getItems());

        itemListController.onModelChange();

        roomView.setDoors(overlayImages);

        roomView.setRoomName(r.getId());
    }

    /**
     * Visszaad egy kiválasztott ajtót
     *
     * @return kiválasztott ajtó
     */
    public Door getChosenDoor(){
        return doorsMap.get(roomView.getSelectedDoor());
    }

    /**
     * Visszadja a kiválaszott tárgyat.
     *
     * @return kiválaszott tárgy
     */
    public Item getChosenItem(){return null;} //todo

    public void setRoom(Room r){
        this.r = r;
    }
}
