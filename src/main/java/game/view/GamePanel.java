package game.view;

import game.controller.*;
import game.model.entities.Character;
import game.model.entities.Student;
import game.model.entities.items.Item;
import game.model.main.GameEngine;
import game.model.main.GameMain;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.*;

public class GamePanel {

    int frame_size = 800;

    private JFrame frame;

    private static JPanel cardPanel = new JPanel();

    private JPanel mainMenuPanel = new JPanel();

    private static JPanel playPanel = new JPanel();

    private static CardLayout cardLayout = new CardLayout();

    public static CardLayout getCardLayout(){
        return cardLayout;
    }

    public static JPanel getPlayPanel(){
        return playPanel;
    }

    public static JPanel getCardPanel(){
        return cardPanel;
    }

    private MainMenuView mainMenuView = new MainMenuView();

    private MainMenuController mainMenuController = new MainMenuController(mainMenuView);

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

    public RoomView addRoomView() {
        RoomView roomView = new RoomView(new CharacterView(), new ItemListView());
        playPanel.add(roomView, BorderLayout.CENTER);

        return roomView;
    }

    public MenuView addMenuView() {
        MenuView menuView = new MenuView();
        playPanel.add(menuView, BorderLayout.WEST);

        return menuView;
    }

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

    public void display() {
        frame.setVisible(true);
    }

    public void menu() {

        GameMain.gamePanel = new GamePanel();


        GameMain.gamePanel.addMainMenuView();
        GameMain.gamePanel.display();
    }
    public void gaming() {
        GameMain.gamePanel = new GamePanel();

        GameEngine ge = GameMain.gameEngine;
        GameMain.gamePanel.addRoomView();

        //A Room panel beállítása

        CharacterView roomCharacterView = new CharacterView();

        List<Character> currentRoomsChars = ge.getCurrent().getLocation().getCharacters();

        CharacterController roomChars = new CharacterController(currentRoomsChars, roomCharacterView);

        ItemListView itemListView = new ItemListView();

        List<Item> currentRoomItems = ge.getCurrent().getLocation().getItems();

        ItemListController itemListController = new ItemListController(itemListView, currentRoomItems);

        RoomView roomView = addRoomView();

        RoomController roomController = new RoomController(ge.getCurrent().getLocation(), roomView);

        //a bal felső sarokban a karaktercomboboxok beállítása

        CharacterView characterView1 = GameMain.gamePanel.addCharacterView();
        CharacterView characterView2 = GameMain.gamePanel.addCharacterView();
        CharacterView characterView3 = GameMain.gamePanel.addCharacterView();

        CharacterController allcontroller1 = new CharacterController(new ArrayList<>(ge.getStudents().values()), characterView1);
        CharacterController allcontroller2 = new CharacterController(new ArrayList<>(ge.getCleaners().values()), characterView2);
        CharacterController allcontroller3 = new CharacterController(new ArrayList<>(ge.getProfessors().values()), characterView3);

        //a bal oldali menu beállítása

        MenuView menuView = GameMain.gamePanel.addMenuView();

        new MenuController(menuView, ge, roomController);

        //listenerek beállítása
        ge.addListener(roomController);
        ge.addListener(itemListController);
        ge.addListener(roomChars);
        ge.addListener(allcontroller1);
        ge.addListener(allcontroller2);
        ge.addListener(allcontroller3);



        GameMain.gamePanel.display();
    }
}