package at.ac.htlsteyr.tetris;

import at.ac.htlsteyr.tetris.Saves.JSONhandler;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        final int WINDOW_WIDTH = 960;
        final int WINDOW_HEIGHT = 720;

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setTitle("Tetris");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        JSONhandler jsoNhandler = new JSONhandler();
        jsoNhandler.writeToJSON("Samc", 33);
    }

    public static void main(String[] args) {
        launch();
    }
}