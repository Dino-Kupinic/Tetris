package at.ac.htlsteyr.tetris.Controller;

import at.ac.htlsteyr.tetris.Model.Grid;
import at.ac.htlsteyr.tetris.Saves.JSONhandler;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class MainController {
    @FXML
    AnchorPane anchorField;
    private static Pane root = new Pane();
    private static MainController instance;

    public void initialize() {
        Grid grid = new Grid(root);
        grid.generateGrid();
        anchorField.getChildren().add(root);
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