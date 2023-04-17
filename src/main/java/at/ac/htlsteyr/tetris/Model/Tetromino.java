package at.ac.htlsteyr.tetris.Model;

import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class Tetromino {
    private TetrominoShapes shape;
    private ArrayList<Block> TetrominoBlockList;

    int[][] tetroGrid = {
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
    };

    public Tetromino(ArrayList<Block> blockList, TetrominoShapes shape) {
        this.TetrominoBlockList = blockList;
        this.shape = shape;
        for (Block b : blockList) {
            int x = b.getBlockPoint().x();
            int y = b.getBlockPoint().y();
            tetroGrid[x][y] = 1;
        }
    }

    public int[][] getTetroGrid() {
        return tetroGrid;
    }

    public ArrayList<Block> getTetroBlocks() {
        return TetrominoBlockList;
    }

    public TetrominoShapes getShape() {
        return shape;
    }

    public void setShape(TetrominoShapes shape) {
        this.shape = shape;
    }

    public void rotate(TetrominoMovements move) {
        switch (move) {
            case RIGHT -> {
                for (int i = 0; i < tetroGrid.length; i++) {
                    for (int j = 0; j < tetroGrid[0].length; j++) {
                        if (tetroGrid[i][j] == 1) {
                            tetroGrid[i][j] = 0;
                            tetroGrid[i][j + 1] = 1;
                        }
                    }
                }
            }
            case LEFT -> {
                for (int i = 0; i < tetroGrid.length; i++) {
                    for (int j = 0; j < tetroGrid[0].length; j++) {
                        if (tetroGrid[i][j] == 1) {
                            tetroGrid[i][j] = 0;
                            tetroGrid[i][j - 1] = 1;
                        }
                    }
                }
            }
            case DOWN -> {
                for (int i = 0; i < tetroGrid.length; i++) {
                    for (int j = 0; j < tetroGrid[0].length; j++) {
                        if (tetroGrid[i][j] == 1) {
                            tetroGrid[i][j] = 0;
                            tetroGrid[i + 1][j] = 1;
                        }
                    }
                }
            }
            case FASTDROP -> {
                /*for(int i = 0; i < tetroGrid.length; i++) {
                    for(int j = 0; j < tetroGrid[0].length; j++) {
                        if(tetroGrid[i][j] == 1) {
                            tetroGrid[i][j] = 0;
                            tetroGrid[i+1][j] = 1;
                        }
                    }
                }*/
                System.out.println("Muss noch etabliert werden");
            }
        }
    }
}
