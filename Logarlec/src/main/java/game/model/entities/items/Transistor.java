package game.model.entities.items;


//#todo: implement class
public class Transistor extends Item{
    private Transistor Pair;
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

    public void pair(Transistor t){
        t.setPair(this);
        this.setPair(t);
    }

    public void unpair(){
        this.Pair.setPair(null);
        this.setPair(null);

    }
}
