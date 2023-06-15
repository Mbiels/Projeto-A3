package controllers;

import java.io.IOException;

import app.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SignInController {

    @FXML
    private Button btDeslogar;

    @FXML
    private Button btLogin;

    @FXML
    private TextField tfLogin;

    @FXML
    private TextField tflPassword;

    @FXML
    void Sair(ActionEvent event) throws IOException {
        Main.setRoot("login");
    }

    @FXML
    void login(ActionEvent event) throws IOException {
        Main.setRoot("professor");
    }

}

