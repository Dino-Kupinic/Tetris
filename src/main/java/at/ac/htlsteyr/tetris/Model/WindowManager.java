package at.ac.htlsteyr.tetris.Model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class WindowManager {
    private static Stage stage;

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

    public static void closeWindow() {
        stage.close();
    }

    private record Result(Scene scene, Stage stage) {}
}
