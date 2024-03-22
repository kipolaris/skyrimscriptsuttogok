package game.model.entities.items;

//#todo: implement class
public class Cups extends Item{
    @Override
    public void activate() {
        activated = true;
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
        return true;
    }

    @Override
    public boolean protectFromGas() {
        return false;
    }
}
