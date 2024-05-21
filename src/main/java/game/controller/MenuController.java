package game.controller;
import game.model.entities.Student;
import game.model.entities.building.Door;
import game.model.entities.items.Item;
import game.model.main.GameEngine;
import game.model.main.GameMain;
import game.view.InfoView;
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
    private InfoView infoView;

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
        this.infoView = GameMain.gamePanel.getInfoView();
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

    class DropButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Item chosen = itemListController.getSelectedItem();
            if(chosen != null) {
                student.dropItem(chosen);
                System.out.println("Item dropped");
                infoView.showInfo("Item dropped", 2000);
            }
        }
    }

    /**
     * ActionListener a view pickupButton gombjához.
     */
    class PickupButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Item i = roomController.getChosenItem();

            if (i != null) {
                student.addItem(i);
                System.out.println("Item picked up");
                infoView.showInfo("Item picked up", 2000);
            }
        }
    }

    /**
     * ActionListener a view useButton gombjához.
     */
    class UseButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Item i = itemListController.getSelectedItem();
            if(i != null){
                student.useItem(i);
                System.out.println("Item used");
                infoView.showInfo("Item used", 2000);
            }

        }
    }

    /**
     * ActionListener a view moveButton gombjához.
     */
    class MoveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Door d = roomController.getChosenDoor();
            if(d != null) {
                student.move(d);
                System.out.println("Character moved");
                infoView.showInfo(student.getId() + " moved to another room", 2000);
            }
        }
    }

    /**
     * ActionListener a view skipButton gombjához.
     */
    class SkipButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            student.skipTurn();
            System.out.println("Turn skipped");
            infoView.showInfo("Turn skipped", 2000);
        }
    }
}

