package game.controller;
import game.model.entities.Student;
import game.model.entities.building.Door;
import game.model.entities.items.Item;
import game.model.main.GameEngine;
import game.view.MenuView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Control osztály az MenuView osztályhoz.
 */
public class MenuController implements ModelListener{
    private final MenuView view;
    private Student student;
    private final GameEngine gameEngine;
    private final RoomController roomController;
    private ItemListController itemListController;

    /**
     * Három paraméteres konstruktor.
     *
     * @param _view a MenuView osztály egy példánya
     * @param _model a GameEngine osztály egy példánya
     * @param _rc a RoomController osztály egy példánya
     */
    public MenuController(MenuView _view, GameEngine _model, RoomController _rc) {
        this.view = _view;
        this.gameEngine = _model;
        roomController = _rc;

        itemListController = new ItemListController(view.getItemListView(), student.getItems());

        // Add action listeners to the buttons
        view.addDropActionListener(new DropButtonListener());
        view.addPickupActionListener(new PickupButtonListener());
        view.addUseActionListener(new UseButtonListener());
        view.addMoveActionListener(new MoveButtonListener());
        view.addSkipActionListener(new SkipButtonListener());
    }

    @Override
    public void onModelChange() {
        if(gameEngine.getCurrent() instanceof Student){
            student = (Student) gameEngine.getCurrent();

            itemListController.onModelChange();
        }
    }

    /**
     * Akciófigyelő a tárgy ledobása gombhoz.
     */
    class DropButtonListener implements ActionListener {
        /**
         * A tárgy ledobásának eseményét kezeli.
         *
         * @param e az esemény
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            Item chosen = itemListController.getSelectedItem();
            if(chosen != null) {
                student.dropItem(chosen);
                System.out.println("Item dropped");
            }
        }
    }

    /**
     * Akciófigyelő a tárgy felvétele gombhoz.
     */
    class PickupButtonListener implements ActionListener {
        /**
         * A tárgy felvételének eseményét kezeli.
         *
         * @param e az esemény
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            Item i = roomController.getChosenItem();

            if (i != null) {
                student.addItem(i);
                System.out.println("Item picked up");
            }
        }
    }

    /**
     * Akciófigyelő a tárgy használata gombhoz.
     */
    class UseButtonListener implements ActionListener {
        /**
         * A tárgy használatának eseményét kezeli.
         *
         * @param e az esemény
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            Item i = itemListController.getSelectedItem();
            if(i != null){
                student.useItem(i);
                System.out.println("Item used");
            }

        }
    }

    /**
     * Akciófigyelő a mozgás gombhoz.
     */
    class MoveButtonListener implements ActionListener {
        /**
         * A mozgás eseményét kezeli.
         *
         * @param e az esemény
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            Door d = roomController.getChosenDoor();
            if(d != null) {
                student.move(d);
                System.out.println("Character moved");
            }
        }
    }

    /**
     * Akciófigyelő a kör átugrása gombhoz.
     */
    class SkipButtonListener implements ActionListener {
        /**
         * A kör átugrásának eseményét kezeli.
         *
         * @param e az esemény
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            student.skipTurn();
            System.out.println("Turn skipped");
        }
    }
}

