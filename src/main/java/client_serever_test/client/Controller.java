package client_serever_test.client;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ToggleButton onoffbtn;

    @FXML
    void initialize() {
        boolean a = false;
        onoffbtn.setSelected(a);
        onoffbtn.setOnAction(event -> {
            onoffbtn.setSelected(!a);
            System.out.println("Кнопка нажата ? " + a);
        });

    }
}
