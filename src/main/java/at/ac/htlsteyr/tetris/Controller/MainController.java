package at.ac.htlsteyr.tetris.Controller;

import at.ac.htlsteyr.tetris.Model.Game;
import at.ac.htlsteyr.tetris.Model.Grid;
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
        root = new Pane();
        root.setPrefSize(300, 600);
        grid = new Grid(root);
        root = grid.generateGrid();
        anchorField.getChildren().add(root);

        Game game = new Game();
        game.createContent(grid);
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