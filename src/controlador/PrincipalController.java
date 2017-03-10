/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

/**
 *
 * @author tcloss
 */
public class PrincipalController implements Initializable{
    
    @FXML
    private Pane painel;
    
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        try {
            //());
            painel.getChildren().add(FXMLLoader.load(getClass().getResource("../view/usuario/cadastro.fxml")));
            //painel.getChildren().clear();
            //painel.add(FXMLLoader.load(getClass().getResource("../view/login.fxml")), 0, 1);
            
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
