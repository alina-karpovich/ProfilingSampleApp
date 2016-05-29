package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {
    @FXML private Button goButton;

    @FXML protected void handleGoEvent(ActionEvent event) {
        System.out.println("pressed!");
    }
}
