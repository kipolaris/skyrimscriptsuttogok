package game.model.entities.items;
import game.model.entities.Character;
import game.model.entities.building.Room;
import game.model.logging.Suttogo;

public class Transistor extends Item{
  private Transistor Pair;
  public Transistor(boolean activated, boolean defensive, int durability, Room location, Character owner) {
      super(activated, defensive, durability, location, owner);
  }
    public int getPriority(){
        Suttogo.info("getPriority()");
        Suttogo.info("\treturn -1");
        return -1;
    }

    @Override
    public void activate() {
        Suttogo.info("activate()");
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
        Suttogo.info("decreaseDurability()");
        Suttogo.info("\treturn false");
        return false;
    }

    @Override
    public boolean isPairable() {
        Suttogo.info("isPairable()");
        if (this.Pair == null) {
            Suttogo.info("\treturn true");
            return true;
        }
        Suttogo.info("\treturn false");
        return false;
    }

    @Override
    public boolean protectFromKill() {
        Suttogo.info("protectFromKill()");
        Suttogo.info("\treturn false");
        return false;
    }

    @Override
    public boolean protectFromGas() {
        Suttogo.info("protectFromGas()");
        Suttogo.info("\treturn false");
        return false;
    }

    public void setPair(Transistor p){
      Suttogo.info("setPair(Transistor)");
      this.Pair = p;
    }

    public Transistor getPair() {
        Suttogo.info("getPair()");
        Suttogo.info("\treturn Transistor");
        return this.Pair;
    }

    public void pair(Transistor t){
        Suttogo.info("pair(Transistor)");
        t.setPair(this);
        this.setPair(t);
    }

    public void unpair(){
        Suttogo.info("unpair()");
        this.Pair.setPair(null);
        this.setPair(null);

    }
}
