/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import negocio.NegocioException;
import negocio.UsuarioNegocio;
import utilitarios.Alertas;
import vo.UsuarioVO;

/**
 *
 * @author tcloss
 */
public class ListaUsuarioController implements Initializable {

    @FXML
    private TableView<UsuarioVO> tbUsuarios;
    
    @FXML
    private Button btEditar, btExcluir;

    private UsuarioNegocio uNegocio;
    
    private Parent cadastro;
    private Pane painelBase;

    public Pane getPainelBase() {
        return painelBase;
    }

    public void setPainelBase(Pane painelBase) {
        this.painelBase = painelBase;
    }
    
    public Parent getCadastro() {
        return cadastro;
    }

    public void setCadastro(Parent cadastro) {
        this.cadastro = cadastro;
    }

    public ListaUsuarioController() {
        try {
            uNegocio = new UsuarioNegocio();
        } catch (NegocioException ex) {
            Alertas.exibirAlerta(Alert.AlertType.ERROR, "Erro", "Erro ao iniciar a persistencia", ex.getLocalizedMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            TableColumn colunaLogin = (TableColumn) tbUsuarios.getColumns().get(0);
            TableColumn colunaNome = (TableColumn) tbUsuarios.getColumns().get(1);
            colunaLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
            colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
            tbUsuarios.setItems(FXCollections.observableArrayList(uNegocio.todosUsuarios()));

        } catch (NegocioException ex) {
            Alertas.exibirAlerta(Alert.AlertType.ERROR, "Erro", ex.getLocalizedMessage(), ex.getCause().getLocalizedMessage());
        }
    }

    @FXML
    public void btEditarOnAction(ActionEvent ev) {

    }
    
    @FXML 
    public void btNovoOnAction(ActionEvent e){
        painelBase.getChildren().clear();
        painelBase.getChildren().add(cadastro);
    }

    @FXML
    public void btExcluirOnAction(ActionEvent e) {
        try {
            if (Alertas.exibirAlerta(Alert.AlertType.CONFIRMATION, "Confirmar exclusão?", "Confirmar Exclusão?", "O cadastro será excluído e não pode ser recuperado, deseja continuar?").get() == ButtonType.OK) {
                uNegocio.deletarUsuario(tbUsuarios.getSelectionModel().getSelectedItem());
                tbUsuarios.getItems().remove(tbUsuarios.getSelectionModel().getSelectedItem());
            }
        } catch (NegocioException ex) {
            Alertas.exibirAlerta(Alert.AlertType.ERROR, "Erro", ex.getLocalizedMessage(), ex.getCause().getLocalizedMessage());
        }
    }

}
