package at.ac.htlsteyr.tetris.Controller;

import at.ac.htlsteyr.tetris.Exceptions.InvalidShapeException;
import at.ac.htlsteyr.tetris.Model.*;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class MainController {
    @FXML
    AnchorPane anchorField;

    private Pane root = new Pane();
    Grid grid;


    public void initialize() {
        root.setPrefSize(300, 600);
        grid = new Grid(root);
        root = grid.generateGrid();
        anchorField.getChildren().add(root);

        createContent();
    }

    public void createContent() {
        int x, y;

        Field[][] myGrid = grid.getGrid();

        myGrid[1][0].getFieldNode().setFill(Color.BLUE);
        myGrid[1][0] = new Field(1, 0);
        myGrid[1][1].getFieldNode().setFill(Color.BLUE);
        myGrid[1][1] = new Field(1, 0);
        myGrid[1][2].getFieldNode().setFill(Color.BLUE);
        myGrid[1][2] = new Field(1, 0);

    }


}