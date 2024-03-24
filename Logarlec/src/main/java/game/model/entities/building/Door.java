package game.model.entities.building;

import game.model.entities.Character;

public class Door {
    private Room from;
    private Room to;

    private boolean bothWays;

    private boolean visible;

    public boolean accept(Character c, Room r){
        if(r.getCharacters().size()+1 >= r.getCapacity()) return false;
        else r.addCharacter(c);
        return true;
    }

    public Room getNeighbour(Room r){
        if(r==from) return to;
        else return from;
    }
}
