/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import utilitarios.Alertas;


/**
 *
 * @author tcloss
 */
public class PrincipalController implements Initializable{
    
    @FXML
    private Pane painel;
    @FXML
    private Label lbTelaAtual;
    private Parent cadastroUsuario,listaUsuario;
    private FXMLLoader fxControlador;
    private CadastroUsuarioController cadastroUsuarioCtr;
    
    public PrincipalController() {
        try {
            fxControlador = new FXMLLoader(getClass().getResource("../view/usuario/cadastro.fxml"));
            cadastroUsuario=fxControlador.load();
            cadastroUsuarioCtr = (CadastroUsuarioController) fxControlador.getController();
            System.out.println(cadastroUsuarioCtr.toString());
            
            fxControlador=new FXMLLoader(getClass().getResource("../view/usuario/lista.fxml"));
            listaUsuario=fxControlador.load();
        } catch (IOException ex) {
            Alertas.exibirAlerta(Alert.AlertType.ERROR, "Erro", "Erro ao carregar a tela", ex.getLocalizedMessage());
        }
    }
    
    
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
            cadastroUsuarioCtr.setPainelBase(painel);
       
    }
    
    public void limpar(ActionEvent e){
        painel.getChildren().clear();
        painel.getChildren().add(listaUsuario);
    }
    public void add(ActionEvent e){
                cadastroUsuarioCtr.setPrincipal(listaUsuario);
                painel.getChildren().clear();
                lbTelaAtual.setText("Cadastro de Usuário");
                painel.getChildren().add(cadastroUsuario);
            
    }
    
    
}
