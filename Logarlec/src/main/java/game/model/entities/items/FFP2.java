package game.model.entities.items;

//#todo: implement class
public class FFP2 extends Item{
    @Override
    public void activate() {
        this.activated = true;
    }

    public int getPriority(){
        return 1;
    }

    @Override
    public boolean decreaseDurability() {
        this.durability--;

        if (this.durability <= 0) {
            return false;
        }
        return true;
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
        if(activated) {
            return true;
        }
        return false;
    }
}
