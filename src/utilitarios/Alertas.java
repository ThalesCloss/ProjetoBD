/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitarios;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;

/**
 *
 * @author tcloss
 */
public class Alertas {
    private static Alert msg;
    
    public static Optional<ButtonType> exibirAlerta(AlertType tipo, String titulo, String cabecalho, String corpo){
        msg=new Alert(tipo);
        msg.setTitle(titulo);
        msg.setHeaderText(cabecalho);
        msg.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        
        msg.setContentText(corpo);
        return msg.showAndWait();
    }
}
