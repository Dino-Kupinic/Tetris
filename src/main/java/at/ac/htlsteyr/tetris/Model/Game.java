package at.ac.htlsteyr.tetris.Model;

import at.ac.htlsteyr.tetris.Controller.MainController;
import at.ac.htlsteyr.tetris.Controller.SettingsController;
import at.ac.htlsteyr.tetris.Exceptions.InvalidShapeException;
import at.ac.htlsteyr.tetris.MainApplication;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Game {
    private static Gamemode gamemode;
    private Field[][] gameGrid;
    private TetrominoFactory tetroFactory;
    private Tetromino currentTetromino;
    boolean endOfGameGrid = false;
    int xOffset = 0;
    int yOffset = 0;

    boolean collisionCheck;

    boolean hasMoved = false;

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

    public int getxOffset() {
        return xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }

    public void setxOffset(int xOffset) {
        this.xOffset = xOffset;
    }

    public void setyOffset(int yOffset) {
        this.yOffset = yOffset;
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


        AnimationTimer animationTimer = new AnimationTimer() {
            int count = 0;
            final int ticks = 30;
            int loopLimit = 0;
            int tetroLength = 1;
            int rowOfChecking = 3;


            @Override
            public void handle(long l) {
                assert currentTetromino != null;
                Tetromino currentTetro = currentTetromino;
                int[][] tetroGrid = currentTetro.getTetroGrid();
                ArrayList<Block> blocks = currentTetromino.getTetroBlocks();
                Color color = blocks.get(0).blockColor;

                scene.setOnKeyPressed(keyEvent -> {
                    Controls controls = SettingsController.getInstance().getControlsObject();
                    String key = keyEvent.getCode().toString();


                    if (Objects.equals(controls.getMoveLeft(), key)) {
                        moveLeft(tetroGrid, getyOffset(), color, hasMoved, getxOffset());
                    }

                    if (Objects.equals(controls.getMoveRight(), key)) {
                        moveRight(tetroGrid, getyOffset(), color, hasMoved, getxOffset());
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

                    if (Objects.equals(key, "ESCAPE")) {
                        WindowManager windowManager = new WindowManager();
                        try {
                            windowManager.createNewWindow("Save Highscore", "highscore-view.fxml", 300, 200, Modality.APPLICATION_MODAL);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });

                count++;
                if (count == ticks) {
                    tetroCollision(tetroGrid);
                    //loopLimit hier verursacht, dass das Tetromino nicht nach ganzu unten fällt
                    tetroFall(tetroGrid, yOffset, color, hasMoved, xOffset);
                    hasMoved = true;
                    yOffset++;
                    count = 0;
                    loopLimit++;

                    if (endOfGameGrid) {
                        stop();
                        loopLimit = 0;
                        xOffset = 0;
                        yOffset = 0;
                        hasMoved = false;
                        count = 0;
                        endOfGameGrid = false;
                        createTetromino();
                        start();
                    }
                }
            }

            private void tetroCollision(int[][] tetroGrid) {
                try {
                    for (int checkRowTetroGrid1 = 0; checkRowTetroGrid1 < tetroGrid.length; checkRowTetroGrid1++) {
                        for (int checkRowTetroGrid = 0; checkRowTetroGrid < tetroGrid.length; checkRowTetroGrid++) {
                            if (tetroGrid[checkRowTetroGrid][rowOfChecking] == 1) {
                                if (gameGrid[checkRowTetroGrid + getxOffset()][getyOffset() + tetroGrid.length-1].isContainsBlock() && yOffset != 0) {
                                    collisionCheck = true;
                                    Score.increaseScoreOnCollision();
                                    stop();
                                    loopLimit = 0;
                                    collisionCheck = false;
                                    xOffset = 0;
                                    yOffset = 0;
                                    hasMoved = false;
                                    count = 0;
                                    endOfGameGrid = false;
                                    createTetromino();
                                    start();
                                } else if (gameGrid[checkRowTetroGrid + getxOffset()][getyOffset() + tetroGrid.length-1].isContainsBlock() && yOffset == 0) {
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
            }

            public int getxOffset() {
                return xOffset;
            }

            public int getyOffset() {
                return yOffset;
            }
        };
        animationTimer.start();
    }


    public void tetroFall(int[][] tetroGrid, int yOffset, Color color, boolean hasMoved, int xOffset) {
        // move tetro
        for (int row = 0; row < tetroGrid.length; row++) {
            for (int col = 0; col < tetroGrid[row].length; col++) {
                xOffset = getxOffset();

                try {
                    if (gameGrid[0][4 + yOffset].isContainsBlock())
                        endOfGameGrid = false;
                } catch (ArrayIndexOutOfBoundsException e) {
                    endOfGameGrid = true;
                }
                // tetroGrid
                if (tetroGrid[row][col] == 0) {
                    gameGrid[row + xOffset][col + yOffset].getFieldNode().setFill(Color.GRAY);
                }

                // tetro
                if (tetroGrid[row][col] == 1) {
                    gameGrid[row + xOffset][col + yOffset].getFieldNode().setFill(color);
                    gameGrid[row + xOffset][col + yOffset].setContainsBlock(true);
                }
                deleteLastBlock(hasMoved, yOffset, xOffset);
            }
        }
    }

    private void deleteLastBlock(boolean hasMoved, int yOffset, int xOffset) {
        // if shape is I delete last block like this
        if (currentTetromino.getShape().equals(TetrominoShapes.SHAPE_I)) {
            int firstcol = 0;
            for (int checkRow = 0; checkRow < 4; checkRow++) {
                if (hasMoved) {
                    gameGrid[checkRow + xOffset][firstcol + yOffset - 1].getFieldNode().setFill(Color.WHITE);
                    gameGrid[checkRow + xOffset][firstcol + yOffset - 1].setContainsBlock(false);

                }
            }
        }

        if (xOffset >= 1) {
            int firstcol = 0;
            for (int checkCol = 0; checkCol < 4; checkCol++) {
                if (hasMoved) {
                    gameGrid[firstcol + xOffset - 1][checkCol + yOffset].getFieldNode().setFill(Color.WHITE);
                    gameGrid[firstcol + xOffset - 1][checkCol + yOffset].setContainsBlock(false);

                }
            }
        }


        for (int checkRow = 0; checkRow < gameGrid.length; checkRow++) {
            for (int checkCol = 0; checkCol < gameGrid[checkRow].length; checkCol++) {
                if (gameGrid[checkRow][checkCol].getFieldNodeColor().equals(Color.GRAY)) {
                    gameGrid[checkRow][checkCol].getFieldNode().setFill(Color.WHITE);
                    gameGrid[checkRow][checkCol].setContainsBlock(false);

                }
            }
        }
    }

    public void moveRight(int[][] tetroGrid, int yOffset, Color color, boolean hasMoved, int xOffset) {
        if (xOffset < 6 && yOffset <= 16 && !collisionCheck) {
            xOffset++;
            setxOffset(xOffset);
            yOffset = getyOffset();
            for (int row = 0; row < tetroGrid.length; row++) {
                for (int col = 0; col < tetroGrid[row].length; col++) {
                    // tetroGrid
                    if (tetroGrid[row][col] == 0) {
                        gameGrid[row + xOffset][col + yOffset].getFieldNode().setFill(Color.GRAY);
                    }

                    // tetro
                    if (tetroGrid[row][col] == 1) {
                        gameGrid[row + xOffset][col + yOffset - 1].getFieldNode().setFill(color);
                        gameGrid[row + xOffset][col + yOffset - 1].setContainsBlock(true);

                    }

                    deleteLastBlock(hasMoved, yOffset, xOffset);

                }
            }
        }
    }

    public void moveLeft(int[][] tetroGrid, int yOffset, Color color, boolean hasMoved, int xOffset) {
        if (xOffset != 0 && yOffset <= 16 && !collisionCheck) {
            xOffset--;
            setxOffset(xOffset);
            for (int row = 0; row < tetroGrid.length; row++) {
                for (int col = 0; col < tetroGrid[row].length; col++) {
                    // tetroGrid
                    if (tetroGrid[row][col] == 0) {
                        gameGrid[row + xOffset][col + yOffset].getFieldNode().setFill(Color.GRAY);
                    }

                    // tetro
                    if (tetroGrid[row][col] == 1) {
                        gameGrid[row + xOffset][col + yOffset - 1].getFieldNode().setFill(color);
                        gameGrid[row + xOffset][col + yOffset - 1].setContainsBlock(true);

                    }

                    deleteLastBlock(hasMoved, yOffset, xOffset);

                }
            }
        }
    }


}



