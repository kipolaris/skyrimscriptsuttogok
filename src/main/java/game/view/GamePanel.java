package game.view;

import game.controller.MainMenuController;
import game.model.main.GameMain;

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

        frame.setVisible(true);
    }

    public void addRoomView() {
        RoomView roomView = new RoomView(new CharacterView(), new ItemListView());
        playPanel.add(roomView, BorderLayout.CENTER);
    }

    public void addItemListView() {
        ItemListView itemListView = new ItemListView();

        // Please call itemListView.setItems(yourItemsList) here, you need replace yourItemsList with the actual data

        playPanel.add(itemListView.getComboBox(), BorderLayout.EAST);
    }

    public void addMenuView() {
        MenuView menuView = new MenuView();
        playPanel.add(menuView, BorderLayout.WEST);
    }

    public void addMainMenuView() {
        mainMenuPanel.add(mainMenuView, BorderLayout.CENTER);
    }

    public void addCharacterView() {
        CharacterView characterView = new CharacterView();
        // Set characters of characterView here
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(characterView.getComboBox());
        playPanel.add(topPanel, BorderLayout.NORTH);
    }

    // Add more methods to add other views

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
        GameMain.gamePanel.addRoomView();
        GameMain.gamePanel.addMenuView();
        GameMain.gamePanel.addCharacterView();
        GameMain.gamePanel.display();
    }
}