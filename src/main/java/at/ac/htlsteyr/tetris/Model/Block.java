package at.ac.htlsteyr.tetris.Model;

import javafx.scene.paint.Color;

public class Block {
    Point blockPoints;

    Color blockColor;

    int x,y;

    public Block(Point p, Color c) {
        blockColor = c;
        blockPoints = p;
    }

    public void initCoords () {
        y = GridSize.rows;
        x = GridSize.columns;
    }

    public Point getBlockcoords() {
        return blockPoints;
    }

    public Color getBlockColor() {
        return blockColor;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


}
