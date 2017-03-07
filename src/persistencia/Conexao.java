/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tcloss
 */
public class Conexao {
    private static Connection connection;
    private static Conexao instancia;
    
    private Conexao() throws PersistenciaException{
        try{
            Class.forName("org.postgresql.Driver");
            connection=DriverManager.getConnection("jdbc.postgresql:localhost:5432/banco", "postgres", "");
        } catch (ClassNotFoundException ex) {
            throw new PersistenciaException("Driver não localizado", ex);
        } catch (SQLException ex) {
            throw new PersistenciaException("Não foi possível estabelecer a conexão com o banco de dados", ex);
        }
    }
    
    public static Conexao getInstance() throws PersistenciaException{
        try {
            if(instancia==null || instancia.getConnection().isClosed())
                instancia=new Conexao();
            return instancia;
        } catch (SQLException ex) {
            throw new PersistenciaException("Erro ao verificar a conexão");
        }
    }
    
    public Connection getConnection(){
        return connection;
    }
    
}
