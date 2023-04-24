/*-----------------------------------------------------------------------------
 *              Hoehere Technische Bundeslehranstalt STEYR
 *----------------------------------------------------------------------------*/
/**
 * Kurzbeschreibung
 *
 * @author : Jannick Angerer, Dino Kupinic
 * @date : 22.4.2023
 *
 * @details
 * Class used to display each field in a grid
 */

package at.ac.htlsteyr.tetris.Model;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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

        this.getChildren().addAll(fieldNode, debugText);
    }

    public void setContainsBlock(boolean bool) {
        containsBlock = bool;
    }

    public Rectangle getFieldNode() {
        return fieldNode;
    }

    public boolean isContainsBlock() {
        return containsBlock;
    }

    public Paint getFieldNodeColor() {
        return fieldNode.getFill();
    }
}
