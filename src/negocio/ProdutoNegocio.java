/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.Conexao;
import persistencia.PersistenciaException;
import persistencia.ProdutoDAO;
import vo.ProdutoVO;

/**
 *
 * @author tcloss
 */
public class ProdutoNegocio {
    private ProdutoDAO produtoDAO;

    public ProdutoNegocio() throws NegocioException {
        try {
            produtoDAO=new ProdutoDAO(Conexao.getInstance());
        } catch (PersistenciaException ex) {
            throw new NegocioException("Erro ao iniciar o negócio de produto", ex.getCause());
        }
    }
    
    public void inserirProduto(ProdutoVO produtoVO) throws NegocioException{
        validarProduto(produtoVO);
        try {
            this.produtoDAO.inserirProduto(produtoVO);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Erro ao inserir produto", ex.getCause());
        }
    }
    public void atualizarProduto(ProdutoVO produtoVO) throws NegocioException{
        validarProduto(produtoVO);
        try {
            this.produtoDAO.atualizarProduto(produtoVO);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Erro ao alterar produto", ex.getCause());
        }
    }
    
    private void validarProduto(ProdutoVO produtoVO) throws NegocioException{
        String exception = "Erro na validação do produto";
        String causa="";
        boolean erro=false;
        if(produtoVO!=null){
            if(produtoVO.getDescricao().trim().isEmpty() | produtoVO.getDescricao().trim().length()>200)
            {
                causa="A descrição não pode ser vazia e não pode ter mais de 200 caracteres\n";
                erro=true;
            }
            if(produtoVO.getMarca().trim().isEmpty() | produtoVO.getDescricao().trim().length()>100){
                causa+="A marca não pode ser vazia e não pode ter mais de 100 caracteres\n";
                erro=true;
            }
            if(produtoVO.getVlrUnitarioCompra()==0){
                causa+="O valor unitário de compra não pode ser zero\n";
                erro=true;
            }
            if(produtoVO.getVlrUnitarioVenda()==0){
                causa+="O valor unitário de venda não pode ser zero\n";
                erro=true;
            }
        }
        else
        {
          causa+="Produto vazio";
          erro=true;
        }
        if(erro)
            throw new NegocioException(exception, new Throwable(causa));
        
    }
    public List<ProdutoVO> todos() throws  NegocioException{
        try {
            return produtoDAO.listarTodos();
        } catch (PersistenciaException e) {
            throw new NegocioException("Erro ao carregar produtos", e.getCause());
        }
        
    }
    
    public void excluirProduto(ProdutoVO produtoVO) throws NegocioException{
        try {
            produtoDAO.deletarProdtudo(produtoVO);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Erro ao excluir produto",ex.getCause());
        }
    }
    
}
