package at.ac.htlsteyr.tetris.Model;

import java.util.ArrayList;

public class Tetromino {

    int[][] tetroGrid = {
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
    };

    public Tetromino(ArrayList<Block> blockList) {
        for (Block b : blockList) {
            int x = b.getBlockPoint().getX();
            int y = b.getBlockPoint().getY();
            tetroGrid[x][y] = 1;
        }
    }

    public int[][] getTetroGrid() {
        return tetroGrid;
    }

    public void rotate(Movements move) {
        switch(move) {
            case RIGHT -> {
                for(int i = 0; i < tetroGrid.length; i++) {
                    for(int j = 0; j < tetroGrid[0].length; j++) {
                        if(tetroGrid[i][j] == 1) {
                            tetroGrid[i][j] = 0;
                            tetroGrid[i][j+1] = 1;
                        }
                    }
                }
            }
            case LEFT -> {
                for(int i = 0; i < tetroGrid.length; i++) {
                    for(int j = 0; j < tetroGrid[0].length; j++) {
                        if(tetroGrid[i][j] == 1) {
                            tetroGrid[i][j] = 0;
                            tetroGrid[i][j-1] = 1;
                        }
                    }
                }
            }
            case DOWN -> {
                for(int i = 0; i < tetroGrid.length; i++) {
                    for(int j = 0; j < tetroGrid[0].length; j++) {
                        if(tetroGrid[i][j] == 1) {
                            tetroGrid[i][j] = 0;
                            tetroGrid[i+1][j] = 1;
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
