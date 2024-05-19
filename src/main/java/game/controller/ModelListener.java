package game.controller;

/**
 * Control interfész.
 */
public interface ModelListener {
    /**
     * Állapotfrissítésre szolgáló függvény.
     */
    public void onModelChange();

    /**
     * Speciális művelet ablak átméretezése esetére.
     */
    public void onResizeWindow(); //todo: valósítsuk meg, vagy szedjük ki, ha nem kell
}
