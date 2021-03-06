/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitarios;

import com.sun.javafx.collections.ElementObservableListDecorator;
import java.util.ArrayList;
import java.util.Collection;
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
    public static Collection<ButtonType> SIM_NAO_BOTOES;
    static {
        SIM_NAO_BOTOES=new ArrayList<>();
        SIM_NAO_BOTOES.add(ButtonType.YES);
        SIM_NAO_BOTOES.add(ButtonType.NO);       
    };
    public static Optional<ButtonType> exibirAlerta(AlertType tipo, String titulo, String cabecalho, String corpo){
        return createAlert(tipo, titulo, cabecalho, corpo).showAndWait();
    }
    public static Optional<ButtonType> exibirAlerta(AlertType tipo, String titulo, String cabecalho, String corpo,Collection<ButtonType> botoes){
        return  createAlert(tipo, titulo, cabecalho, corpo, botoes).showAndWait();
    }
    private static Alert createAlert(AlertType tipo, String titulo, String cabecalho, String corpo){
        msg=new Alert(tipo);
        msg.setTitle(titulo);
        msg.setHeaderText(cabecalho);
        msg.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        msg.setContentText(corpo);
        return msg;
    }
    private static Alert createAlert(AlertType tipo, String titulo, String cabecalho, String corpo,Collection<ButtonType> botoes){
        msg = createAlert(tipo, titulo, cabecalho, corpo);
        msg.getButtonTypes().clear();
        msg.getButtonTypes().addAll(botoes);
        return msg;
    }
}
