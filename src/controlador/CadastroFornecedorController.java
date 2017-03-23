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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import negocio.FornecedorNegocio;
import negocio.NegocioException;
import utilitarios.Alertas;
import vo.Estados;
import vo.FornecedorVO;

/**
 *
 * @author tcloss
 */
public class CadastroFornecedorController implements Initializable {

    @FXML
    private TextField tfNomeFantasia;
    @FXML
    private TextField tfCnpj;
    @FXML
    private TextField tfEndereco;
    @FXML
    private TextField tfCidade;
    @FXML
    private TextField tfEmail;
    @FXML
    private ComboBox<Estados> cbUf;
    @FXML
    private TextField tfTelefone;

    private FornecedorVO fornecedorVO;
    private FornecedorNegocio fNegocio;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbUf.setItems(FXCollections.observableArrayList(Estados.values()));
        cbUf.getSelectionModel().select(-1);
    }

    public CadastroFornecedorController() {
        try {
            fNegocio = new FornecedorNegocio();
        } catch (NegocioException ex) {
            Alertas.exibirAlerta(Alert.AlertType.ERROR, "Erro", ex.getMessage(), ex.getCause().getMessage());
        }
    }

    public void btCancelarOnAction(ActionEvent e) {
        fornecedorVO = null;
        PrincipalController.limparTextField();
        tfNomeFantasia.requestFocus();
    }
    
    public void btOnPressEnter(KeyEvent k){
        if(k.getCode()==KeyCode.ENTER)
        {
            ((Button) k.getSource()).fire();
        }
    }

    public void btGravarOnAction(ActionEvent e) {
        if (fornecedorVO == null) {
            fornecedorVO = new FornecedorVO();
        }
        if(cbUf.getSelectionModel().getSelectedIndex()==-1)
        {
            Alertas.exibirAlerta(Alert.AlertType.ERROR, "Selecione", "Selecione um estado", "Por favor, selecione um estado da lista");
            return;
        }
        fornecedorVO.setUf(cbUf.getSelectionModel().getSelectedItem());
        fornecedorVO.setCidade(tfCidade.getText());
        fornecedorVO.setCnpj(tfCnpj.getText());
        fornecedorVO.setEdereco(tfEndereco.getText());
        fornecedorVO.setEmail(tfEmail.getText());
        fornecedorVO.setNomeFantasia(tfNomeFantasia.getText());
        fornecedorVO.setTelefone(tfTelefone.getText());
        try {
            if (fornecedorVO.getIdFornecedor() != 0) {
                fNegocio.editarFornecedor(fornecedorVO);
            } else {
                fNegocio.inserirFornecedor(fornecedorVO);
            }
            if (Alertas.exibirAlerta(Alert.AlertType.CONFIRMATION, "Gravar", "Fornecedor gravado com sucesso", "Deseja cadastrar outro fornecedor", Alertas.SIM_NAO_BOTOES).get() == ButtonType.YES) {
                fornecedorVO = null;
                PrincipalController.limparTextField();
            } else {

            }
            
        } catch (NegocioException ex) {
            Alertas.exibirAlerta(Alert.AlertType.ERROR, "Erro", ex.getMessage(), ex.getCause().getMessage());
        }
        
    }

    public void setFornecedorVO(FornecedorVO fornecedorVO) {
        this.fornecedorVO = fornecedorVO;
        tfNomeFantasia.setText(fornecedorVO.getNomeFantasia());
        tfCidade.setText(fornecedorVO.getCidade());
        tfCnpj.setText(fornecedorVO.getCnpj());
        tfEmail.setText(fornecedorVO.getEmail());
        tfEndereco.setText(fornecedorVO.getEdereco());
        tfTelefone.setText(fornecedorVO.getTelefone());
        cbUf.getSelectionModel().select(fornecedorVO.getUf());
    }
    

}
