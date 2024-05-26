package game.controller;

import game.model.main.GameEngine;
import game.model.main.GameMain;
import game.view.InfoView;

public class InfoController implements ModelListener {
    private final InfoView view;
    private final GameEngine gameEngine = GameMain.gameEngine;
    private int timer;

    public InfoController(InfoView v, int t) {
        this.view = v;
        this.timer = t;

        onModelChange();
    }

    public void onModelChange() {
        view.showInfo(gameEngine.getSuttogo().getLastMessage(), timer);
    }
}
