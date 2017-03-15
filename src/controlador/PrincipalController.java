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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import utilitarios.Alertas;

/**
 *
 * @author tcloss
 */
public class PrincipalController implements Initializable {

    @FXML
    private Pane painel;
    private static Pane sPainel;
    @FXML
    private static Parent cadastroUsuario, listaUsuario, listaProduto,cadastroProduto;
    private FXMLLoader fxControlador;
    public static CadastroUsuarioController cadastroUsuarioCtr;
    private static ListaUsuarioController listaUsuarioCtr;

    public PrincipalController() {
        try {
            fxControlador = new FXMLLoader(getClass().getResource("../view/usuario/cadastro.fxml"));
            cadastroUsuario = fxControlador.load();
            cadastroUsuarioCtr = (CadastroUsuarioController) fxControlador.getController();

            fxControlador = new FXMLLoader(getClass().getResource("../view/usuario/lista.fxml"));
            listaUsuario = fxControlador.load();
            listaUsuarioCtr = fxControlador.getController();

            fxControlador = new FXMLLoader(getClass().getResource("../view/produto/lista.fxml"));
            listaProduto = fxControlador.load();
            
            fxControlador=new FXMLLoader(getClass().getResource("../view/produto/cadastro.fxml"));
            cadastroProduto=fxControlador.load();
            

        } catch (IOException ex) {
            System.out.println(ex);
            Alertas.exibirAlerta(Alert.AlertType.ERROR, "Erro", "Erro ao carregar a tela", ex.getLocalizedMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//            listaUsuarioCtr.setCadastro(cadastroUsuario);
//            listaUsuarioCtr.setPainelBase(painel);
//            cadastroUsuarioCtr.setPrincipal(listaUsuario);
        //cadastroUsuarioCtr.setPainelBase(painel);
        sPainel = painel;

    }

    public void btUsuarioOnAction(ActionEvent e) {
        abrirListaUsuario();
    }

    public void btProdutoOnAction(ActionEvent e) {
        carregarTela(listaProduto);
    }

    private static void carregarTela(Parent tela) {
        sPainel.getChildren().clear();
        sPainel.getChildren().add(tela);
    }

    public static void abrirListaUsuario() {
        listaUsuarioCtr.preencherTabela();
        carregarTela(listaUsuario);
    }
    public static void abrirListaProduto(){
        carregarTela(listaProduto);
    }
    public static void abrirCadastroUsuario(){
        carregarTela(cadastroUsuario);
    }
    public static void abrirCadastroProduto(){
        carregarTela(cadastroProduto);
    }
    
    public static void limparTextField(){
        
    }
    
    private static void limpar(Parent no){
        
    }

}
