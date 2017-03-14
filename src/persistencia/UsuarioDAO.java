/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vo.UsuarioVO;

/**
 *
 * @author tcloss
 */
public class UsuarioDAO extends DAO {
    
    
    public UsuarioDAO(Conexao conexao) {
        super(conexao);
    }

    public int inserirUsuario(UsuarioVO usuarioVO) throws PersistenciaException {
        try {
            this.pStatement = this.conexao.getConnection().prepareStatement("INSERT INTO USUARIO(nome,login,senha) VALUES(?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            this.pStatement.setString(1, usuarioVO.getNome());
            this.pStatement.setString(2, usuarioVO.getLogin());
            this.pStatement.setString(3, usuarioVO.getSenha());
            this.pStatement.execute();
            return this.pStatement.getGeneratedKeys().getInt(1);
        } catch (SQLException ex) {
            throw new PersistenciaException("Erro ao inserir usuário", ex);
        }
    }
    
    public void alterarUsuario(UsuarioVO usuarioVO) throws PersistenciaException{
        try {
            this.pStatement=this.conexao.getConnection().prepareStatement("UPDATE USUARIO SET nome=?,login=?,senha=? WHERE id_usuario=?");
            this.pStatement.setString(1, usuarioVO.getNome());
            this.pStatement.setString(2, usuarioVO.getLogin());
            this.pStatement.setString(3, usuarioVO.getSenha());
            this.pStatement.setInt(4, usuarioVO.getId());
            this.pStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new PersistenciaException("Erro ao alterar usuário", ex);
        }
    }
    
    public void deletarUsuario(UsuarioVO usuarioVO) throws PersistenciaException{
        try {
            this.pStatement=this.conexao.getConnection().prepareStatement("DELETE FROM USUARIO WHERE id_usuario=?");
            this.pStatement.setInt(1, usuarioVO.getId());
            this.pStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new PersistenciaException("Erro ao deletar usuário", ex);
        }
    }
    public List<UsuarioVO> pesquisarUsuario(UsuarioVO usuarioVO) throws PersistenciaException{
        try {
            String sql="SELECT * FROM USUARIO WHERE ";
            String where = null;
            if(!usuarioVO.getLogin().isEmpty())
                where="login like '"+usuarioVO.getLogin()+"'";
            if(!usuarioVO.getNome().isEmpty()){
                if(!where.isEmpty())
                    where+=" and ";
                where+="nome like '"+usuarioVO.getNome()+"%'";
            }
            sql=sql+where;
            Statement stmt = this.conexao.getConnection().createStatement();
            return this.convertResultSetToList(stmt.executeQuery(sql));
        } catch (SQLException ex) {
            throw new PersistenciaException("Erro ao realizar a consulta de usuário",ex);
        }
    }
    
    private List<UsuarioVO> convertResultSetToList(ResultSet resultSet) throws PersistenciaException{
        List<UsuarioVO> usuarios = new ArrayList<>();
        try {
            while(resultSet.next())
            {
                usuarios.add(new UsuarioVO(resultSet.getInt("id_usuario"),
                        resultSet.getString("nome"),
                        resultSet.getString("login"),
                        resultSet.getString("senha")));
            }
        } catch (SQLException ex) {
            throw new PersistenciaException("Erro ao percorrer resultados", ex);
        }
        return usuarios;
    }
}
