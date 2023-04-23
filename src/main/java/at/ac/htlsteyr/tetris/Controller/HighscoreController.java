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
 * Class responsible for interaction with the save window after the game has ended
 */

package at.ac.htlsteyr.tetris.Controller;

import at.ac.htlsteyr.tetris.Model.WindowManager;
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
        WindowManager.closeWindow();
    }
}
