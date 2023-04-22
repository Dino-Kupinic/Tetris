module at.ac.htlsteyr.tetris {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens at.ac.htlsteyr.tetris.Model to com.google.gson;
    opens at.ac.htlsteyr.tetris to javafx.fxml;
    exports at.ac.htlsteyr.tetris;
    exports at.ac.htlsteyr.tetris.Controller;
    exports at.ac.htlsteyr.tetris.Model;
    opens at.ac.htlsteyr.tetris.Controller to javafx.fxml;
}