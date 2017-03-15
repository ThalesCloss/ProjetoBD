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
        if(usuarioVO==null)
            usuarioVO=new UsuarioVO();
        usuarioVO.setLogin(tfLogin.getText());
        usuarioVO.setNome(tfNome.getText());
        usuarioVO.setSenha(pfSenha.getText());
        usuarioVO.setConfirmSenha(pfConfSenha.getText());
        try {
            if (usuarioVO.getId() == 0) {
                uNegocio.inserirUsuario(usuarioVO);
            } else {
                uNegocio.alterarUsuario(usuarioVO);
            }
            if(Alertas.exibirAlerta(Alert.AlertType.CONFIRMATION, "Gravação", "Usuário gravado com sucesso", "Deseja cadastrar outro usuário", Alertas.SIM_NAO_BOTOES).get()==ButtonType.NO)
            {
                PrincipalController.abrirListaUsuario();
            }
        } catch (NegocioException ex) {
            Alertas.exibirAlerta(Alert.AlertType.ERROR, "Erro", ex.getLocalizedMessage(), ex.getCause().getLocalizedMessage());
        }
        
    }
    
    @FXML
    public void btCancelarOnAction(ActionEvent evt) {
        usuarioVO = null;
        //PrincipalController.limparTextField();
       // PrincipalController.abrirListaUsuario();
    }
    
        
    public UsuarioVO getUsuarioVO() {
        return usuarioVO;
    }
    
    public void setUsuarioVO(UsuarioVO usuarioVO) {
        this.usuarioVO = usuarioVO;
         if(usuarioVO!=null && usuarioVO.getId()!=0)
        {
            tfLogin.setText(usuarioVO.getLogin());
            tfNome.setText(usuarioVO.getNome());
            pfSenha.setText(usuarioVO.getSenha());
            pfConfSenha.setText(usuarioVO.getSenha());
        }
    }
}
