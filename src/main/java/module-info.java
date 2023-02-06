module at.ac.htlsteyr.tetris {
    requires javafx.controls;
    requires javafx.fxml;


    opens at.ac.htlsteyr.tetris to javafx.fxml;
    exports at.ac.htlsteyr.tetris;
    exports at.ac.htlsteyr.tetris.Controller;
    opens at.ac.htlsteyr.tetris.Controller to javafx.fxml;
}