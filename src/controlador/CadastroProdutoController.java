/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Currency;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.MapChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import negocio.NegocioException;
import negocio.ProdutoNegocio;
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
    private ProdutoNegocio nProduto;
    private NumberFormat nf;
    
    public CadastroProdutoController() {
        nf = NumberFormat.getCurrencyInstance();
        try {
            nProduto = new ProdutoNegocio();
        } catch (NegocioException ex) {
            Alertas.exibirAlerta(Alert.AlertType.ERROR, "Erro", ex.getMessage(), ex.getCause().getMessage());
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tfDescricao.requestFocus();
    }
    
    public void onMoneyPressKey(KeyEvent k) {
        
        TextField source = (TextField) k.getSource();
        if (!source.getText().startsWith(nf.getCurrency().getSymbol() + " ")) {
            source.setText(nf.getCurrency().getSymbol() + " " + source.getText());
            source.positionCaret(source.getLength());
        }
        
    }
    
    public void setProdutoVO(ProdutoVO produtoVO) {
        
        tfDescricao.setText(produtoVO.getDescricao());
        tfMarca.setText(produtoVO.getMarca());
        tfQtd.setText(Integer.toString(produtoVO.getQtdEstoque()));
        tfVlrCompra.setText(nf.format(produtoVO.getVlrUnitarioCompra()));
        tfVlrVenda.setText(nf.format(produtoVO.getVlrUnitarioVenda()));
        this.produtoVO = produtoVO;
    }
    
    public void btGravarOnAction(ActionEvent e) {
        try {
            
            if (produtoVO == null) {
                produtoVO = new ProdutoVO();
            }
            produtoVO.setDescricao(tfDescricao.getText());
            produtoVO.setMarca(tfMarca.getText());
            produtoVO.setQtdEstoque(Integer.parseInt(tfQtd.getText()));
            produtoVO.setVlrUnitarioCompra(nf.parse(tfVlrCompra.getText()).doubleValue());
            produtoVO.setVlrUnitarioVenda(nf.parse(tfVlrVenda.getText()).doubleValue());
            if (produtoVO.getIdProduto() == 0) {
                nProduto.inserirProduto(produtoVO);
            } else {
                nProduto.atualizarProduto(produtoVO);
            }
            if(Alertas.exibirAlerta(Alert.AlertType.ERROR, "Gravar", "Produto inserido com sucesso", "Deseja inserir outro produto?", Alertas.SIM_NAO_BOTOES).get()==ButtonType.YES)
            {   
                produtoVO=null;
                PrincipalController.limparTextField();
            }
            else{
                PrincipalController.abrirListaProduto();
            }
        } catch (ParseException ex) {
            System.err.println(ex);
            Alertas.exibirAlerta(Alert.AlertType.ERROR, "Valores", "Valores incorretos", "Confira os valores de venda e de compra " + ex.getMessage());
        } catch (NumberFormatException ex) {
            Alertas.exibirAlerta(Alert.AlertType.ERROR, "Quantidade", "Valores incorretos", "Confira o valor da quantidade");
        } catch (NegocioException ex) {
            Alertas.exibirAlerta(Alert.AlertType.ERROR, "Erro", ex.getMessage(), ex.getCause().getMessage());
        }
        
    }

    public void btCancelarOnAction(ActionEvent e) {
        PrincipalController.limparTextField();
    }
}
