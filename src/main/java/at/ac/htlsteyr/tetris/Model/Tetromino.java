package at.ac.htlsteyr.tetris.Model;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Tetromino {
    ArrayList<Block> TetrominoBlockList;

    int[][] tetroGrid = {
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
    };

    Field[][] gameGrid;
    Tetromino currentTetromino = null;

    public Tetromino(ArrayList<Block> blockList) {
        this.TetrominoBlockList = blockList;
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

    public void rotate(TetrominoMovements move, Grid grid, int xOffset) {
        gameGrid = grid.getGrid();
        ArrayList<Block> blocks = currentTetromino.getTetroBlocks();
        Color color = blocks.get(0).blockColor;
        switch (move) {
            case RIGHT -> {
                for (int row = 0; row < tetroGrid.length; row++) {
                    for (int col = 0; col < tetroGrid[0].length; col++) {
                        if (tetroGrid[row][col] == 1) {
                            gameGrid[row + xOffset][col].getFieldNode().setFill(color);
                            gameGrid[row + xOffset][col].setContainsBlock(true);
                            gameGrid[row + xOffset][col].updateDebugText();
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
