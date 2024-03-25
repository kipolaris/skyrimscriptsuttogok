package game.model.entities.items;

//#todo: implement class
public class SlideRule extends Item{
    //A prioritási listán nem élvez előnyt
    public int getPriority(){
        return -1;
    }

    //A tárgy felvételekor hívódik meg a függvény. Ekkor a hallgatók megnyerték a játékot..
    @Override
    public void activate() {
        //implement
    }

    //Élettartama nincs
    @Override
    public boolean decreaseDurability() {
        return false;
    }

    //Nem párosítható
    @Override
    public boolean isPairable() {
        return false;
    }

    //Nem tud megvédeni tanártól
    @Override
    public boolean protectFromKill() {
        return false;
    }

    //Nem tud megvédeni gáztól
    @Override
    public boolean protectFromGas() {
        return false;
    }
}
