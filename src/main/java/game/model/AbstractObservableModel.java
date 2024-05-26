package game.model;

import game.controller.ModelListener;
import game.model.main.GameMain;

import java.util.ArrayList;
import java.util.List;

/**
 * Absztrakt osztály, amely implementálja az ObservableModel interfészt,
 * és alapvető funkciókat biztosít a megfigyelők kezeléséhez.
 */
public abstract class AbstractObservableModel implements ObservableModel {
    /**
     * A megfigyelők listája.
     */
    protected List<ModelListener> listeners = new ArrayList<>();

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
            if(!GameMain.developermode && !GameMain.isInit()) System.err.println(this.getClass() + ": no listeners found");
        }
    }
}
