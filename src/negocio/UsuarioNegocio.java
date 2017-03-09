/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

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
public class UsuarioNegocio {

    private UsuarioDAO uDao;

    public UsuarioNegocio() throws NegocioException {
        try {
            this.uDao = new UsuarioDAO(Conexao.getInstance());
        } catch (PersistenciaException ex) {
            throw new NegocioException("Erro ao iniciar a persistência do usuário ", ex);
        }
    }

    public void inserirUsuario(UsuarioVO usuarioVO) throws NegocioException {
        try {
            this.validarUsuario(usuarioVO);
            this.uDao.inserirUsuario(usuarioVO);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Não foi possível inserir o usuário", ex.getCause());
        }
    }

    public void alterarUsuario(UsuarioVO usuarioVO) throws NegocioException {
        try {
            this.validarUsuario(usuarioVO);
            this.uDao.alterarUsuario(usuarioVO);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Não foi possível alterar o usuário", ex.getCause());
        }
    }

    public void deletarUsuario(UsuarioVO usuarioVO) throws NegocioException {
        try {
            this.uDao.deletarUsuario(usuarioVO);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Não foi possível deletar o usuário", ex.getCause());
        }
    }

    public boolean login(UsuarioVO usuarioVO) throws NegocioException {
        if (!usuarioVO.getLogin().trim().isEmpty() && !usuarioVO.getSenha().trim().isEmpty()) {
            try {
                if (!this.uDao.pesquisarUsuario(usuarioVO).get(0).getSenha().equals(usuarioVO.getSenha())) {
                    throw new NegocioException("Senha inválida");
                }
            } catch (PersistenciaException ex) {
                throw new NegocioException("Não foi possível localizar o usuário", ex.getCause());
            } catch(IndexOutOfBoundsException ex){
                throw new NegocioException("Nenhum usuário encontrado");
            }
        } else {
            throw new NegocioException("Informe o login e senha para entrar no sistema");
        }
        return true;
    }

    private void validarUsuario(UsuarioVO usuarioVO) throws NegocioException {
        if (usuarioVO.getLogin().trim().isEmpty() || usuarioVO.getNome().trim().isEmpty() || usuarioVO.getSenha().trim().isEmpty()) {
            throw new NegocioException("Todos os campos são obrigatórios");
        }
        if (!usuarioVO.getSenha().equals(usuarioVO.getConfirmSenha())) {
            throw new NegocioException("As senhas informadas não são iguais");
        }

    }

}
