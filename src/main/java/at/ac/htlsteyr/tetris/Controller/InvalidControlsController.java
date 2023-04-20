package at.ac.htlsteyr.tetris.Controller;

import at.ac.htlsteyr.tetris.Model.WindowManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class InvalidControlsController {
    public Button okButton;

    @FXML
    public void onOkButtonClicked() {
        WindowManager.closeWindow();
    }
}
