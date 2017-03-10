/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import negocio.NegocioException;
import negocio.UsuarioNegocio;
import vo.UsuarioVO;
import utilitarios.Alertas;

/**
 * FXML Controller class
 *
 * @author tcloss
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField tf_login;
    @FXML
    private PasswordField tf_senha;
    @FXML
    private Button btEntrar;
    private Stage palco;
    private boolean logado = false;
    private UsuarioNegocio uNegocio;
    private UsuarioVO usuarioVO;

    public LoginController() {
        try {
            uNegocio = new UsuarioNegocio();
            usuarioVO = new UsuarioVO();
        } catch (NegocioException ex) {
            Alertas.exibirAlerta(AlertType.ERROR, "Erro", ex.getMessage(), ex.getCause().getMessage());
            System.exit(1);
        }
    }

    public void setPalco(Stage palco) {
        this.palco = palco;
    }
    @FXML
    public void enterOnSenha(KeyEvent key){
        if(key.getCode()==KeyCode.ENTER)
        {
            btEntrar.fire();
        }
        
    }
    @FXML
    public void btLogarOnAction(ActionEvent event) {
        try {
        this.usuarioVO.setLogin(this.tf_login.getText());
        this.usuarioVO.setSenha(this.tf_senha.getText());
            logado = this.uNegocio.login(this.usuarioVO);
            palco.close();
        } catch (NegocioException ex) {
            Alertas.exibirAlerta(AlertType.ERROR, "Erro", ex.getMessage(), ex.getCause().getMessage());
        }

    }

    @FXML
    public void btSairOnAction(ActionEvent event) {
        palco.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public boolean isLogado() {
        return logado;
    }

}
