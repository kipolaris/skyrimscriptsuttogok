package game.view;

import javax.swing.*;
import java.awt.*;

public class GamePanel {

    int frame_size = 800;

    private JFrame frame;

    public GamePanel() {
        frame = new JFrame();

        // set common frame properties here
        frame.setSize(frame_size, frame_size);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // set the background color of the JFrame
        frame.getContentPane().setBackground(Color.DARK_GRAY);
    }

    public void addDoorView() {
        RoomView roomView = new RoomView(new CharacterView(), new ItemListView());
        frame.add(roomView, BorderLayout.CENTER);
    }

    public void addItemListView() {
        ItemListView itemListView = new ItemListView();

        // Please call itemListView.setItems(yourItemsList) here, you need replace yourItemsList with the actual data

        frame.add(itemListView.getComboBox(), BorderLayout.EAST);
    }

    public void addMenuView() {
        MenuView menuView = new MenuView();
        frame.add(menuView, BorderLayout.WEST);
    }

    public void addMainMenuView() {
        MainMenuView mainMenuView = new MainMenuView();
        frame.add(mainMenuView, BorderLayout.CENTER);
    }

    public void addCharacterView() {
        CharacterView characterView = new CharacterView();
        // Set characters of characterView here
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(characterView.getComboBox());
        frame.add(topPanel, BorderLayout.NORTH);
    }

    // Add more methods to add other views

    public void display() {
        frame.setVisible(true);
    }

    public void start() {
        menu();
        //gaming();
    }

    public void menu() {
        GamePanel gamePanel = new GamePanel();
        gamePanel.addMainMenuView();
        gamePanel.display();
    }
    public void gaming() {
        GamePanel gamePanel = new GamePanel();
        gamePanel.addDoorView();
        gamePanel.addItemListView();
        gamePanel.addMenuView();
        gamePanel.addCharacterView();
        gamePanel.display();
    }
}