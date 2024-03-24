package game.model.entities.building;

//#todo: implement class
public class Door {
    private Room from;
    private Room to;

    private boolean bothWays;

    private boolean visible;

    public boolean accept(Character c, Room r){
        return false;
    }

    public Room getNeighbour(Room r){
        return null;
    }
}
