package game.model.entities.items;

import game.model.logging.Suttogo;

public class Transistor extends Item{
  private Transistor Pair;

    //Prioritási listán betöltött fontosságával tér vissza. Nem élvez előnyt ez a tárgy.
    public int getPriority(){
        return -1;
    }

    //Tranzisztor aktiválása
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

    //Nincs élettartama, nem tud lejárni
    @Override
    public boolean decreaseDurability() {
        return false;
    }

    //Párosíthatóságot tudatja. Ha már van párja, akkor nem párosítható, amúgy meg igen.
    @Override
    public boolean isPairable() {
        if (this.Pair == null) {
            return true;
        }
        return false;
    }

    //Nem véd tanárok ellen
    @Override
    public boolean protectFromKill() {
        return false;
    }

    //Nem véd gáz ellen
    @Override
    public boolean protectFromGas() {
        return false;
    }

    //Ezáltal lehet a tranzisztor párját párosítás esetén beállítani
    public void setPair(Transistor p){ this.Pair = p; }

    //Visszatér a párosított tranzisztor párjával
    public Transistor getPair() {
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
