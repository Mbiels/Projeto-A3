package controllers;

import java.io.IOException;

import app.Main;
import controllers.util.Cpf;
import entities.Professor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SignInController {

    @FXML
    private Button btDeslogar;

    @FXML
    private Button btLogin;

    @FXML
    private Label lbMensagemErro;

    @FXML
    private TextField tfCpf;

    @FXML
    private TextField tfData;

    @FXML
    private TextField tfLogin;

    @FXML
    void Sair(ActionEvent event) throws IOException {
        Main.setRoot("login");
    }

    @FXML
    void login(ActionEvent event) throws IOException {
        if(checkarCampos()){
            cadastrarProfessor();
            Main.setRoot("professor");
        } 
    }

    public Boolean checkarCampos(){
        if(tfLogin.getText().isEmpty() || tfCpf.getText().isEmpty() || tfData.getText().isEmpty()){
            lbMensagemErro.setText("Preencha todos os campos!");
            return false;
        }else if(!Cpf.isValid(tfCpf.getText())){
            lbMensagemErro.setText("CPF inválido!");
            return false;
        }else if(Main.daoProfessor.cpfJaExiste(tfCpf.getText())){
            lbMensagemErro.setText("Já existe um professor com este CPF!");
            return false;
        }
        return true;
    }


    public void cadastrarProfessor(){
        Main.professorLogado = Main.daoProfessor.inserirProfessor(new Professor(tfCpf.getText(), tfLogin.getText(), tfData.getText()));
    }
}


