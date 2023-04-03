package at.ac.htlsteyr.tetris.Model;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Field extends StackPane {
    private Rectangle fieldNode;
    private boolean containsBlock;
    private final int FIELD_SIZE = 30;

    private final Text debugText = new Text();

    public Field(int x, int y, boolean containsTetrominoBlock) {
        containsBlock = containsTetrominoBlock;

        fieldNode = new Rectangle(FIELD_SIZE, FIELD_SIZE);
        fieldNode.setFill(Color.WHITE);
        fieldNode.setStroke(Color.BLACK);
        fieldNode.setVisible(true);

        setTranslateX(x * FIELD_SIZE);
        setTranslateY(y * FIELD_SIZE);

        updateDebugText();

        this.getChildren().addAll(fieldNode, debugText);
    }

    public void updateDebugText() {
        if (containsBlock) {
            debugText.setText("1");
        } else {
            debugText.setText("0");
        }
        System.out.println(containsBlock);
    }

    public void setContainsBlock(boolean bool) {
        containsBlock = bool;
    }

    public Rectangle getFieldNode() {
        return fieldNode;
    }
}
