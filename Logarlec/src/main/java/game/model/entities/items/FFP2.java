package game.model.entities.items;

public class FFP2 extends Item{
    @Override
    public void activate() { //ezzel lehet aktiválni a maszkot, innentől megvédi használóját a mérges gáztól
        this.activated = true;
    }

    public int getPriority(){ // visszaadja a tárgy prioritását, erre akkor van szükség, amikor két vagy több azonos tárgy található a játékosnál

        return 1;
    }

    @Override
    public boolean decreaseDurability() { // hátralévő élettartam csökkentése 1 körrel
        this.durability--;

        if (this.durability <= 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isPairable() { // megmondja hogy a tárgy párosítható-e (tranzisztor esetén releváns csak)
        return false;
    }

    @Override
    public boolean protectFromKill() {// ezzel a függvénnyel lehet megkérni a tárgyat, hogy az védje meg használóját a kibukástól
        return false;
    }

    @Override
    public boolean protectFromGas() {// ezzel a függvénnyel lehet megkérni a tárgyat, hogy az védje meg használóját a mérges gáztól
        if(activated) {
            return true;
        }
        return false;
    }
}
