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
 * main class
 */

package at.ac.htlsteyr.tetris;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainApplication extends Application {
    private static MainApplication instance;
    Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        final int WINDOW_WIDTH = 960;
        final int WINDOW_HEIGHT = 720;

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        scene = new Scene(fxmlLoader.load(), WINDOW_WIDTH, WINDOW_HEIGHT);

        scene.getStylesheets().addAll(
                Objects.requireNonNull(Objects.requireNonNull(getClass().getResource("styles/styles.css")).toExternalForm())
        );

        stage.setTitle("Tetris");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public MainApplication() {
        instance = this;
    }

    public static MainApplication getInstance() {
        if (instance == null) {
            instance = new MainApplication();
        }
        return instance;
    }

    public Scene getScene() {
        return scene;
    }
}