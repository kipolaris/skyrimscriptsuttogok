package game.model;

import game.controller.ModelListener;

import java.util.List;

/**
 * Absztrakt osztály, amely implementálja az ObservableModel interfészt,
 * és alapvető funkciókat biztosít a megfigyelők kezeléséhez.
 */
public abstract class AbstractObservableModel implements ObservableModel {
    /**
     * A megfigyelők listája.
     */
    protected List<ModelListener> listeners;

    @Override
    public void addListener(ModelListener listener) {
        listeners.add(listener);
    }

    @Override
    public void notifyEveryone() {
        if (!listeners.isEmpty()) {
            for (ModelListener listener : listeners) {
                listener.onModelChange();
            }
        } else {
            System.err.println(this.getClass() + ": no listeners found");
        }
    }
}
