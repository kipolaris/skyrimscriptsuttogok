package game.model.entities.items;

//#todo: implement class
public class TVSZ extends Item{
    @Override
    public void activate() {
        //implement
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
