package controllers;

import java.io.IOException;

import app.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
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
    private Label lbCpf;

    @FXML
    private Label lbMateria;

    @FXML
    private Label lbNome;
    
    @FXML
    private Label lbInfoAlunos;

    @FXML
    private TextField tfCpf;

    @FXML
    private TextField tfCurso;

    @FXML
    private TextField tfDate;

    @FXML
    private TextField tfMensalidade;

    @FXML
    private TableColumn<?, ?> tbcData;

    @FXML
    private TableColumn<?, ?> tbcID;
    
    @FXML
    private TableColumn<?, ?> tbcMensalidade;

    @FXML
    private TableColumn<?, ?> tbcNome;

    @FXML
    private TableColumn<?, ?> tbcSalario;

    @FXML
    void Sair(ActionEvent event) throws IOException {
        Main.setRoot("login");
    }

    @FXML
    void adicionar(ActionEvent event) {
        ancEdit.setVisible(true);
        ancEdit.setDisable(false);
        lbInfoAlunos.setText("Adcionar novo aluno");
        btAlterar.setText("Adcionar");
    }

    @FXML
    void alterar(ActionEvent event) {
        ancEdit.setVisible(true);
        ancEdit.setDisable(false);
        lbInfoAlunos.setText("Editar aluno");
        btAlterar.setText("Editar");
        
    }

    @FXML
    void atualizar(ActionEvent event) {

    }

    @FXML
    void remover(ActionEvent event) {

    }

    @FXML
    void voltar(ActionEvent event) {
        ancEdit.setVisible(false);
        ancEdit.setDisable(true);
    }

}
