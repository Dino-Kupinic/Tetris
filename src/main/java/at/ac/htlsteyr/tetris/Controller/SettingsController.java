package at.ac.htlsteyr.tetris.Controller;

import at.ac.htlsteyr.tetris.Model.Controls;
import at.ac.htlsteyr.tetris.Saves.JSONhandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class SettingsController {
    // FXML Variables
    public TextField moveRight;
    public TextField moveLeft;
    public TextField fastDrop;
    public TextField rotate;
    public TextField hold;
    public TextField softDrop;
    public CheckBox musicCheckbox;
    public Button cancelButton;
    public Button saveButton;

    private Controls controlsObject;

    public void initialize() {
        controlsObject = new Controls(
                "D", "A", "Space",
                "W", true, "Q", "S"
        );
    }

    public void setDefaultValues() {
        controlsObject.setMoveRight("D");
        controlsObject.setMoveLeft("A");
        controlsObject.setFastDrop("Space");
        controlsObject.setSoftdrop("S");
        controlsObject.setRotate("W");
        controlsObject.setHold("Q");
        controlsObject.setMusicCheckbox(true);
    }

    public void onSaveButtonClicked() {
        JSONhandler handler = new JSONhandler();
        handler.writeControlsToJSON(controlsObject);
    }
}
