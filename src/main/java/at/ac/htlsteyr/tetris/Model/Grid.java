package at.ac.htlsteyr.tetris.Model;
import at.ac.htlsteyr.tetris.Controller.MainController;
import javafx.scene.layout.Pane;

public class Grid {
    private Field[][] grid;
    Pane root;

    public Grid (Pane root) {
        this.root = root;
    }

    public void generateGrid () {
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
