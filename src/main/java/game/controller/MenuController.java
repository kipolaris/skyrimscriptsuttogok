package game.controller;
import game.model.entities.Student;
import game.model.entities.building.Door;
import game.model.entities.items.Item;
import game.model.logging.Suttogo;
import game.model.main.GameEngine;
import game.model.main.GameMain;
import game.view.InfoView;
import game.view.MenuView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

        // Add action listeners to the buttons
        view.addDropActionListener(new DropButtonListener());
        view.addPickupActionListener(new PickupButtonListener());
        view.addUseActionListener(new UseButtonListener());
        view.addMoveActionListener(new MoveButtonListener());
        view.addSkipActionListener(new SkipButtonListener());

        onModelChange();
    }

    @Override
    public void onModelChange() {
        if(gameEngine.getCurrent().getId().startsWith("Student")){
            if(student == null || !student.equals(gameEngine.getCurrent())) {
                student = (Student) gameEngine.getCurrent();
                view.setCurrentStudent(student.getId());
                itemListController = new ItemListController(view.getItemListView(), new ArrayList<>(student.getItems().values()));
            }
            else {
                view.setActionPoints("Akciók: " + student.getActions());
                itemListController.setItems(new ArrayList<>(gameEngine.getCurrent().getItems().values()));
                itemListController.onModelChange();
            }
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
                Suttogo.getSuttogo().info("You dropped " + chosen.getId());
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
                Suttogo.getSuttogo().info("You picked up" + i.getId());
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
                Suttogo.getSuttogo().info("You used " + i.getId());
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
                Suttogo.getSuttogo().info(student.getId() + " moved to another room");
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
        }
    }
}

