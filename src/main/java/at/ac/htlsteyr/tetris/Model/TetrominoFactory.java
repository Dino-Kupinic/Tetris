/*-----------------------------------------------------------------------------
 *              Hoehere Technische Bundeslehranstalt STEYR
 *----------------------------------------------------------------------------*/
/**
 * Kurzbeschreibung
 *
 * @author  : Dino Kupinic
 * @date    : 22.4.2023
 *
 * @details
 * Class responsible for creating tetrominos based on a passed shape
 */

package at.ac.htlsteyr.tetris.Model;

import at.ac.htlsteyr.tetris.Exceptions.InvalidShapeException;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;

public class TetrominoFactory {
    /**
     * checks what shape was chosen by the random generator then makes a
     * new tetromino
     * @param shape shape of the tetromino
     * @return returns created tetromino
     * @throws InvalidShapeException thrown when there is no shape like wanted
     */
    public Tetromino createTetromino(TetrominoShapes shape) throws InvalidShapeException {
        switch (shape) {
            case SHAPE_I -> {
                Color col = Color.BLUE;
                return new Tetromino(new ArrayList<>(Arrays.asList(
                        new Block(new Point(1, 0), col),
                        new Block(new Point(1, 1), col),
                        new Block(new Point(1, 2), col),
                        new Block(new Point(1, 3), col)
                )), shape);
            }
            case SHAPE_L -> {
                Color col = Color.ORANGE;
                return new Tetromino(new ArrayList<>(Arrays.asList(
                        new Block(new Point(1, 1), col),
                        new Block(new Point(1, 2), col),
                        new Block(new Point(1, 3), col),
                        new Block(new Point(2, 3), col)
                )), shape);
            }
            case SHAPE_J -> {
                Color col = Color.DARKBLUE;
                return new Tetromino(new ArrayList<>(Arrays.asList(
                        new Block(new Point(1, 1), col),
                        new Block(new Point(1, 2), col),
                        new Block(new Point(1, 3), col),
                        new Block(new Point(0, 3), col)
                )), shape);
            }
            case SHAPE_T -> {
                Color col = Color.PURPLE;
                return new Tetromino(new ArrayList<>(Arrays.asList(
                        new Block(new Point(1, 2), col),
                        new Block(new Point(1, 3), col),
                        new Block(new Point(0, 3), col),
                        new Block(new Point(2, 3), col)
                )), shape);
            }
            case SHAPE_O -> {
                Color col = Color.YELLOW;
                return new Tetromino(new ArrayList<>(Arrays.asList(
                        new Block(new Point(1, 2), col),
                        new Block(new Point(0, 2), col),
                        new Block(new Point(1, 3), col),
                        new Block(new Point(0, 3), col)
                )), shape);
            }
            case SHAPE_S -> {
                Color col = Color.GREEN;
                return new Tetromino(new ArrayList<>(Arrays.asList(
                        new Block(new Point(0, 1), col),
                        new Block(new Point(0, 2), col),
                        new Block(new Point(1, 2), col),
                        new Block(new Point(1, 3), col)
                )), shape);
            }
            case SHAPE_Z -> {
                Color col = Color.RED;
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
