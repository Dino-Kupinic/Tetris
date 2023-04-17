package at.ac.htlsteyr.tetris.Controller;

import at.ac.htlsteyr.tetris.Model.*;
import at.ac.htlsteyr.tetris.Saves.JSONhandler;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;

import java.io.IOException;

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
    private WindowManager windowManager;

    public void initialize() throws IOException {
        windowManager = new WindowManager();

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
        handler.checkIfJSONisValid();
    }

    @FXML
    public void onStartClicked() {
        // init Game
        Gamemode gamemode = Gamemode.valueOf(modeChoiceBox.getValue());
        Game game = new Game(grid, gamemode);
        game.createTetromino();
        game.startGameLoop();
    }

    @FXML
    public void onSettingsClicked() throws IOException {
        windowManager.createNewWindow("Settings", "settings-view.fxml", 400, 300, Modality.APPLICATION_MODAL,"styles-settings.css");
    }

    @FXML
    public void onHelpClicked() throws IOException {
        windowManager.createNewWindow("Help", "help-view.fxml", 300, 600, Modality.APPLICATION_MODAL,"styles-help.css");
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
