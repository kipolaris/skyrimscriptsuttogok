package game.model;

import game.controller.ModelListener;

/**
 * Interfész az ObservableModel számára, amely lehetővé teszi modellek megfigyelhetőségét.
 */
public interface ObservableModel {
    /**
     * Hozzáad egy figyelőt a modellhez.
     *
     * @param listener a hozzáadandó figyelő (ModelListener)
     */
    void addListener(ModelListener listener);

    /**
     * Értesíti az összes figyelőt a modell változásáról.
     */
    void notifyEveryone();
}

