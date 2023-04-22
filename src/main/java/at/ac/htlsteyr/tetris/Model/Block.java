/*-----------------------------------------------------------------------------
 *              Hoehere Technische Bundeslehranstalt STEYR
 *----------------------------------------------------------------------------*/
/**
 * Kurzbeschreibung
 *
 * @author  : Dino Kupinic, Jannick Angerer
 * @date    : 22.4.2023
 *
 * @details
 * Class used to store each block of a tetromino
 */

package at.ac.htlsteyr.tetris.Model;

import javafx.scene.paint.Color;

public class Block {
    Point blockPoint;
    Color blockColor;

    public Block(Point p, Color c) {
        blockColor = c;
        blockPoint = p;
    }

    public Point getBlockPoint() {
        return blockPoint;
    }

    public void setBlockPoint(Point blockPoint) {
        this.blockPoint = blockPoint;
    }

    public void setBlockColor(Color blockColor) {
        this.blockColor = blockColor;
    }
}
