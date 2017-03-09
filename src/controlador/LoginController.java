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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import negocio.NegocioException;
import negocio.UsuarioNegocio;

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
    private Stage palco;
    private boolean logado = false;
    private UsuarioNegocio uNegocio;

  

    public void setPalco(Stage palco) {
        this.palco = palco;
    }

    @FXML
    public void btLogarOnAction(ActionEvent event) {

        logado = true;
        Alert l = new Alert(Alert.AlertType.WARNING);
        l.setTitle("Teste");
        l.showAndWait();
        palco.close();
    }

    @FXML
    public void btSairOnAction(ActionEvent event) {
        palco.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            uNegocio=new UsuarioNegocio();
            System.out.println("controlador.LoginController.<init>()");
        } catch (NegocioException ex) {
            Alert erro = new Alert(Alert.AlertType.ERROR);
            erro.contentTextProperty().setValue(ex.getMessage());
        }
        // TODO
    }

    public boolean isLogado() {
        return logado;
    }

}
