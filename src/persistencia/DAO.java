/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tcloss
 */
public class DAO {
    protected Conexao conexao;
    protected PreparedStatement pStatement;
    protected ResultSet resultSet;
    public DAO(Conexao conexao) {
        this.conexao=conexao;
    }
    
    public void startTransaction() throws PersistenciaException{
        try{
            if(this.conexao.getConnection().getAutoCommit()){
                this.conexao.getConnection().setAutoCommit(false);
            }                
        } catch (SQLException ex) {
            throw new PersistenciaException("Erro ao iniciar transação", ex);
        }
    }
    
    public void commitTransaction() throws PersistenciaException{
        try {
            if(!this.conexao.getConnection().getAutoCommit())
            {
                this.conexao.getConnection().commit();
                this.conexao.getConnection().setAutoCommit(true);
            }
        } catch (SQLException ex) {
            throw new PersistenciaException("Erro ao confirmar transação",ex);
        }
    }
    
    public void roolbackTransaction() throws PersistenciaException{
        try {
            if(!this.conexao.getConnection().getAutoCommit())
            {
                this.conexao.getConnection().rollback();
                this.conexao.getConnection().setAutoCommit(true);
            }
        } catch (SQLException ex) {
            throw new PersistenciaException("Erro ao cancelar transação",ex);
        }
    }
    public Savepoint createSavepoint() throws PersistenciaException{
        try {
            return this.conexao.getConnection().setSavepoint();
        } catch (SQLException ex) {
            throw new PersistenciaException("Erro ao criar ponto de salvamento", ex);
        }
    }
    public void deleteSavepoint(Savepoint savePoint) throws PersistenciaException{
        try {
            this.conexao.getConnection().releaseSavepoint(savePoint);
        } catch (SQLException ex) {
            throw new PersistenciaException("Erro ao excluir ponto de salvamento", ex);
        }
    }
    public void roolbackTransactionToSavePoint(Savepoint savePoint) throws PersistenciaException{
        try {
            this.conexao.getConnection().rollback(savePoint);
        } catch (SQLException ex) {
            throw new PersistenciaException("Erro ao voltar ao ponto de salvamento", ex);
        }
    }
    
    
}
