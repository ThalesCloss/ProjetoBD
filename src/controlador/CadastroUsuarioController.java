/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import negocio.NegocioException;
import negocio.UsuarioNegocio;
import utilitarios.Alertas;
import vo.UsuarioVO;

/**
 *
 * @author tcloss
 */
public class CadastroUsuarioController implements Initializable {

    private UsuarioNegocio uNegocio;
    private UsuarioVO usuarioVO;
    @FXML
    private TextField tfLogin;
    @FXML
    private TextField tfNome;
    @FXML
    private PasswordField pfSenha;
    @FXML
    private PasswordField pfConfSenha;

    private Pane painelBase;
    private Parent principal;

    public Pane getPainelBase() {
        return painelBase;
    }

    public void setPainelBase(Pane painelBase) {
        this.painelBase = painelBase;
    }

    public CadastroUsuarioController() {
        try {
            uNegocio = new UsuarioNegocio();
        } catch (NegocioException ex) {
            Alertas.exibirAlerta(Alert.AlertType.ERROR, "Erro", "Erro ao iniciar a persistência do usuário", ex.getLocalizedMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void btGravarOnAction(ActionEvent evt) {
        usuarioVO = new UsuarioVO();
        usuarioVO.setLogin(tfLogin.getText());
        usuarioVO.setNome(tfNome.getText());
        usuarioVO.setSenha(pfSenha.getText());
        usuarioVO.setConfirmSenha(pfConfSenha.getText());
        Collection<ButtonType> bt = new ArrayList<>();
        bt.add(new ButtonType("Sim", ButtonBar.ButtonData.YES));
        bt.add(new ButtonType("Não", ButtonBar.ButtonData.NO));
        try {
            uNegocio.inserirUsuario(usuarioVO);
            Alertas.exibirAlerta(Alert.AlertType.CONFIRMATION, "Inserir novo", "Usuário cadastrado com sucesso", "Deseja cadastrar outro usuário", bt);
        } catch (NegocioException ex) {
            Alertas.exibirAlerta(Alert.AlertType.ERROR, "Erro", ex.getLocalizedMessage(), ex.getCause().getLocalizedMessage());
        }

    }

    @FXML
    public void btCancelarOnAction(ActionEvent evt) {
        usuarioVO = null;
        System.out.println(painelBase.toString());
        this.painelBase.getChildren().clear();
        this.painelBase.getChildren().add(this.principal);
    }

    public Parent getPrincipal() {
        return principal;
    }

    public void setPrincipal(Parent principal) {
        this.principal = principal;
    }

    public UsuarioVO getUsuarioVO() {
        return usuarioVO;
    }

    public void setUsuarioVO(UsuarioVO usuarioVO) {
        this.usuarioVO = usuarioVO;
    }
}
