/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema;

import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.Conexao;
import persistencia.PersistenciaException;
import persistencia.UsuarioDAO;
import vo.UsuarioVO;

/**
 *
 * @author tcloss
 */
public class Sistema {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Conexao.getInstance();
            UsuarioDAO u = new UsuarioDAO(Conexao.getInstance());
            UsuarioVO usr= new UsuarioVO();
            usr.setLogin("teste");
            usr.setNome("teste");
            usr.setSenha("teste");
            System.out.println(u.inserirUsuario(usr));
            u.pesquisarUsuario(usr).forEach(e->System.out.println(e.getLogin()));
        } catch (PersistenciaException ex) {
            System.out.println(ex.getMessage()+"\n"+ex.getCause());
        }
    }
    
}
