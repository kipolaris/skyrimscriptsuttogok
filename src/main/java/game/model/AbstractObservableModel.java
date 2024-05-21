package game.model;

import game.controller.ModelListener;

import java.util.List;

public abstract class AbstractObservableModel implements ObservableModel {
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
