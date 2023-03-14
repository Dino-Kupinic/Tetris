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

    public void rotate(Movements move) {
        switch(move) {
            case RIGHT -> {
                //tetroGrid[][] = tetroGrid[]
            }
        }
    }
}
