/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import utilitarios.Alertas;
import vo.UsuarioVO;


/**
 *
 * @author tcloss
 */
public class PrincipalController implements Initializable{
    
    @FXML
    private Pane painel;
    @FXML
    
    private Parent cadastroUsuario,listaUsuario,listaProduto;
    private FXMLLoader fxControlador;
    private CadastroUsuarioController cadastroUsuarioCtr;
    private ListaUsuarioController listaUsuarioCtr;
    
    public PrincipalController() {
        try {
            fxControlador = new FXMLLoader(getClass().getResource("../view/usuario/cadastro.fxml"));
            cadastroUsuario=fxControlador.load();
            cadastroUsuarioCtr = (CadastroUsuarioController) fxControlador.getController();
            
            fxControlador=new FXMLLoader(getClass().getResource("../view/usuario/lista.fxml"));
            listaUsuario=fxControlador.load();
            listaUsuarioCtr=fxControlador.getController();
            listaUsuarioCtr.setCadUsrCtrl(cadastroUsuarioCtr);
            
            fxControlador=new FXMLLoader(getClass().getResource("../view/produto/lista.fxml"));
            listaProduto=fxControlador.load();
            
            
        } catch (IOException ex) {
            System.out.println(ex);
            Alertas.exibirAlerta(Alert.AlertType.ERROR, "Erro", "Erro ao carregar a tela", ex.getLocalizedMessage());
        }
    }
    
    
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
            listaUsuarioCtr.setCadastro(cadastroUsuario);
            listaUsuarioCtr.setPainelBase(painel);
            cadastroUsuarioCtr.setPrincipal(listaUsuario);
            cadastroUsuarioCtr.setPainelBase(painel);
       
    }
    
    public void btUsuarioOnAction(ActionEvent e){        
        carregarTela(listaUsuario);
    }
    public void btProdutoOnAction(ActionEvent e){        
        carregarTela(listaProduto);
    }
    
    private void carregarTela(Parent tela){
        painel.getChildren().clear();
        painel.getChildren().add(tela);
    }
    
    
}
