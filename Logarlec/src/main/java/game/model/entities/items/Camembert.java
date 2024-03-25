package game.model.entities.items;

public class Camembert extends Item{

    public int getPriority(){ // visszaadja a tárgy prioritását, erre akkor van szükség, amikor két vagy több azonos tárgy található a játékosnál

        return -1;
    }

    @Override
    public void activate() { // ezzel lehet aktiválni a tárgyat, ezután a szoba mérgesgázzal teli lesz
        location.setGassed(true);
        durability = 0;
    }

    @Override
    public boolean decreaseDurability() {
        return false;
    } // hátralévő élettartam csökkentése 1 körrel

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
        return false;
    }
}
