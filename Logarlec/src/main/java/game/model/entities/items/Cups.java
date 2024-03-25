package game.model.entities.items;

public class Cups extends Item{

    public int getPriority(){ // visszaadja a tárgy prioritását, erre akkor van szükség, amikor két vagy több azonos tárgy található a játékosnál

        return 1;
    }
    @Override
    public void activate() { // ezzel lehet aktiválni a tárgyat, innentől a tárgy megvédi használóját a csúnya, gonosz, rosszakaró, hamis oktatóktól
        this.activated = true;
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
        if(this.activated){
            return true;
        }
        return false;
    }

    @Override
    public boolean protectFromGas() {// ezzel a függvénnyel lehet megkérni a tárgyat, hogy az védje meg használóját a mérges gáztól
        return false;
    }
}
