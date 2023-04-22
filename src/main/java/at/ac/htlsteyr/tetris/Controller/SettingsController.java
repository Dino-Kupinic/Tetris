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
 * Class responsible for managing interaction with the controls
 */

package at.ac.htlsteyr.tetris.Controller;

import at.ac.htlsteyr.tetris.Model.Controls;
import at.ac.htlsteyr.tetris.Model.WindowManager;
import at.ac.htlsteyr.tetris.Saves.JSONhandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;

import java.io.IOException;

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
    public Button restoreButton;

    private Controls controlsObject;
    private static SettingsController instance;

    public Controls getControlsObject() {
        return controlsObject;
    }

    /**
     * initializes the controls object
     */
    public void initialize() {
        controlsObject = new Controls(
                true, "D", "A", "M",
                "W", "Q", "S"
        );
    }

    /**
     * restores the default values for the controls
     */
    public void setDefaultValues() {
        controlsObject.setMoveRight("D");
        controlsObject.setMoveLeft("A");
        controlsObject.setFastDrop("M");
        controlsObject.setSoftdrop("S");
        controlsObject.setRotate("W");
        controlsObject.setHold("Q");
        controlsObject.setMusicCheckbox(true);
    }

    /**
     * updates the textfields with the current values stored in the controlsObject
     */
    public void updateTextFields() {
        moveRight.setText(controlsObject.getMoveRight());
        moveLeft.setText(controlsObject.getMoveLeft());
        fastDrop.setText(controlsObject.getFastDrop());
        rotate.setText(controlsObject.getRotate());
        hold.setText(controlsObject.getHold());
        softDrop.setText(controlsObject.getSoftdrop());
        musicCheckbox.setSelected(controlsObject.getMusicCheckbox());
    }

    /**
     * Checks if the controls are valid, if so, writes them to the JSON and closes the window.
     * Otherwise, an error window is created.
     * @throws IOException Thrown when something goes wrong with creating the error window
     */
    @FXML
    public void onSaveButtonClicked() throws IOException {
        JSONhandler handler = new JSONhandler();
        updateControlsObject();
        if (controlsObject.checkValidValues(controlsObject)) {
            updateControlsObject();
            handler.writeControlsToJSON(controlsObject);
            WindowManager.closeWindow();
        } else {
            WindowManager windowManager = new WindowManager();
            windowManager.createNewWindow("Error", "invalidControls.fxml", 160, 200, Modality.APPLICATION_MODAL);
        }
    }

    /**
     * updates the controlsObject with the data from the textfields and checkbox
     */
    @FXML
    public void updateControlsObject() {
        controlsObject.setMoveRight(moveRight.getText().toUpperCase());
        controlsObject.setMoveLeft(moveLeft.getText().toUpperCase());
        controlsObject.setFastDrop(fastDrop.getText().toUpperCase());
        controlsObject.setSoftdrop(softDrop.getText().toUpperCase());
        controlsObject.setRotate(rotate.getText().toUpperCase());
        controlsObject.setHold(hold.getText().toUpperCase());
        controlsObject.setMusicCheckbox(musicCheckbox.isSelected());
    }

    /**
     * restores the default values and writes them to the JSON
     */
    @FXML
    public void onDefaultButtonClicked() {
        JSONhandler handler = new JSONhandler();
        setDefaultValues();
        updateTextFields();
        handler.writeControlsToJSON(controlsObject);
    }

    /**
     * closes the window
     */
    @FXML
    public void onCancelButtonClicked() {
        WindowManager.closeWindow();
    }

    public SettingsController() {
        instance = this;
    }

    public static SettingsController getInstance() {
        if (instance == null) {
            instance = new SettingsController();
        }
        return instance;
    }
}
