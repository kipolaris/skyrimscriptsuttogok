package game.view;

import javax.swing.*;
import java.awt.*;

<<<<<<< HEAD
/**
 * View osztály, amely a játék fő paneljét jeleníti meg.
 */
public class GamePanel extends JPanel {
    private MenuView menuView;
    private CharacterView characterView;
    private ItemListView itemListView;
    private InfoView infoView;
=======
public class GamePanel {
>>>>>>> a63547aaa1075e7a31041c26ab54bb874f24e8e8

    int frame_size = 800;

    private JFrame frame;

<<<<<<< HEAD
        // Create views
        menuView = new MenuView();
        characterView = new CharacterView();
        itemListView = new ItemListView();
        infoView = new InfoView();

        // Add views to the panel
        add(menuView, BorderLayout.WEST);
        add(characterView.getComboBox(), BorderLayout.CENTER);
        add(itemListView.getComboBox(), BorderLayout.EAST);
        add(infoView, BorderLayout.SOUTH);

=======
    public GamePanel() {
        frame = new JFrame();

        // set common frame properties here
        frame.setSize(frame_size, frame_size);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
>>>>>>> a63547aaa1075e7a31041c26ab54bb874f24e8e8

        // set the background color of the JFrame
        frame.getContentPane().setBackground(Color.DARK_GRAY);
    }

    public void addDoorView() {
        DoorView doorView = new DoorView(frame_size);
        frame.add(doorView, BorderLayout.CENTER);
    }

<<<<<<< HEAD
    public InfoView getInfoView() {
        return infoView;
    }
}
=======
    public void addItemListView() {
        ItemListView itemListView = new ItemListView();

        // Please call itemListView.setItems(yourItemsList) here, you need replace yourItemsList with the actual data

        frame.add(itemListView.getComboBox(), BorderLayout.EAST);
    }

    public void addMenuView() {
        MenuView menuView = new MenuView();
        frame.add(menuView, BorderLayout.WEST);
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

    public static void main(String[] args) {
        GamePanel gamePanel = new GamePanel();

        gamePanel.addDoorView();
        gamePanel.addItemListView();
        gamePanel.addMenuView();
        gamePanel.addCharacterView();

        // add other views
        gamePanel.display();
    }
}
>>>>>>> a63547aaa1075e7a31041c26ab54bb874f24e8e8
