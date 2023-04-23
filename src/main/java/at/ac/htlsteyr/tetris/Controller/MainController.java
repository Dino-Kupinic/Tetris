/*-----------------------------------------------------------------------------
 *              Hoehere Technische Bundeslehranstalt STEYR
 *----------------------------------------------------------------------------*/
/**
 * Kurzbeschreibung
 *
 * @author  : Dino Kupinic
 * @date    : 22.4.2023
 *
 * @details
 * Class responsible for managing interaction with the game and user interface
 */

package at.ac.htlsteyr.tetris.Controller;

import at.ac.htlsteyr.tetris.Model.*;
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
    public Label score;
    public Label timerHeader;

    private Pane root;
    private Grid grid;
    private static MainController instance;
    private static Timer timer;
    private WindowManager windowManager;

    /**
     * initializes the game grid and controls
     */
    public void initialize() {
        // initialize settings so controls can be used without having to open the menu
        SettingsController.getInstance().initialize();

        // init window manager
        windowManager = new WindowManager();

        // setup Pane
        final int WIDTH = GridSize.width;
        final int HEIGHT = GridSize.height;
        root = new Pane();
        root.setPrefSize(HEIGHT, WIDTH);
        addDifficultyOptions();

        // init Grid
        grid = new Grid(root);
        root = grid.generateGrid();
        anchorField.getChildren().add(root);
    }

    /**
     * starts the game
     */
    @FXML
    public void onStartClicked() {
        // init Game
        Gamemode gamemode = Gamemode.valueOf(modeChoiceBox.getValue());
        if (!startButton.isDisabled()) {
            Game game = new Game(grid, gamemode);
            game.createTetromino();
            game.startGameLoop();
        }

        startButton.setDisable(true);
    }

    /**
     * opens the settings window
     * @throws IOException Thrown when something goes wrong with creating the window
     */
    @FXML
    public void onSettingsClicked() throws IOException {
        windowManager.createNewWindow("Settings", "settings-view.fxml", 400, 300, Modality.APPLICATION_MODAL,"styles-settings.css");
    }

    /**
     * opens the help window
     * @throws IOException Thrown when something goes wrong with creating the window
     */
    @FXML
    public void onHelpClicked() throws IOException {
        windowManager.createNewWindow("Help", "help-view.fxml", 300, 600, Modality.APPLICATION_MODAL,"styles-help.css");
    }

    /**
     * Sets the displayed time to a value
     * @param time the time value
     */
    public void setTimerLabel(String time) {
        timerLabel.setText(time);
    }

    /**
     * adds the difficulties to the choicebox
     */
    private void addDifficultyOptions() {
        modeChoiceBox.getItems().addAll(
                String.valueOf(Gamemode.NORMAL),
                String.valueOf(Gamemode.TIMED)
        );
        modeChoiceBox.setValue(String.valueOf(Gamemode.NORMAL));
    }

    /**
     * makes the timer header label visible
     */
    public void setTimerLabelVisible() {
        timerHeader.setVisible(true);
    }

    /**
     * makes the timer header label invisible
     */
    public void setTimerLabelInvisible() {
        timerHeader.setVisible(false);
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
