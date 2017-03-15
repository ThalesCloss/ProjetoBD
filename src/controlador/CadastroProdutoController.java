/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import vo.ProdutoVO;

/**
 *
 * @author CTI
 */
public class CadastroProdutoController implements Initializable {
    @FXML
    private TextField tfDescricao;
    @FXML
    private TextField tfMarca;
    @FXML
    private TextField tfQtd;
    @FXML
    private TextField tfVlrVenda;
    @FXML
    private TextField tfVlrCompra;
    private ProdutoVO produtoVO;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    public void setProdutoVO(ProdutoVO produtoVO){
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        tfDescricao.setText(produtoVO.getDescricao());
        tfMarca.setText(produtoVO.getMarca());
        tfQtd.setText(Integer.toString(produtoVO.getQtdEstoque()));
        tfVlrCompra.setText(nf.format(produtoVO.getVlrUnitarioCompra()));
        tfVlrVenda.setText(nf.format(produtoVO.getVlrUnitarioVenda()));
        this.produtoVO=produtoVO;
    }
}
