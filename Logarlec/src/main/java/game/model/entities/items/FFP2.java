package game.model.entities.items;

//#todo: implement class
public class FFP2 extends Item{
    @Override
    public void activate() {
        this.activated = true;
    }

    @Override
    public boolean decreaseDurability() {
        durability--;

        if (durability <= 0) {
        //#todo: mivan ha elfogy a durability?
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
        return true;
    }
}
