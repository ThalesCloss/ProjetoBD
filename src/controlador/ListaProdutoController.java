/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.sun.xml.internal.bind.v2.runtime.property.PropertyFactory;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import negocio.NegocioException;
import negocio.ProdutoNegocio;
import persistencia.PersistenciaException;
import utilitarios.Alertas;
import vo.ProdutoVO;

/**
 *
 * @author tcloss
 */
public class ListaProdutoController implements Initializable {

    @FXML
    private TableView<ProdutoVO> tbProdutos;
    private ProdutoNegocio pNegocio;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public ListaProdutoController() {
        try {
            pNegocio = new ProdutoNegocio();
        } catch (NegocioException ex) {
            Alertas.exibirAlerta(Alert.AlertType.ERROR, "Erro", ex.getLocalizedMessage(), ex.getCause().getLocalizedMessage());
        }
    }

    public void atualizarTabela() {
        try {
            TableColumn colunaDescricao = (TableColumn) tbProdutos.getColumns().get(0);
            TableColumn colunaMarca = (TableColumn) tbProdutos.getColumns().get(1);
            TableColumn colunaPreco = (TableColumn) tbProdutos.getColumns().get(3);
            TableColumn colunaQtd = (TableColumn) tbProdutos.getColumns().get(2);

            colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
            colunaMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
            colunaPreco.setCellValueFactory(new PropertyValueFactory<>("vlrUnitarioVenda"));
            colunaQtd.setCellValueFactory(new PropertyValueFactory<>("qtdEstoque"));

            tbProdutos.setItems(FXCollections.observableArrayList(pNegocio.todos()));

        } catch (NegocioException ex) {
            Alertas.exibirAlerta(Alert.AlertType.ERROR, "Erro", ex.getLocalizedMessage(), ex.getCause().getLocalizedMessage());
        }
    }

    @FXML
    public void btNovoOnAction(ActionEvent e) {
        PrincipalController.abrirCadastroProduto();
    }

    public void btEditarOnAction(ActionEvent e) {

        if (tbProdutos.getSelectionModel().getSelectedIndex() != -1) {
            PrincipalController.cadastroProdutoCtr.setProdutoVO(tbProdutos.getSelectionModel().getSelectedItem());
            PrincipalController.abrirCadastroProduto();
        } else {
            Alertas.exibirAlerta(Alert.AlertType.ERROR, "Editar", "Nenhum produto selecionado", "Selecione um produto na tabela");
        }
    }

    public void btExcluirOnAction(ActionEvent e) {
        if (tbProdutos.getSelectionModel().getSelectedIndex() != -1) {
            try{
                pNegocio.excluirProduto(tbProdutos.getSelectionModel().getSelectedItem());
            }
            catch(NegocioException ex){
                Alertas.exibirAlerta(Alert.AlertType.ERROR,"Excluir", ex.getLocalizedMessage() , ex.getCause().getLocalizedMessage());
            }
        } else {
            Alertas.exibirAlerta(Alert.AlertType.ERROR, "Excluir", "Nenhum produto selecionado", "Selecione um produto na tabela para excluir");
        }
    }

}
