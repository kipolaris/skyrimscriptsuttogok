package game.model.entities.items;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//#todo: implement class
public class Transistor extends Item{
  private Transistor Pair;

    private static final Logger logger = LogManager.getLogger();

    @Override
    public void activate() {
        Transistor otherTransistor = owner.getActiveTransistor(); //itt kapunk egy másik transistort
        this.pair(otherTransistor); //erre meghíjuk a párosítást, hogy párosítsa össze a másikkal
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
