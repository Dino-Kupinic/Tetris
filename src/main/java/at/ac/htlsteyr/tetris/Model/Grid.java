package at.ac.htlsteyr.tetris.Model;

import javafx.scene.layout.Pane;

public class Grid {
    private Field[][] grid;
    Pane root;

    public Grid (Pane root) {
        this.root = root;
    }

    public void generateGrid () {
        root.setPrefSize(GridSize.width, GridSize.height);
        int cols = GridSize.columns;
        int rows = GridSize.rows;
        grid = new Field[cols][rows];

        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                Field field = new Field(cols, rows);
                grid[x][y] = field;
                root.getChildren().add(field);
            }
        }
    }
}
