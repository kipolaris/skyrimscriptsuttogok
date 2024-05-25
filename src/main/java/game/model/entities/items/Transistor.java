package game.model.entities.items;
import game.model.entities.Character;
import game.model.entities.building.Room;
import game.model.logging.Suttogo;


import static game.model.main.GameMain.gameEngine;

/**A tranzisztor osztálya*/
public class Transistor extends Item{

  private Transistor Pair;

    /**
     * Konstruktor: létrehozza a tárgyat, és inicializálja a kezdőértékeket
     */
  public Transistor(boolean activated, boolean defensive, Room location, Character owner) {
      super("Transistor"+gameEngine.getItemID(), activated, defensive, 1000, location, owner);
  }

  @Override
  public int getPriority(){

        return -1;
  }

  /**
  * Aktiválás esetén, ha még nincs párosítva, akkor párosítjuk
  * Ha már párosítva van, akkor letesszük egy szobába
  */
  @Override
  public void activate() {
      if(this.Pair == null) {
          Transistor otherTransistor = owner.getActiveTransistor(); //itt kapunk egy másik transistort
          this.pair(otherTransistor); //erre meghíjuk a párosítást, hogy párosítsa össze a másikkal
          owner.setActions(-1);
      }
      else{
          String s = this.getId() + " used. " + getEffect();
          Suttogo.info(s);
          if(Pair.getLocation()==null) {
              owner.dropItem(this);
          }
          else {
              if(Pair.getLocation().addCharacter(owner)) unpair();
              owner.setActions(-1);
          }
      }
  }

  /**
  * Nincs élettartama
  * @return
  */
  @Override
  public boolean decreaseDurability() {

      return false;
  }

  /**
   * Párosítható, ha még nincsen párja
   * Ha már van, akkor nem párosítható
   */
  @Override
  public boolean isPairable() {
      if (this.Pair == null) {
          return true;
      }
      return false;
  }

  /**
   * Beállítjuk a párjának a másik tranzisztort
   */
  public void setPair(Transistor p){
      this.Pair = p;
  }
    /**
     * Párjának lekérdezése
     */
    @Override
    public Transistor getPair() {

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
      Suttogo.info("unpair..");
      this.Pair.setPair(null);
      this.setPair(null);
  }

  @Override
  public String getEffect() {
        return "You get teleported away.";
    }

    /**
     * A tárgy létrehozásához szükséges command
     */
    @Override
    public String create(){
        return "transistor";
    }

    /**
     * Hanyadikként volt a tárgy létrehozva
     */
    @Override
    public String getNumID(){
        String uj = getId().replace("Transistor", "");
        return uj;
    }
}
