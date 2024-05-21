package game.model;

import game.controller.ModelListener;

public interface ObservableModel {
    // Method to add a listener
    void addListener(ModelListener listener);

    // Method to notify all listeners
    void notifyEveryone();
}

