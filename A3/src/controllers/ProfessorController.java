package controllers;

import java.io.IOException;

import app.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ProfessorController {

    @FXML
    private AnchorPane ancEdit;

    @FXML
    private Button btAlterar;

    @FXML
    private Button btDeslogar;

    @FXML
    private Button btVoltar;

    @FXML
    private Button btlAdicionar;

    @FXML
    private Button btlAtualizar;

    @FXML
    private Button btlRemover;

    @FXML
    private TextField tfCpf;

    @FXML
    private TextField tfCurso;

    @FXML
    private TextField tfDate;

    @FXML
    private TextField tfMensalidade;

    @FXML
    void Sair(ActionEvent event) throws IOException {
       Main.setRoot("login");
    }

    @FXML
    void adicionar(ActionEvent event) {
        ancEdit.setVisible(true);
        ancEdit.setDisable(false);
    }

    @FXML
    void alterar(ActionEvent event) {

    }

    @FXML
    void atualizar(ActionEvent event) {

    }

    @FXML
    void remover(ActionEvent event) {

    }

    @FXML
    void voltar(ActionEvent event) throws IOException {
        ancEdit.setVisible(false);
        ancEdit.setDisable(true);
    }

}
