package at.ac.htlsteyr.tetris.Model;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Field extends StackPane {
    private Rectangle fieldNode;
    private boolean containsBlock;
    private int xPosition;
    private int yPosition;
    private Grid grid;
    private final int FIELD_SIZE = 30;

    public Field(int x, int y) {
        this.xPosition = x;
        this.yPosition = y;
        fieldNode = new Rectangle(FIELD_SIZE, FIELD_SIZE);

        fieldNode.setFill(Color.RED);
        fieldNode.setVisible(true);

        getChildren().add(fieldNode);
        setTranslateX(x*FIELD_SIZE);
        setTranslateY(y*FIELD_SIZE);
    }



}
