package controllers;

import java.io.IOException;

import app.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private Button btLogin;

    @FXML
    private Button btsingIn;

    @FXML
    private TextField tfLogin;

    @FXML
    void login(ActionEvent event) throws IOException {
        Main.setRoot("professor");
    }

    @FXML
    void singIn(ActionEvent event) throws IOException {
       Main.setRoot("singIn");
    }

}
