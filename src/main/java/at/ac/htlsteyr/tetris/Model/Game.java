package at.ac.htlsteyr.tetris.Model;

import at.ac.htlsteyr.tetris.Controller.MainController;
import at.ac.htlsteyr.tetris.Controller.SettingsController;
import at.ac.htlsteyr.tetris.Exceptions.InvalidShapeException;
import at.ac.htlsteyr.tetris.MainApplication;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
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
        if (gamemode == Gamemode.TIMED) {
            Timer timer = new Timer();
            timer.startTimer(MainController.getInstance());

            MainController.getInstance().setTimerLabelVisible();
        }

        Scene scene = MainApplication.getInstance().getScene();
        scene.setOnKeyPressed(keyEvent -> {
            Controls controls = SettingsController.getInstance().getControlsObject();
            String key = keyEvent.getCode().toString();

            if (Objects.equals(controls.getMoveLeft(), key)) {
                System.out.println("move left");
            }

            if (Objects.equals(controls.getMoveRight(), key)) {
                System.out.println("move right");
            }

            if (Objects.equals(controls.getHold(), key)) {
                System.out.println("hold");
            }

            if (Objects.equals(controls.getRotate(), key)) {
                System.out.println("rotate");
            }

            if (Objects.equals(controls.getSoftdrop(), key)) {
                System.out.println("soft drop");
            }

            if (Objects.equals(controls.getFastDrop(), key)) {
                System.out.println("fast drop");
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


                    tetroCollision(tetroGrid);

                    if (loopLimit <= 16) {
                        tetroFall(tetroGrid, yOffset, color, hasMoved, xOffset);
                        loopLimit++;
                        hasMoved = true;
                        xOffset++;
                        yOffset++;
                        count = 0;
                    }

                }
            }

            private void tetroCollision(int[][] tetroGrid) {
                try {
                    for (int checkRowTetroGrid1 = 0; checkRowTetroGrid1 < tetroGrid.length - 1; checkRowTetroGrid1++) {
                        for (int checkRowTetroGrid = 0; checkRowTetroGrid < tetroGrid.length; checkRowTetroGrid++) {
                            if (tetroGrid[checkRowTetroGrid][rowOfChecking] == 1) {
                                if (gameGrid[checkRowTetroGrid][yOffset + tetroGrid.length].isContainsBlock()) {
                                    stop();
                                    createTetromino();

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
            }
        };
        animationTimer.start();
    }


    public void tetroFall(int[][] tetroGrid, int yOffset, Color color, boolean hasMoved, int xOffset) {
        // move tetro

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


                deleteLastBlock(hasMoved, yOffset);

            }
        }

    }

    private void deleteLastBlock(boolean hasMoved, int yOffset) {
        // if shape is I delete last block like this
        if (currentTetromino.getShape().equals(TetrominoShapes.SHAPE_I)) {
            int firstrow = 0;
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



