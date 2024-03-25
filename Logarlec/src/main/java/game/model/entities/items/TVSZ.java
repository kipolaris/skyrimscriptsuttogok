package game.model.entities.items;


public class TVSZ extends Item{

    //Prioritási listán levő pozíciója
    public int getPriority(){
        return 2;
    }

    //Aktiválás esetén
    @Override
    public void activate() {
        this.activated = true;
    }

    //Használat esetén csökkenti az élettartamát
    @Override
    public boolean decreaseDurability() {
        this.durability--;

        if (this.durability <= 0) {
            return false;
        }
        return true;
    }

    //Nem párosítható
    @Override
    public boolean isPairable() {
        return false;
    }

    //Meg tud védeni a tanártól, és meg is véd, ha aktiválva van
    @Override
    public boolean protectFromKill() {
        if(this.activated) {
            return true;
        }
        return false;
    }

    //Nem véd gáz ellen
    @Override
    public boolean protectFromGas() {
        return false;
    }
}
