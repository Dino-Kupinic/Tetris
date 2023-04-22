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
 * Class responsible for interaction with the error window after the user tries to save with invalid controls
 */

package at.ac.htlsteyr.tetris.Controller;

import at.ac.htlsteyr.tetris.Model.WindowManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class InvalidControlsController {
    public Button okButton;

    /**
     * closes the window
     */
    @FXML
    public void onOkButtonClicked() {
        WindowManager.closeWindow();
    }
}
