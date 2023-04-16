package at.ac.htlsteyr.tetris.Model;

import at.ac.htlsteyr.tetris.Exceptions.InvalidShapeException;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Objects;

public class Game {
    private static Gamemode gamemode;
    private Field[][] gameGrid;
    private TetrominoFactory tetroFactory;
    private Tetromino currentTetromino;

    public Game(Grid grid, Gamemode mode) {
        Game.gamemode = mode;
        this.gameGrid = grid.getGrid();
        this.tetroFactory = new TetrominoFactory();
        this.currentTetromino = null;
    }

    public Field[][] getGameGrid() {
        return gameGrid;
    }

    public void setGameGrid(Field[][] gameGrid) {
        this.gameGrid = gameGrid;
    }

    public TetrominoFactory getTetroFactory() {
        return tetroFactory;
    }

    public void setTetroFactory(TetrominoFactory tetroFactory) {
        this.tetroFactory = tetroFactory;
    }

    public Tetromino getCurrentTetromino() {
        return currentTetromino;
    }

    public void setCurrentTetromino(Tetromino currentTetromino) {
        this.currentTetromino = currentTetromino;
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

            @Override
            public void handle(long l) {
                count++;
                if (count == ticks) {

                    assert currentTetromino != null;
                    Tetromino currentTetro = currentTetromino;
                    int[][] tetroGrid = currentTetro.getTetroGrid();
                    ArrayList<Block> blocks = currentTetromino.getTetroBlocks();
                    Color color = blocks.get(0).blockColor;
                    int zaehler = 0;
                    // move tetro
                    for (int row = 0; row < tetroGrid.length; row++) {
                        for (int col = 0; col < tetroGrid[row].length; col++) {
                            if (tetroFactory.tetroHeight == 4) {
                                if (zaehler == 0) {
                                    yOffset = 1;
                                    zaehler = zaehler + 1;
                                }
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
                            } else {
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
            }
        };
        animationTimer.start();
    }
}
