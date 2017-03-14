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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import negocio.NegocioException;
import negocio.ProdutoNegocio;
import persistencia.PersistenciaException;
import vo.ProdutoVO;

/**
 *
 * @author tcloss
 */
public class ListaProdutoController implements Initializable{
    @FXML
    private TableView<ProdutoVO> tbProdutos;
    private ProdutoNegocio pNegocio;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            pNegocio=new ProdutoNegocio();
             TableColumn colunaDescricao = (TableColumn) tbProdutos.getColumns().get(0);
            TableColumn colunaMarca = (TableColumn) tbProdutos.getColumns().get(1);
            TableColumn colunaPreco = (TableColumn) tbProdutos.getColumns().get(2);
            //TableColumn colunaQtd= (TableColumn) colunaPreco.getColumns().get(0);
            colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
            colunaMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
            colunaPreco.setCellFactory(new PropertyValueFactory<>("vlrUnitarioVenda"));
            //colunaQtd.setCellFactory(new PropertyValueFactory<>("qtdEstoque"));
            tbProdutos.setItems(FXCollections.observableArrayList(pNegocio.todos()));
        } catch (NegocioException ex) {
            Logger.getLogger(ListaProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PersistenciaException ex) {
            Logger.getLogger(ListaProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }
    
    
}
