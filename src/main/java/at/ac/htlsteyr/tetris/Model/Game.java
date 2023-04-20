package at.ac.htlsteyr.tetris.Model;

import at.ac.htlsteyr.tetris.Controller.MainController;
import at.ac.htlsteyr.tetris.Exceptions.InvalidShapeException;
import at.ac.htlsteyr.tetris.MainApplication;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
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
        Scene scene = MainApplication.getInstance().getScene();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                System.out.println(keyEvent.getCode());
            }
        });

        AnimationTimer animationTimer = new AnimationTimer() {
            int count = 0;
            final int ticks = 30;
            int xOffset = 0;
            int yOffset = 0;
            int loopLimit = 0;
            boolean hasMoved = false;
            int tetroLength = 1;
            int rowOfChecking = 3;


            @Override
            public void handle(long l) {
                count++;
                if (count == ticks) {
                    assert currentTetromino != null;
                    Tetromino currentTetro = currentTetromino;
                    int[][] tetroGrid = currentTetro.getTetroGrid();
                    ArrayList<Block> blocks = currentTetromino.getTetroBlocks();
                    Color color = blocks.get(0).blockColor;


                    try {
                        for (int checkRowTetroGrid1 = 0; checkRowTetroGrid1 < tetroGrid.length - 1; checkRowTetroGrid1++) {
                            for (int checkRowTetroGrid = 0; checkRowTetroGrid < tetroGrid.length; checkRowTetroGrid++) {
                                if (tetroGrid[checkRowTetroGrid][rowOfChecking] == 1) {
                                    if (gameGrid[checkRowTetroGrid][yOffset + tetroGrid.length].isContainsBlock()) {
                                        stop();
                                    }
                                } else {
                                    rowOfChecking--;
                                }
                            }
                            rowOfChecking = 3;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        stop();
                    }

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

                                if (currentTetromino.getShape().equals(TetrominoShapes.SHAPE_I)) {
                                    int firstrow = 0;
                                    System.out.println("----------I-Tetromino----------");
                                    for (int checkRow = 0; checkRow < 4; checkRow++) {
                                        if (hasMoved) {
                                            gameGrid[checkRow][firstrow + yOffset - 1].getFieldNode().setFill(Color.WHITE);
                                            gameGrid[checkRow][firstrow + yOffset - 1].setContainsBlock(false);
                                            gameGrid[checkRow][firstrow + yOffset - 1].updateDebugText();
                                        }
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
                        hasMoved = true;
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
