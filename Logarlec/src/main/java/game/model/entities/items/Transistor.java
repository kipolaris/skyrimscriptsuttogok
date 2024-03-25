package game.model.entities.items;

import game.model.logging.Suttogo;

//#todo: implement class
public class Transistor extends Item{
  private Transistor Pair;

    //Prioritási listán betöltött fontosságával tér vissza. Nem élvez előnyt ez a tárgy.
    public int getPriority(){
        Suttogo.info("\tret -1");
        return -1;
    }

    //Tranzisztor aktiválása
    @Override
    public void activate() {
        Transistor otherTransistor = owner.getActiveTransistor(); //itt kapunk egy másik transistort
        this.pair(otherTransistor); //erre meghíjuk a párosítást, hogy párosítsa össze a másikkal
    }

    //Nincs élettartama, nem tud lejárni
    @Override
    public boolean decreaseDurability() {
        Suttogo.info("\tret false");
        return false;
    }

    //Párosíthatóságot tudatja. Ha már van párja, akkor nem párosítható, amúgy meg igen.
    @Override
    public boolean isPairable() {
        if (this.Pair == null) {
            Suttogo.info("\tret true");
            return true;
        }
        Suttogo.info("\tret false");
        return false;
    }

    //Nem véd tanárok ellen
    @Override
    public boolean protectFromKill() {
        Suttogo.info("\tret false");
        return false;
    }

    //Nem véd gáz ellen
    @Override
    public boolean protectFromGas() {
        Suttogo.info("\tret false");
        return false;
    }

    //Ezáltal lehet a tranzisztor párját párosítás esetén beállítani
    public void setPair(Transistor p){ this.Pair = p; }

    //Visszatér a párosított tranzisztor párjával
    public Transistor getPair() {
        Suttogo.info("\tret Transistor");
        return this.Pair;
    }

    //Párosítási folyamat, melyben mindkét tranzisztornál beállítja, hogy összepárosodtak
    public void pair(Transistor t){
        t.setPair(this);
        this.setPair(t);
    }

    //Szétkapcsolási folyamat, melyben mindkét tranzisztornál beállítja, hogy szétkapcsolódtak
    public void unpair(){
        this.Pair.setPair(null);
        this.setPair(null);

    }
}
