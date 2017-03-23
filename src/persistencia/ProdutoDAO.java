/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
            this.pStatement=this.conexao.getConnection().prepareStatement("INSERT INTO PRODUTO(descricao,marca,qtd_estoque,vlr_unitario_venda,vlr_unitario_compra) VALUES(?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
            this.pStatement.setString(1, produtoVO.getDescricao());
            this.pStatement.setString(2, produtoVO.getMarca());
            this.pStatement.setInt(3, produtoVO.getQtdEstoque());
            this.pStatement.setDouble(4, produtoVO.getVlrUnitarioVenda());
            this.pStatement.setDouble(5, produtoVO.getVlrUnitarioCompra());
            this.pStatement.execute();
            produtoVO.setIdProduto(this.pStatement.getGeneratedKeys().getInt(1));
            return produtoVO;
        } catch (SQLException ex) {
            throw new PersistenciaException("Erro ao inserir na camada de persistência", ex);
        }
    }
    
    public void atualizarProduto(ProdutoVO produtoVO) throws PersistenciaException{
        try {
            this.pStatement=this.conexao.getConnection().prepareStatement("UPDATE PRODUTO SET descricao=?,marca=?,qtd_estoque=?,vlr_unitario_venda=?,vlr_unitario_compra=? where id_produto=?");
            this.pStatement.setString(1, produtoVO.getDescricao());
            this.pStatement.setString(2, produtoVO.getMarca());
            this.pStatement.setInt(3, produtoVO.getQtdEstoque());
            this.pStatement.setDouble(4, produtoVO.getVlrUnitarioVenda());
            this.pStatement.setDouble(5, produtoVO.getVlrUnitarioCompra());
            this.pStatement.setInt(6, produtoVO.getIdProduto());
            this.pStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new PersistenciaException("Erro ao atualizar o produto na camada de persistência", ex);
        }
        
    }
    
    public void deletarProdtudo(ProdutoVO produtoVO) throws PersistenciaException{
        try {
            this.pStatement=this.conexao.getConnection().prepareStatement("DELETE FROM PRODUTO WHERE id_produto=?");
            this.pStatement.setInt(1, produtoVO.getIdProduto());
            this.pStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new PersistenciaException("Erro ao deletar o produto na camada de persistência", ex);
        }
        
    }
    
    public List<ProdutoVO> listarTodos() throws PersistenciaException{
        try {
            this.pStatement=this.conexao.getConnection().prepareStatement("SELECT * FROM PRODUTO");
            this.resultSet=this.pStatement.executeQuery();
            return preencherList();
        } catch (SQLException ex) {
            throw new PersistenciaException("Erro ao selecionar produtos", ex);
        }
        
    }
    
    private List<ProdutoVO> preencherList() throws PersistenciaException{
        try {
            List<ProdutoVO> produtos = new ArrayList<>();
            while(this.resultSet.next())
            {
                produtos.add(new ProdutoVO(this.resultSet.getInt("id_produto"),
                        this.resultSet.getString("descricao"),
                        this.resultSet.getString("marca"),
                        this.resultSet.getInt("qtd_estoque"),
                        this.resultSet.getDouble("vlr_unitario_venda"),
                        this.resultSet.getDouble("vlr_unitario_compra")));
            }
            return produtos;
        } catch (SQLException ex) {
            throw new PersistenciaException("Erro ao carregar lista de produtos", ex);
        }
    }
    
}
