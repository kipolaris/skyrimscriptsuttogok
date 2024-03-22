package game.model.entities.items;

//#todo: implement class
public class Transistor extends Item{
    @Override
    public void activate() {

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

    public void pair(Transistor t){
        //implement
    }

    public void unpair(){
        //implement
    }
}
