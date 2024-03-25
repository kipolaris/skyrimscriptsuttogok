package game.model.entities.items;
import game.model.logging.Suttogo;

public class Transistor extends Item{
  private Transistor Pair;

    public int getPriority(){
        Suttogo.info("\tret -1");
        return -1;
    }

    @Override
    public void activate() {
        if(this.Pair == null) {
            Transistor otherTransistor = owner.getActiveTransistor(); //itt kapunk egy másik transistort
            this.pair(otherTransistor); //erre meghíjuk a párosítást, hogy párosítsa össze a másikkal
        }
        else{
            owner.setLocation(this.Pair.getLocation());
            unpair();
        }
    }

    @Override
    public boolean decreaseDurability() {
        Suttogo.info("\tret false");
        return false;
    }

    @Override
    public boolean isPairable() {
        if (this.Pair == null) {
            Suttogo.info("\tret true");
            return true;
        }
        Suttogo.info("\tret false");
        return false;
    }

    @Override
    public boolean protectFromKill() {
        Suttogo.info("\tret false");
        return false;
    }

    @Override
    public boolean protectFromGas() {
        Suttogo.info("\tret false");
        return false;
    }

    public void setPair(Transistor p){ this.Pair = p; }

    public Transistor getPair() {
        Suttogo.info("\tret Transistor");
        return this.Pair;
    }

    public void pair(Transistor t){
        t.setPair(this);
        this.setPair(t);
    }

    public void unpair(){
        this.Pair.setPair(null);
        this.setPair(null);

    }
}
