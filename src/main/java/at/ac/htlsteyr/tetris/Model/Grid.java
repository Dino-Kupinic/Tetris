/*-----------------------------------------------------------------------------
 *              Hoehere Technische Bundeslehranstalt STEYR
 *----------------------------------------------------------------------------*/
/**
 * Kurzbeschreibung
 *
 * @author  : Jannick Angerer
 * @date    : 22.4.2023
 *
 * @details
 * Class responsible for displaying the game grid
 */

package at.ac.htlsteyr.tetris.Model;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Grid extends GridPane {
    private Field[][] grid;
    private Pane root;

    public Grid(Pane root) {
        this.root = root;
    }

    /**
     * generates a 10x20 grid
     * @return the pane with the fields
     */
    public Pane generateGrid() {
        int cols = GridSize.columns;
        int rows = GridSize.rows;
        grid = new Field[cols][rows];

        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                Field field = new Field(x, y, false);
                grid[x][y] = field;
                root.getChildren().add(field);
            }
        }
        return root;
    }

    public Field[][] getGrid() {
        return grid;
    }

    public void setGrid(Field[][] grid) {
        this.grid = grid;
    }
}
