package game.controller;

import game.model.entities.Character;
import game.model.entities.Student;
import game.model.entities.building.Door;
import game.model.entities.building.Room;
import game.model.entities.items.Item;
import game.model.main.GameMain;
import game.view.*;

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

    private final String s = ImageLoader.getDirectorySeparator();

    private final String standardDoorPic = ImageLoader.appendAbsolutePath("src"+s+"pics"+s+"standard_door.png");

    private final String onewayInDoorPic = ImageLoader.appendAbsolutePath("src"+s+"pics"+s+"oneway_in_door.png");

    private final String onewayOutDoorPic = ImageLoader.appendAbsolutePath("src"+s+"pics"+s+"oneway_out_door.png");

    private final String invisibleDoorPic = ImageLoader.appendAbsolutePath("src"+s+"pics"+s+"invisible_door.png");

    private final String gassedMark = ImageLoader.appendAbsolutePath("src"+s+"pics"+s+"gassed_mark.png");

    private final String cursedMark = ImageLoader.appendAbsolutePath("src"+s+"pics"+s+"cursed_mark.png");

    private final String freshedMark = ImageLoader.appendAbsolutePath("src"+s+"pics"+s+"freshed_mark.png");

    private final String raggedMark = ImageLoader.appendAbsolutePath("src"+s+"pics"+s+"ragged_mark.png");

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
        Character current = GameMain.gameEngine.getCurrent();

        if(current instanceof Student){
            r = GameMain.gameEngine.getCurrent().getLocation();
        }
        //egyébként marad a legutóbbi szoba adata, csak akkor frissül, ha a current egy diák


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

        roomView.clearOverlays();

        roomView.setRoom();

        roomView.setDoors(overlayImages);

        roomView.setRoomName(r.getId());

        overlayImages = new ArrayList<>();

        if(r.getGassed()) { overlayImages.add(gassedMark); }
        if(r.getCursed()) { overlayImages.add(cursedMark); }
        if(r.getHasAirFreshener()) { overlayImages.add(freshedMark); }
        ArrayList<Item> items = r.getItems();
        for(Item item : items) {
            if(item.getId().startsWith("Rag") && item.isActivated()){
                overlayImages.add(raggedMark);
                break;
            }
        }

        roomView.setMarks(overlayImages);

        roomView.validate();
        roomView.repaint();
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
    public Item getChosenItem(){
        ArrayList<Item> items = r.getItems();
        String item = roomView.getSelectedItem();
        for(int i = 0; i < items.size(); i++) {
            if(items.get(i).getId().equals(item)) { return items.get(i); }
        }
        return null;
    }

    public void setRoom(Room r){
        this.r = r;
    }
}
