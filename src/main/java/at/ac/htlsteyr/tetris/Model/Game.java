package at.ac.htlsteyr.tetris.Model;

import at.ac.htlsteyr.tetris.Exceptions.InvalidShapeException;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Objects;

public class Game {
    Field[][] gameGrid;
    TetrominoFactory tetroFactory;
    Tetromino currentTetromino;
    Grid grid;

    public Game(Grid grid) {
        gameGrid = grid.getGrid();
        tetroFactory = new TetrominoFactory();
        currentTetromino = null;
        this.grid = grid;
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

            // create Tetromino
            currentTetromino = tetroFactory.createTetromino(Objects.requireNonNull(shape));
        } catch (InvalidShapeException e) {
            throw new RuntimeException(e);
        }
    }

    public void startGameLoop() {
        AnimationTimer animationTimer = new AnimationTimer() {
            int count = 0;
            final int ticks = 30;
            int xOffset = 0;
            int yOffset = 0;
            int loopLimit = 0;

            @Override
            public void handle(long l) {
                count++;
                if (count == ticks) {

                    assert currentTetromino != null;
                    Tetromino currentTetro = currentTetromino;
                    int[][] tetroGrid = currentTetro.getTetroGrid();
                    ArrayList<Block> blocks = currentTetromino.getTetroBlocks();
                    Color color = blocks.get(0).blockColor;

                    // move tetro
                    if (loopLimit <= 16) {
                        for (int row = 0; row < tetroGrid.length; row++) {
                            for (int col = 0; col < tetroGrid[row].length; col++) {

                                // tetroGrid
                                if (tetroGrid[row][col] == 0) {
                                    gameGrid[row][col + yOffset].getFieldNode().setFill(Color.GRAY);
                                }

                                // tetro
                                if (tetroGrid[row][col] == 1) {
                                    gameGrid[row][col + yOffset].getFieldNode().setFill(color);
                                    gameGrid[row][col + yOffset].setContainsBlock(true);
                                    gameGrid[row][col + yOffset].updateDebugText();
                                }


                                for (int checkRow = 0; checkRow < gameGrid.length; checkRow++) {
                                    for (int checkCol = 0; checkCol < gameGrid[checkRow].length; checkCol++) {
                                        if (gameGrid[checkRow][checkCol].getFieldNodeColor().equals(Color.GRAY)) {
                                            gameGrid[checkRow][checkCol].getFieldNode().setFill(Color.WHITE);
                                            gameGrid[checkRow][checkCol].setContainsBlock(false);
                                            gameGrid[checkRow][checkCol].updateDebugText();
                                        }
                                    }
                                }
                            }
                        }
                        xOffset++;
                        yOffset++;
                        count = 0;
                    }
                    loopLimit++;
                }
            }
        };
        animationTimer.start();
    }
}
