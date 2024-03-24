package game.model.entities.items;

<<<<<<< HEAD
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

=======
//#todo: implement class
>>>>>>> 9279b00d7d8c49bc963b757c4406c4a198153b4b
public class Transistor extends Item{
  private Transistor Pair;

    public int getPriority(){
        return -1;
    }

    @Override
    public void activate() {
        Transistor otherTransistor = owner.getActiveTransistor();
        this.pair(otherTransistor);
    }

    @Override
    public boolean decreaseDurability() {
        return false;
    }

    @Override
    public boolean isPairable() {
        if (this.Pair == null) {
            return true;
        }
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

    public void setPair(Transistor p){
        this.Pair = p;
    }

    public Transistor getPair() { return this.Pair; }

    public void pair(Transistor t){
        t.setPair(this);
        this.setPair(t);
    }

    public void unpair(){
        this.Pair.setPair(null);
        this.setPair(null);

    }
}
