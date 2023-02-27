package at.ac.htlsteyr.tetris.Model;

public class TetrominoFactory {
    public TetrominoFactory(Shapes shape) {
        switch (shape) {
            case LONG -> {
                return new Tetromino();
            }
        }
    }
}
