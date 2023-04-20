package at.ac.htlsteyr.tetris.Controller;

import at.ac.htlsteyr.tetris.Saves.JSONhandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class HighscoreController {
    public Button continueButton;
    public TextField playerNameInput;

    @FXML
    public void onContinueButtonClicked() throws IOException {
        JSONhandler handler = new JSONhandler();
        handler.checkIfSaveJSONisValid();
        handler.writePlayerToSaveJSON(playerNameInput.getText(), Integer.parseInt(MainController.getInstance().score.getText()));
    }
}
