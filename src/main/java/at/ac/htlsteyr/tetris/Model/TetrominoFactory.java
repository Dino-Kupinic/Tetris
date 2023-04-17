package at.ac.htlsteyr.tetris.Model;

import at.ac.htlsteyr.tetris.Exceptions.InvalidShapeException;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.Arrays;

public class TetrominoFactory {
    int tetroHeight = 0;
    int tetroWidth = 0;
    public Tetromino createTetromino(TetrominoShapes shape) throws InvalidShapeException {
        switch (shape) {
            case SHAPE_I -> {
                Color col = Color.BLUE;
                tetroHeight=4;
                return new Tetromino(new ArrayList<>(Arrays.asList(
                        new Block(new Point(1, 0), col),
                        new Block(new Point(1, 1), col),
                        new Block(new Point(1, 2), col),
                        new Block(new Point(1, 3), col)
                )), shape);
            }
            case SHAPE_L -> {
                Color col = Color.ORANGE;
                tetroHeight=3;
                return new Tetromino(new ArrayList<>(Arrays.asList(
                        new Block(new Point(1, 1), col),
                        new Block(new Point(1, 2), col),
                        new Block(new Point(1, 3), col),
                        new Block(new Point(2, 3), col)
                )), shape);
            }
            case SHAPE_J -> {
                Color col = Color.DARKBLUE;
                tetroHeight=3;
                return new Tetromino(new ArrayList<>(Arrays.asList(
                        new Block(new Point(1, 1), col),
                        new Block(new Point(1, 2), col),
                        new Block(new Point(1, 3), col),
                        new Block(new Point(0, 3), col)
                )), shape);
            }
            case SHAPE_T -> {
                Color col = Color.PURPLE;
                tetroHeight=2;
                return new Tetromino(new ArrayList<>(Arrays.asList(
                        new Block(new Point(1, 2), col),
                        new Block(new Point(1, 3), col),
                        new Block(new Point(0, 3), col),
                        new Block(new Point(2, 3), col)
                )), shape);
            }
            case SHAPE_O -> {
                Color col = Color.YELLOW;
                tetroHeight=2;
                return new Tetromino(new ArrayList<>(Arrays.asList(
                        new Block(new Point(1, 2), col),
                        new Block(new Point(0, 2), col),
                        new Block(new Point(1, 3), col),
                        new Block(new Point(0, 3), col)
                )), shape);
            }
            case SHAPE_S -> {
                Color col = Color.GREEN;
                tetroHeight=2;
                return new Tetromino(new ArrayList<>(Arrays.asList(
                        new Block(new Point(0, 3), col),
                        new Block(new Point(1, 3), col),
                        new Block(new Point(1, 2), col),
                        new Block(new Point(2, 2), col)
                )), shape);
            }
            case SHAPE_Z -> {
                Color col = Color.RED;
                tetroHeight=2;
                return new Tetromino(new ArrayList<>(Arrays.asList(
                        new Block(new Point(0, 2), col),
                        new Block(new Point(1, 2), col),
                        new Block(new Point(1, 3), col),
                        new Block(new Point(2, 3), col)
                )), shape);
            }
            default -> throw new InvalidShapeException();
        }
    }
}
