package game.model.entities.items;

public class Camembert extends Item{
    public int getPriority(){
        return -1;
    }

    @Override
    public void activate() {
        location.setGassed(true);
        durability = 0;
    }

    @Override
    public boolean decreaseDurability() {
        return false;
    }

    @Override
    public boolean isPairable() {
        return false;
    }

    @Override
    public boolean protectFromKill() {
        return false;
    }

    @Override
    public boolean protectFromGas() {
        return false;
    }
}
