package game.model.entities.items;
import game.model.entities.Character;
import game.model.entities.building.Room;
import game.model.logging.Suttogo;

public class Transistor extends Item{
  private Transistor Pair;

    /**
     * Konstruktor: létrehozza a tárgyat, és inicializálja a kezdőértékeket
     */
  public Transistor(boolean activated, boolean defensive, int durability, Room location, Character owner) {
      super(activated, defensive, durability, location, owner);
  }
    public int getPriority(){
        Suttogo.info("getPriority()");
        Suttogo.info("\treturn -1");
        return -1;
    }

    /**
     * Aktiválás esetén, ha még nincs párosítva, akkor párosítjuk
     * Ha már párosítva van, akkor letesszük egy szobába
     */
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

    /**
     * Nincs élettartama
     * @return
     */
    @Override
    public boolean decreaseDurability() {
        Suttogo.info("decreaseDurability()");
        Suttogo.info("\treturn false");
        return false;
    }

    /**
     * Párosítható, ha még nincsen párja
     * Ha már van, akkor nem párosítható
     */
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

    /**
     * Nem tud gyilkolás ellen megvédeni
     */
    @Override
    public boolean protectFromKill() {
        Suttogo.info("protectFromKill()");
        Suttogo.info("\treturn false");
        return false;
    }

    /**
     * Gáz ellen nem véd
     */
    @Override
    public boolean protectFromGas() {
        Suttogo.info("protectFromGas()");
        Suttogo.info("\treturn false");
        return false;
    }

    /**
     * Beállítjuk a párjának a másik tranzisztort
     */
    public void setPair(Transistor p){
      Suttogo.info("setPair(Transistor)");
      this.Pair = p;
    }

    /**
     * Párjának lekérdezése
     */
    public Transistor getPair() {
        Suttogo.info("getPair()");
        Suttogo.info("\treturn Transistor");
        return this.Pair;
    }

    /**
     * Másik tranzisztorra és saját magára is beállítja, hogy összepárosodtak
     */
    public void pair(Transistor t){
        Suttogo.info("pair(Transistor)");
        t.setPair(this);
        this.setPair(t);
    }

    /**
     * Az adott tranzisztor és párjának kapcsolatát felbontja
     */
    public void unpair(){
        Suttogo.info("unpair()");
        this.Pair.setPair(null);
        this.setPair(null);

    }
}
