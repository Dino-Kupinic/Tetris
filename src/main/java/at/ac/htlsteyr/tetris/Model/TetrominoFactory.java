package at.ac.htlsteyr.tetris.Model;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class TetrominoFactory {
    public Tetromino createTetromino(Shapes shape) {
        ArrayList<Block> arr = null;
        Color col;
        switch (shape) {
            case SHAPE_I -> {
                col = Color.BLUE;
                arr = new ArrayList<Block>(
                        new Block(new Point(0, 1), col),
                        new Block(new Point(1, 1), col),
                        new Block(new Point(1, 2), col),
                        new Block(new Point(1, 3), col)
                );
            }
            case SHAPE_L -> {
                col = Color.ORANGE;
                arr = new ArrayList<Block>(
                        new Block(new Point(1, 1), col),
                        new Block(new Point(1, 2), col),
                        new Block(new Point(1, 3), col),
                        new Block(new Point(2, 3), col)
                );
            }
            case SHAPE_J -> {
                col = Color.DARKBLUE;
                arr = new ArrayList<Block>(
                        new Block(new Point(1, 1), col),
                        new Block(new Point(1, 2), col),
                        new Block(new Point(1, 3), col),
                        new Block(new Point(0, 3), col)
                );
            }
            case SHAPE_T -> {
                col = Color.PURPLE;
                arr = new ArrayList<Block>(
                        new Block(new Point(1, 2), col),
                        new Block(new Point(1, 3), col),
                        new Block(new Point(0, 3), col),
                        new Block(new Point(2, 3), col)
                );
            }
            case SHAPE_O -> {
                col = Color.YELLOW;
                arr = new ArrayList<Block>(
                        new Block(new Point(1, 2), col),
                        new Block(new Point(0, 2), col),
                        new Block(new Point(1, 3), col),
                        new Block(new Point(0, 3), col)
                );
            }
            case SHAPE_S -> {
                col = Color.GREEN;
                arr = new ArrayList<Block>(
                        new Block(new Point(0, 3), col),
                        new Block(new Point(1, 3), col),
                        new Block(new Point(1, 2), col),
                        new Block(new Point(2, 2), col)
                );
            }
            case SHAPE_Z -> {
                col = Color.RED;
                arr = new ArrayList<Block>(
                        new Block(new Point(0, 2), col),
                        new Block(new Point(1, 2), col),
                        new Block(new Point(1, 3), col),
                        new Block(new Point(2, 3), col)
                );
            }
        }
        return new Tetromino(arr);
    }
}
