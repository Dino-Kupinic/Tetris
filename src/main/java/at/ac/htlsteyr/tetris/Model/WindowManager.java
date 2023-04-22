/*-----------------------------------------------------------------------------
 *              Hoehere Technische Bundeslehranstalt STEYR
 *----------------------------------------------------------------------------*/
/**
 * Kurzbeschreibung
 *
 * @author : Dino Kupinic
 * @date : 22.4.2023
 *
 * @details
 * Class to easily create and close windows
 */

package at.ac.htlsteyr.tetris.Model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class WindowManager {
    private static Stage stage;

    /**
     * creates a new window
     * @param title the title of the window
     * @param FXMLfile the fxml file which will be loaded
     * @param width width of the window
     * @param height height of the window
     * @param stylesheet stylesheet for the fxml
     * @throws IOException thrown when something goes wrong when creating the window
     */
    public void createNewWindow(
            String title,
            String FXMLfile,
            int width,
            int height,
            String stylesheet
    ) throws IOException {
        Result result = getResult(title, FXMLfile, width, height, stylesheet);
        result.stage().setScene(result.scene());
        result.stage().show();
    }

    /**
     * creates a new window
     * @param title the title of the window
     * @param FXMLfile the fxml file which will be loaded
     * @param width width of the window
     * @param height height of the window
     * @param modality modality of the window
     * @throws IOException thrown when something goes wrong when creating the window
     */
    public void createNewWindow(
            String title,
            String FXMLfile,
            int width,
            int height,
            Modality modality
    ) throws IOException {
        Result result = getResult(title, FXMLfile, width, height);
        result.stage().initModality(modality);
        result.stage().setScene(result.scene());
        result.stage().show();
    }

    /**
     * creates a new window
     * @param title the title of the window
     * @param FXMLfile the fxml file which will be loaded
     * @param width width of the window
     * @param height height of the window
     * @param modality modality of the window
     * @param stylesheet stylesheet for the fxml
     * @throws IOException thrown when something goes wrong when creating the window
     */
    public void createNewWindow(
            String title,
            String FXMLfile,
            int width,
            int height,
            Modality modality,
            String stylesheet
    ) throws IOException {
        Result result = getResult(title, FXMLfile, width, height, stylesheet);
        result.stage().initModality(modality);
        result.stage().setScene(result.scene());
        result.stage().show();
    }

    /**
     * creates a stage, scene also FXMLLoader. Also sets properties like title and if the window is resizable
     * @param title the title of the window
     * @param FXMLfile the fxml file which will be loaded
     * @param width width of the window
     * @param height height of the window
     * @param stylesheet stylesheet for the fxml
     * @return record containing the scene and stage
     * @throws IOException thrown when something goes wrong when creating the window
     */
    private Result getResult(
            String title,
            String FXMLfile,
            int width,
            int height,
            String stylesheet
    ) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WindowManager.class.getResource(FXMLfile));
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        scene.getStylesheets().addAll(
                Objects.requireNonNull(Objects.requireNonNull(WindowManager.class.getResource("styles/" + stylesheet)).toExternalForm())
        );
        stage = new Stage();
        stage.setTitle(title);
        stage.setResizable(false);
        return new Result(scene, stage);
    }

    /**
     * creates a stage, scene also FXMLLoader. Also sets properties like title and if the window is resizable
     * @param title the title of the window
     * @param FXMLfile the fxml file which will be loaded
     * @param width width of the window
     * @param height height of the window
     * @return record containing the scene and stage
     * @throws IOException thrown when something goes wrong when creating the window
     */
    private Result getResult(
            String title,
            String FXMLfile,
            int width,
            int height
    ) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WindowManager.class.getResource(FXMLfile));
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        stage = new Stage();
        stage.setTitle(title);
        stage.setResizable(false);
        return new Result(scene, stage);
    }

    /**
     * closes the current window
     */
    public static void closeWindow() {
        stage.close();
    }

    private record Result(Scene scene, Stage stage) {}
}
