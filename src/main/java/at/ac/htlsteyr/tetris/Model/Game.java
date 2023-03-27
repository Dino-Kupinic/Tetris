package at.ac.htlsteyr.tetris.Model;

import at.ac.htlsteyr.tetris.Exceptions.InvalidShapeException;
import javafx.scene.paint.Color;

import java.util.Objects;

public class Game {

    public void createContent(Grid grid) {
        Field[][] myGrid = grid.getGrid();
        TetrominoFactory tetroFactory = new TetrominoFactory();

        try {
            // generate random number
            int MIN_NUM = 0;
            int MAX_NUM = TetrominoShapes.values().length;
            int randNumber = (int) (Math.random() * MAX_NUM + MIN_NUM);

            // assign random number to corresponding Shape
            TetrominoShapes shape = null;
            for (TetrominoShapes s : TetrominoShapes.values()) {
                if (randNumber == s.ordinal()) {
                    shape = s;
                }
            }

            // create Tetromino and add to grid
            Tetromino tetro = tetroFactory.createTetromino(Objects.requireNonNull(shape));
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
