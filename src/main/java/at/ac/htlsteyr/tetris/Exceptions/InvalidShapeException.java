package at.ac.htlsteyr.tetris.Exceptions;

public class InvalidShapeException extends Exception {
    public InvalidShapeException() {
        super("Passed invalid shape type parameter");
    }
}
