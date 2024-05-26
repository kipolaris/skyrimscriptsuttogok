package game.view;

import game.controller.*;
import game.model.entities.building.Room;
import game.model.main.GameEngine;
import game.model.main.GameMain;

import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;

/**
 * Osztály a megjelenítők összefogására, és kirajzolására
 */
public class GamePanel {

    int frame_size = 800;

    private JFrame frame;

    private static JPanel cardPanel = new JPanel();

    private JPanel mainMenuPanel = new JPanel();

    private static JPanel playPanel = new JPanel();

    private static CardLayout cardLayout = new CardLayout();

    /**
     * Függvény, ami visszaadja a cardLayout-ot
     */
    public static CardLayout getCardLayout(){ return cardLayout; }

    /**
     * Függvény, ami visszaadja a playPanel-t
     */
    public static JPanel getPlayPanel(){
        return playPanel;
    }

    /**
     * Függvény, ami visszaadja a cardPanel-t
     */
    public static JPanel getCardPanel(){
        return cardPanel;
    }

    private MainMenuView mainMenuView = new MainMenuView();

    private MainMenuController mainMenuController = new MainMenuController(mainMenuView);

    /**
     * Paraméter nélküli konstruktor.
     *
     * <p>Létrehozza az ablakot és beállítja azt</p>
     */
    public GamePanel() {
        frame = new JFrame();

        // set common frame properties here
        frame.setSize(frame_size, frame_size);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(cardPanel);

        playPanel.setLayout(new BorderLayout());

        cardPanel.setLayout(cardLayout);

        cardPanel.add(mainMenuPanel, "menu");
        cardPanel.add(playPanel, "play");



        // set the background color of the JFrame
        frame.getContentPane().setBackground(Color.DARK_GRAY);

        cardLayout.show(cardPanel, "menu");

        //frame.setVisible(true);
    }

    /**
     * Felvesz és visszaad egy addRoomView megjelenítőt
     */
    public RoomView addRoomView(CharacterView characters, ItemListView items) {
        RoomView roomView = new RoomView(characters, items);
        playPanel.add(roomView, BorderLayout.CENTER);

        return roomView;
    }

    /**
     * Felvesz és visszaad egy addMenuView megjelenítőt
     */
    public MenuView addMenuView() {
        MenuView menuView = new MenuView();
        JPanel menuContainer = new JPanel(new BorderLayout());
        menuContainer.add(menuView, BorderLayout.NORTH);
        menuContainer.setPreferredSize(menuView.getPreferredSize());
        playPanel.add(menuContainer, BorderLayout.WEST);

        return menuView;
    }

    /**
     * Felvesz egy addMainMenuView megjelenítőt
     */
    public void addMainMenuView() {
        mainMenuPanel.add(mainMenuView, BorderLayout.CENTER);
    }

    public CharacterView addCharacterView() {
        CharacterView characterView = new CharacterView();
        // Set characters of characterView here
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(characterView.getComboBox());
        playPanel.add(topPanel, BorderLayout.NORTH);

        return characterView;
    }

    /**
     * Láthatóvá teszi az ablakot.
     */
    public void display() {
        frame.setVisible(true);
    }

    /**
     * Összeállítja a menü megjelenését
     */
    public void menu() {
        GameMain.gamePanel = new GamePanel();

        GameMain.gamePanel.addMainMenuView();
        GameMain.gamePanel.display();
    }

    /**
     * Összeállítja az in-game megjelenítést
     */
    public void gaming() {
        GameMain.gamePanel = new GamePanel();

        GameEngine ge = GameMain.gameEngine;

        //A Room panel beállítása

        CharacterView roomCharacterView = new CharacterView();

        CharacterController roomChars = new CharacterController(new ArrayList<>(), roomCharacterView);

        ItemListView itemListView = new ItemListView();

        ItemListController itemListController = new ItemListController(itemListView, new ArrayList<>());

        RoomView roomView = addRoomView(roomCharacterView, itemListView);

        RoomController roomController = new RoomController(new Room(), roomView, itemListController, roomChars);

        //a bal felső sarokban a karaktercomboboxok beállítása

        CharacterView characterView1 = GameMain.gamePanel.addCharacterView();
        CharacterView characterView2 = GameMain.gamePanel.addCharacterView();
        CharacterView characterView3 = GameMain.gamePanel.addCharacterView();

        CharacterController allcontroller1 = new CharacterController(new ArrayList<>(ge.getStudents().values()), characterView1);
        CharacterController allcontroller2 = new CharacterController(new ArrayList<>(ge.getCleaners().values()), characterView2);
        CharacterController allcontroller3 = new CharacterController(new ArrayList<>(ge.getProfessors().values()), characterView3);

        //a bal oldali menu beállítása

        MenuView menuView = GameMain.gamePanel.addMenuView();

        MenuController menuController = new MenuController(menuView, ge, roomController);

        //listenerek beállítása
        ge.addListener(roomController);
        ge.addListener(itemListController);
        ge.addListener(allcontroller1);
        ge.addListener(allcontroller2);
        ge.addListener(allcontroller3);

        ge.addListener(menuController);



        GameMain.gamePanel.display();
    }
}