package game.model.entities.items;

//#todo: implement class
public class Camembert extends Item{
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
