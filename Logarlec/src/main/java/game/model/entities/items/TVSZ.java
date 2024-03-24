package game.model.entities.items;


public class TVSZ extends Item{

    public int getPriority(){
        return 2;
    }
    @Override
    public void activate() {
        this.activated = true;
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
        if(this.activated) {
            return true;
        }
        return false;
    }

    @Override
    public boolean protectFromGas() {
        return false;
    }
}
