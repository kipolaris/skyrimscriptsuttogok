package game.model.entities.items;

//#todo: implement class
public class FFP2 extends Item{
    @Override
    public void activate() {
        //implement
    }

    public int getPriority(){
        return 1;
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
