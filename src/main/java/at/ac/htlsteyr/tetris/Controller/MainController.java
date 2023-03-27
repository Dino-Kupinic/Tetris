package at.ac.htlsteyr.tetris.Controller;

import at.ac.htlsteyr.tetris.Model.Game;
import at.ac.htlsteyr.tetris.Model.Grid;
import at.ac.htlsteyr.tetris.Model.GridSize;
import at.ac.htlsteyr.tetris.Saves.JSONhandler;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class MainController {
    @FXML
    private AnchorPane anchorField;
    private Pane root;
    private Grid grid;
    private static MainController instance;

    public void initialize() {
        // setup Pane
        final int WIDTH = GridSize.width;
        final int HEIGHT = GridSize.height;
        root = new Pane();
        root.setPrefSize(HEIGHT, WIDTH);

        // init Grid
        grid = new Grid(root);
        root = grid.generateGrid();
        anchorField.getChildren().add(root);

        // init Game
        Game game = new Game();
        game.createContent(grid);

        // Test JSON
        JSONhandler handler = new JSONhandler();
        handler.writeToJSON("Dino", 1000);
        System.out.println(handler.getPlayerInfos("Wolfi"));
    }

    public MainController() {
        instance = this;
    }

    public static MainController getInstance() {
        if (instance == null) {
            instance = new MainController();
        }
        return instance;
    }
}
