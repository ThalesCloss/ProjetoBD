/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import negocio.FornecedorNegocio;
import negocio.NegocioException;
import utilitarios.Alertas;
import vo.FornecedorVO;

/**
 *
 * @author tcloss
 */
public class ListaFornecedorController implements Initializable{
    @FXML
    private TableView<FornecedorVO> tbFornecedores;
    
    private FornecedorNegocio nFornecedor;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tbFornecedores.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("nomeFantasia"));
        tbFornecedores.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("telefone"));
    }

    public ListaFornecedorController() {
        try{
            nFornecedor=new FornecedorNegocio();
        }
        catch(NegocioException e){
            Alertas.exibirAlerta(Alert.AlertType.ERROR, "Erro", e.getMessage(), e.getCause().getMessage());
        }
    }
    
    public void atualizarTabela(){
        try {
            tbFornecedores.setItems(FXCollections.observableArrayList(nFornecedor.todos()));
        } catch (NegocioException ex) {
            Alertas.exibirAlerta(Alert.AlertType.ERROR, "Erro", ex.getMessage(), ex.getCause().getMessage());
        }
    }
    
    public void btEditarOnAction(ActionEvent e){
        if(tbFornecedores.getSelectionModel().getSelectedIndex()!=-1)
        {
            PrincipalController.cadastroFornecedorCtr.setFornecedorVO(tbFornecedores.getSelectionModel().getSelectedItem());
            PrincipalController.abrirCadastroFornecedor();
        }
        else{
            Alertas.exibirAlerta(Alert.AlertType.INFORMATION, "Editar", "Nenhum produto selecionado", "Selecione um produto para editar");
        }
    }
    public void btExcluirOnAction(ActionEvent e){
        if(tbFornecedores.getSelectionModel().getSelectedIndex()!=-1)
        {
            if(Alertas.exibirAlerta(Alert.AlertType.CONFIRMATION, "Excluir", "Confirmar exclusão?", "Deseja relamente excluir o fornecedor?", Alertas.SIM_NAO_BOTOES).get()==ButtonType.YES)
            {
                try {
                    nFornecedor.deletarFornecedor(tbFornecedores.getSelectionModel().getSelectedItem());
                    tbFornecedores.getItems().remove(tbFornecedores.getSelectionModel().getSelectedItem());
                } catch (NegocioException ex) {
                    Alertas.exibirAlerta(Alert.AlertType.ERROR, "Erro", ex.getMessage(), ex.getCause().getMessage());
                }
                
            }
        }
        else{
            Alertas.exibirAlerta(Alert.AlertType.INFORMATION, "Editar", "Nenhum produto selecionado", "Selecione um produto para excluir");
        }
    }
    public void btNovoOnAction(ActionEvent e){
        PrincipalController.abrirCadastroFornecedor();
    }
    
}
