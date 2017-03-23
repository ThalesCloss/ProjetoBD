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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import utilitarios.Alertas;

/**
 *
 * @author tcloss
 */
public class PrincipalController implements Initializable {

    @FXML
    private GridPane painel;
    private static Pane sPainel;
    @FXML
    private static Parent cadastroUsuario, listaUsuario, listaProduto, cadastroProduto, cadastroFornecedor,listaFornecedor,cadastroPedido;
    private FXMLLoader fxControlador;
    public static CadastroUsuarioController cadastroUsuarioCtr;
    private static ListaUsuarioController listaUsuarioCtr;
    public static CadastroProdutoController cadastroProdutoCtr;
    public static ListaProdutoController listaProdutoCtr;
    public static CadastroFornecedorController cadastroFornecedorCtr;
    public static ListaFornecedorController listaFornecedorCtr;
    
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
            listaProdutoCtr = fxControlador.getController();

            fxControlador = new FXMLLoader(getClass().getResource("../view/produto/cadastro.fxml"));
            cadastroProduto = fxControlador.load();
            cadastroProdutoCtr = fxControlador.getController();

            fxControlador = new FXMLLoader(getClass().getResource("../view/fornecedor/cadastro.fxml"));
            cadastroFornecedor = fxControlador.load();
            cadastroFornecedorCtr=fxControlador.getController();
            
            fxControlador=new FXMLLoader(getClass().getResource("../view/fornecedor/lista.fxml"));
            listaFornecedor=fxControlador.load();
            listaFornecedorCtr=fxControlador.getController();
            
            fxControlador=new FXMLLoader(getClass().getResource("../view/pedido/cadastro.fxml"));
            cadastroPedido=fxControlador.load();

        } catch (IOException ex) {
            System.out.println(ex);
            Alertas.exibirAlerta(Alert.AlertType.ERROR, "Erro", "Erro ao carregar a tela", ex.getLocalizedMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sPainel = painel;
    }

    public void btUsuarioOnAction(ActionEvent e) {
        abrirListaUsuario();
    }

    public void btProdutoOnAction(ActionEvent e) {
        abrirListaProduto();
    }

    public void btFornecedorOnAction(ActionEvent e) {
        abrirListaFornecedor();
    }
    public void btPedidoOnAction(ActionEvent e){
        abrirCadastroPedido();
    }

    private static void carregarTela(Parent tela) {
        sPainel.getChildren().clear();
        sPainel.getChildren().add(tela);
    }
    public static void abrirCadastroPedido(){
        carregarTela(cadastroPedido);
    }

    public static void abrirCadastroFornecedor() {
        carregarTela(cadastroFornecedor);
    }
    public static void abrirListaFornecedor(){
        listaFornecedorCtr.atualizarTabela();
        carregarTela(listaFornecedor);
    }

    public static void abrirListaUsuario() {
        listaUsuarioCtr.preencherTabela();
        carregarTela(listaUsuario);
    }

    public static void abrirListaProduto() {
        listaProdutoCtr.atualizarTabela();
        carregarTela(listaProduto);
    }

    public static void abrirCadastroUsuario() {
        carregarTela(cadastroUsuario);
    }

    public static void abrirCadastroProduto() {
        carregarTela(cadastroProduto);
    }

    public static void limparTextField() {
        limpar(sPainel);
    }

    private static void limpar(Parent limpar) {
        limpar.getChildrenUnmodifiable().forEach(component -> {
            if (component instanceof ComboBox) {
                ((ComboBox) component).getSelectionModel().select(-1);
            } else if (component instanceof TextField) {
                ((TextField) component).setText("");
            } else if (component instanceof Parent) {
                limpar((Parent) component);
            }
        }
        );
    }
}
