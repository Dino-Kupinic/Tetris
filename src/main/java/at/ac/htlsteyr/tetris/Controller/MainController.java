package at.ac.htlsteyr.tetris.Controller;

import at.ac.htlsteyr.tetris.Model.Game;
import at.ac.htlsteyr.tetris.Model.Grid;
import at.ac.htlsteyr.tetris.Model.GridSize;
import at.ac.htlsteyr.tetris.Saves.JSONhandler;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class MainController {
    @FXML
    private AnchorPane anchorField;
    private Pane root;
    private Grid grid;
    private static MainController instance;

    public void initialize() {
        // setup Pane
        final int WIDTH = GridSize.width;
        final int HEIGHT = GridSize.height;
        root = new Pane();
        root.setPrefSize(HEIGHT, WIDTH);

        // init Grid
        grid = new Grid(root);
        root = grid.generateGrid();
        anchorField.getChildren().add(root);

        // init Game
        Game game = new Game(grid);
        game.createTetromino();
        startGameLoop();

        // Test JSON
        JSONhandler handler = new JSONhandler();
        //handler.writeToJSON("Dino", 1000);
        System.out.println(handler.getPlayerInfos("Wolfi"));
    }

    public void startGameLoop() {
        AnimationTimer animationTimer = new AnimationTimer() {
            int count = 0;

            @Override
            public void handle(long l) {
               count++;
               if (count == 30) {
                    Tetromino currentTetro = Game.getCurrentTetromino();

               }
            }
        };
        animationTimer.start();
    }

    public MainController() {
        instance = this;
    }

    public static MainController getInstance() {
        if (instance == null) {
            instance = new MainController();
        }
        return instance;
    }
}
