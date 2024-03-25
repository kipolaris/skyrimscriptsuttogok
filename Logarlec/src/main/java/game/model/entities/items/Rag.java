package game.model.entities.items;

//#todo: implement class
public class Rag extends Item{
    //A prioritási listán nem élvez előnyt
    public int getPriority(){
        return -1;
    }

    //Aktiválja a tárgyat, ezután eldobódik, és a tanárokra ezután s lesz.
    @Override
    public void activate() {
        this.activated = true;
        this.location.paralyzeProfessors();
        owner.dropItem(this);
    }

    //A tárgy élettartamát csökkenti. Ha aktív a tárgy, akkor körönként egy életet veszt.
    @Override
    public boolean decreaseDurability(){
        this.durability--;

        if (this.durability <= 0) {
            return false;
        }
        return true;
    }

    //Párosíthatóság lekérdezése. Nem párosítható.
    @Override
    public boolean isPairable() {
        return false;
    }

    //Védelem lekérdezése tanárok ellen. Ilyen esetben nem felhasználható.
    @Override
    public boolean protectFromKill() {
        return false;
    }

    //Védelem lekérdezése gáz ellen. Ilyen esetben nem felhasználható.
    @Override
    public boolean protectFromGas() {
        return false;
    }
}
