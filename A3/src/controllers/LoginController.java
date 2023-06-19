package controllers;

import java.io.IOException;

import app.Main;
import entities.Professor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private Button btLogin;

    @FXML
    private Button btsingIn;

    @FXML
    private Label lbMensagemErro;

    @FXML
    private TextField tfLogin;

    @FXML
    private TextField tfCPF;

    @FXML
    void login(ActionEvent event) throws IOException {
        if(checkarCampos()){
            Main.setRoot("professor");
        }
    }

    @FXML
    void singIn(ActionEvent event) throws IOException {
        Main.setRoot("singIn");
    }


    public boolean checkarCampos() {
        if (tfLogin.getText().isEmpty() || tfCPF.getText().isEmpty()) {
            lbMensagemErro.setText("Preencha todos os campos!");
            return false;
        }else{
           Professor professor = Main.daoProfessor.encontrarPeloNomeECpf(tfLogin.getText(), tfCPF.getText());
           if(professor == null){
               lbMensagemErro.setText("Professor não encontrado!");
               return false;
           }
            Main.professorLogado = professor;
        }
        return true;
    }
}
