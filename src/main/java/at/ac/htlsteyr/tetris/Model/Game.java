package at.ac.htlsteyr.tetris.Model;

import at.ac.htlsteyr.tetris.Exceptions.InvalidShapeException;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Objects;

public class Game {
    Field[][] gameGrid;
    TetrominoFactory tetroFactory;

    public Game(Grid grid) {
        gameGrid = grid.getGrid();
        tetroFactory = new TetrominoFactory();
    }

    public void createTetromino() {
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
            ArrayList<Block> blocks = tetro.getTetroBlocks();
            Color color = blocks.get(0).blockColor;

            for (int row = 0; row < tetroGrid.length; row++) {
                for (int col = 0; col < tetroGrid[row].length; col++) {
                    if (tetroGrid[row][col] == 1) {
                        gameGrid[row][col].getFieldNode().setFill(color);
                        gameGrid[row][col] = new Field(row, col);
                    }
                }
            }
        } catch (InvalidShapeException e) {
            throw new RuntimeException(e);
        }
    }


}
