package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import app.Main;
import controllers.util.Cpf;
import entities.Aluno;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class ProfessorController implements Initializable {

    @FXML
    private AnchorPane ancEdit;

    @FXML
    private Button btSave;

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
    private Label lbNome;

    @FXML
    private Label lbInfoAlunos;

    @FXML
    private Label lbMsgError;

    @FXML
    private TextField tfCpf;

    @FXML
    private TextField tfCurso;

    @FXML
    private TextField tfDate;

    @FXML
    private TextField tfMensalidade;

    @FXML
    private TextField tfNome;

    @FXML
    private TableView<Aluno> tbAluno;

    @FXML
    private TableColumn<Aluno, String> tbcData;

    @FXML
    private TableColumn<Aluno, Integer> tbcID;

    @FXML
    private TableColumn<Aluno, String> tbcMateria;

    @FXML
    private TableColumn<Aluno, Double> tbcMensalidade;

    @FXML
    private TableColumn<Aluno, String> tbcNome;

    @FXML
    private TableColumn<Aluno, String> tbcCpf;

    private List<Aluno> alunos;

    private ObservableList<Aluno> items;

    boolean estaEditando = false;

    private Aluno alunoSelecionado;

    @FXML
    void Sair(ActionEvent event) throws IOException {
        Main.setRoot("login");
        Main.professorLogado = null;
    }

    @FXML
    void save(ActionEvent event) {
        // checkar se os campos estão preenchidos
        if (tfCpf.getText().isEmpty() || tfCurso.getText().isEmpty() || tfDate.getText().isEmpty()
                || tfMensalidade.getText().isEmpty() || tfNome.getText().isEmpty()) {
            lbMsgError.setText("Preencha todos os campos");
            // checkar se o cpf é válido
        } else if (!Cpf.isValid(tfCpf.getText())) {
            lbMsgError.setText("CPF inválido");
        } else {

            if (estaEditando) {

                // editar aluno
                Aluno aluno = tbAluno.getSelectionModel().getSelectedItem();
                aluno.setNome(tfNome.getText());
                aluno.setCpf(tfCpf.getText());
                aluno.setMateria(tfCurso.getText());
                aluno.setDataNascimento(tfDate.getText());
                aluno.setMensalidade(Double.parseDouble(tfMensalidade.getText()));

                Main.daoAluno.atualizarAluno(aluno);
            } else {
                // checkar se o aluno já existe
                Aluno aluno = Main.daoAluno.encontrarPeloCPF(tfCpf.getText());
                if (aluno != null) {
                    lbMsgError.setText("Já existe um aluno com esse CPF!");
                } else {
                    // adcionar novo aluno
                    aluno = new Aluno();
                    aluno.setNome(tfNome.getText());
                    aluno.setCpf(tfCpf.getText());
                    aluno.setMateria(tfCurso.getText());
                    aluno.setDataNascimento(tfDate.getText());
                    aluno.setMensalidade(Double.parseDouble(tfMensalidade.getText()));

                    lbMsgError.setText("Aluno adcionado com sucesso!");
                    Main.daoAluno.inserirAluno(aluno);
                }
            }
        }

        // atualizar dados da tabela
        atualizarDadosTabela();

    }

    @FXML
    void adicionar(ActionEvent event) {
        ancEdit.setVisible(true);
        ancEdit.setDisable(false);
        lbInfoAlunos.setText("Adcionar novo aluno");
        btSave.setText("Adcionar");

        estaEditando = false;
    }

    @FXML
    void atualizar(ActionEvent event) {
        ancEdit.setVisible(true);
        ancEdit.setDisable(false);
        lbInfoAlunos.setText("Editar aluno");
        btSave.setText("Editar");

        // atualizar campos
        tfNome.setText(alunoSelecionado.getNome());
        tfCpf.setText(alunoSelecionado.getCpf());
        tfCurso.setText(alunoSelecionado.getMateria());
        tfDate.setText(alunoSelecionado.getDataNascimento());
        tfMensalidade.setText(String.valueOf(alunoSelecionado.getMensalidade()));

        estaEditando = true;
    }

    @FXML
    void remover(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Remover aluno");
        alert.setHeaderText("Remover aluno");
        alert.setContentText("Tem certeza que deseja remover o aluno " + alunoSelecionado.getNome() + "?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            Main.daoAluno.apagarAluno(alunoSelecionado.getId());
            atualizarDadosTabela();
        }

    }

    @FXML
    void voltar(ActionEvent event) {
        ancEdit.setVisible(false);
        ancEdit.setDisable(true);

        // limpar campos
        tfNome.setText("");
        tfCpf.setText("");
        tfCurso.setText("");
        tfDate.setText("");
        tfMensalidade.setText("");
        lbMsgError.setText("");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Carregar dados do professor
        lbNome.setText(Main.professorLogado.getNome());
        lbCpf.setText("CPF: " + Main.professorLogado.getCpf());

        btlAtualizar.setDisable(true);
        btlRemover.setDisable(true);

        // Carregar dados da tabela
        atualizarDadosTabela();

        // aceitar apenas numeros no campo mensalidade e cpf

        tfMensalidade.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                tfMensalidade.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        tfCpf.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                tfCpf.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        // selecionar aluno da tabela
        tbAluno.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                btlAtualizar.setDisable(false);
                btlRemover.setDisable(false);

                alunoSelecionado = newSelection;
            } else {
                btlAtualizar.setDisable(true);
                btlRemover.setDisable(true);
            }
        });
    }

    public void atualizarDadosTabela() {
        alunos = Main.daoAluno.encontrarTodos();

        items = null;
        items = FXCollections.observableArrayList();
        for (Aluno aluno : alunos) {
            items.add(aluno);
        }

        tbcID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tbcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tbcCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tbcData.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        tbcMateria.setCellValueFactory(new PropertyValueFactory<>("materia"));
        tbcMensalidade.setCellValueFactory(new PropertyValueFactory<>("mensalidade"));

        tbAluno.setItems(items);
    }

}
