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
        Field[][] myGrid = grid.getGrid();

        TetrominoFactory tetroFac = new TetrominoFactory();
        try {
            int MAX_NUM = 6;
            int MIN_NUM = 0;
            int randNumber = (int) (Math.random() * MAX_NUM + MIN_NUM);

            Shapes shape = null;
            switch (randNumber) {
                case 0 -> {
                    shape = Shapes.SHAPE_I;
                }
                case 1 -> {
                    shape = Shapes.SHAPE_L;
                }
                case 2 -> {
                    shape = Shapes.SHAPE_J;
                }
                case 3 -> {
                    shape = Shapes.SHAPE_T;
                }
                case 4 -> {
                    shape = Shapes.SHAPE_O;
                }
                case 5 -> {
                    shape = Shapes.SHAPE_S;
                }
                case 6 -> {
                    shape = Shapes.SHAPE_Z;
                }
            }

            Tetromino tetro = tetroFac.createTetromino(shape);
            int[][] tetroGrid = tetro.getTetroGrid();

            for (int row = 0; row < tetroGrid.length; row++) {
                for (int col = 0; col < tetroGrid[row].length; col++) {
                    if (tetroGrid[row][col] == 1) {
                        myGrid[row][col].getFieldNode().setFill(Color.BLUE);
                        myGrid[row][col] = new Field(row, col);
                    }
                }
            }



        } catch (InvalidShapeException e) {
            throw new RuntimeException(e);
        }


    }


}