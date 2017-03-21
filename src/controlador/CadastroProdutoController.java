/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.collections.MapChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import utilitarios.Alertas;
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
    
    public void btGravarOnAction(ActionEvent e){
        try {
            NumberFormat nf = NumberFormat.getCurrencyInstance();
            if(produtoVO==null)
                produtoVO=new ProdutoVO();
            produtoVO.setDescricao(tfDescricao.getText());
            produtoVO.setMarca(tfMarca.getText());
            produtoVO.setQtdEstoque(Integer.parseInt(tfQtd.getText()));
            produtoVO.setVlrUnitarioCompra(nf.parse(tfVlrCompra.getText()).doubleValue());
            produtoVO.setVlrUnitarioVenda(nf.parse(tfVlrVenda.getText()).doubleValue());
        } catch (ParseException ex) {
            System.err.println(ex);
            Alertas.exibirAlerta(Alert.AlertType.ERROR, "Valores", "Valores incorretos", "Confira os valores de venda e de compra "+ex.getMessage());
        }
        catch(NumberFormatException ex){
            Alertas.exibirAlerta(Alert.AlertType.ERROR, "Quantidade", "Valores incorretos", "Confira o valor da quantidade");
        }
        
    }
}
