package at.ac.htlsteyr.tetris.Controller;

import at.ac.htlsteyr.tetris.Model.*;
import at.ac.htlsteyr.tetris.Saves.JSONhandler;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class MainController {
    // FXML Variables
    public ChoiceBox<String> modeChoiceBox;
    public AnchorPane anchorField;
    public Label timerLabel;
    public ImageView modeLabel;
    public ImageView scoreLabel;
    public ImageView helpButton;
    public ImageView settingsButton;
    public ImageView startButton;

    private Pane root;
    private Grid grid;
    private static MainController instance;
    private static Timer timer;

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

        addDifficultyOptions();

        // Test JSON
        JSONhandler handler = new JSONhandler();
        //handler.writeToJSON("Dino", 1000);
        System.out.println(handler.getPlayerInfos("Wolfi"));
    }

    @FXML
    public void onStartClicked() {
        // init Game
        Game game = new Game(grid);
        game.createTetromino();
        game.startGameLoop();
    }

    public void setTimerLabel(String time) {
        timerLabel.setText(time);
    }

    private void addDifficultyOptions() {
        modeChoiceBox.getItems().addAll(
                String.valueOf(Gamemode.NORMAL),
                String.valueOf(Gamemode.TIMED),
                String.valueOf(Gamemode.FREEPLAY)
        );
        modeChoiceBox.setValue(String.valueOf(Gamemode.NORMAL));
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
