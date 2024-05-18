package game.controller;

/**
 * Kontrollerek interfésze.
 */
public interface ModelListener {
    /**
     * állapotfrissítésre szolgál.
     */
    public void onModelChange();

    /**
     * Speciális művelet ablak átméretezése esetére
     */
    public void onResizeWindow();
}
