package game.view;

import javax.swing.*;
import java.awt.*;

public class GamePanel {

    int frame_size = 800;

    private JFrame frame;

    /**
     * Zéró paraméteres konstruktor.
     */
    public GamePanel() {
        frame = new JFrame();

        // set common frame properties here
        frame.setSize(frame_size, frame_size);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // set the background color of the JFrame
        frame.getContentPane().setBackground(Color.DARK_GRAY);
    }
    
    /**
     * Két paraméteres konstruktor.
     *
     * @param gameEngine a GameEngine osztály egy példánya
     * @param roomController a RoomController osztály egy példánya
     */
    public GamePanel(GameEngine gameEngine, RoomController roomController) {
        // Elrendezés beállítása
        setLayout(new BorderLayout());

        // Nézetek létrehozása
        menuView = new MenuView();
        characterView = new CharacterView();
        itemListView = new ItemListView();

        // Nézetek hozzáadása a panelhez
        add(menuView, BorderLayout.WEST);
        add(characterView.getComboBox(), BorderLayout.CENTER);
        add(itemListView.getComboBox(), BorderLayout.EAST);

        // Vezérlők inicializálása
        menuController = new MenuController(menuView, gameEngine, roomController);

        // Minden karakter összegyűjtése egy mapbe
        ArrayList<Character> allCharacters = new ArrayList<Character>();
        allCharacters.addAll(gameEngine.getStudents().values());
        allCharacters.addAll(gameEngine.getProfessors().values());
        allCharacters.addAll(gameEngine.getCleaners().values());

        characterController = new CharacterController(allCharacters, characterView);
        itemListController = new ItemListController(itemListView, gameEngine.getItems());

        // Nézetek frissítése
        menuController.onModelChange();
        characterController.onModelChange();
        itemListController.onModelChange();
    }

    public void addDoorView() {
        DoorView doorView = new DoorView(frame_size);
        frame.add(doorView, BorderLayout.CENTER);
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