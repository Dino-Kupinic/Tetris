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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public void initialize() {
        controlsObject = new Controls(
                "D", "A", "M",
                "W", true, "Q", "S"
        );
    }

    public void setDefaultValues() {
        controlsObject.setMoveRight("D");
        controlsObject.setMoveLeft("A");
        controlsObject.setFastDrop("M");
        controlsObject.setSoftdrop("S");
        controlsObject.setRotate("W");
        controlsObject.setHold("Q");
        controlsObject.setMusicCheckbox(true);
    }

    public void updateTextFields() {
        moveRight.setText(controlsObject.getMoveRight());
        moveLeft.setText(controlsObject.getMoveLeft());
        fastDrop.setText(controlsObject.getFastDrop());
        rotate.setText(controlsObject.getRotate());
        hold.setText(controlsObject.getHold());
        softDrop.setText(controlsObject.getSoftdrop());
        musicCheckbox.setSelected(controlsObject.getMusicCheckbox());
    }

    @FXML
    public void onSaveButtonClicked() throws IOException {
        JSONhandler handler = new JSONhandler();
        if (checkValidValues()) {
            controlsObject.setMoveRight(moveRight.getText());
            controlsObject.setMoveLeft(moveLeft.getText());
            controlsObject.setRotate(rotate.getText());
            controlsObject.setHold(hold.getText());
            controlsObject.setFastDrop(fastDrop.getText());
            controlsObject.setSoftdrop(softDrop.getText());
            controlsObject.setMusicCheckbox(musicCheckbox.isSelected());
            handler.writeControlsToJSON(controlsObject);
            //WindowManager.closeWindow();
        } else {
            WindowManager windowManager = new WindowManager();
            windowManager.createNewWindow("Error", "invalidControls.fxml", 160, 200, Modality.APPLICATION_MODAL, "");
        }
    }

    private boolean checkValidValues() {
        Pattern pattern = Pattern.compile("[A-Z]");

        Object obj = controlsObject;
        for (Method method : obj.getClass().getMethods()) {
            if (
                    method.getName().startsWith("get") &&
                    !method.getName().contains("MusicCheckbox") && // Non-string output
                    !method.getName().contains("Class") && // .getClass()
                    method.getParameterTypes().length == 0
            ) {
                try {
                    Object getterObj = method.invoke(obj);
                    String value = getterObj.toString();
                    Matcher matcher = pattern.matcher(value);
                    if (!matcher.matches()) {
                        return false;
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return true;
    }

    @FXML
    public void onDefaultButtonClicked() {
        JSONhandler handler = new JSONhandler();
        setDefaultValues();
        updateTextFields();
        handler.writeControlsToJSON(controlsObject);
    }

    @FXML
    public void onCancelButtonClicked() {
        WindowManager.closeWindow();
    }
}
