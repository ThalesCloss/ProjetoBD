/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import vo.ProdutoVO;

/**
 *
 * @author tcloss
 */
public class ProdutoDAO extends DAO {
    
    public ProdutoDAO(Conexao conexao) {
        super(conexao);
    }
    
    public ProdutoVO inserirProduto(ProdutoVO produtoVO) throws PersistenciaException{
        try {
            this.pStatement=this.conexao.getConnection().prepareStatement("INSERT INTO PRODUTO(descricao,marca,qtd_estoque,vlt_unitario_venda,vlr_unitario_compra) VALUES(?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
            this.pStatement.execute();
            produtoVO.setIdProduto(this.pStatement.getGeneratedKeys().getInt(0));
            return produtoVO;
        } catch (SQLException ex) {
            throw new PersistenciaException("Erro ao inserir na camada de persistência", ex);
        }
    }
    
    public void atualizarProduto(ProdutoVO produtoVO) throws PersistenciaException{
        try {
            this.pStatement=this.conexao.getConnection().prepareStatement("UPDATE PRODUTO SET descricao=?,marca=?,qtd_estoque=?,vlr_unitario_venda=?,vlr_unitario_compra where id_produto=?");
            this.pStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new PersistenciaException("Erro ao atualizar o produto na camada de persistência", ex);
        }
        
    }
    
    public void deletarProdtudo(ProdutoVO produtoVO){
        this.pStatement=this.conexao.getConnection().prepareStatement("")
    }
    
}
