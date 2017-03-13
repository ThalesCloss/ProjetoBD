/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

/**
 *
 * @author tcloss
 */
public class PedidoProdutoVO {
    private int qtdItem;
    private double vlrUnitario;
    private FornecedorVO fornecedor;
    private ProdutoVO produto;

    public int getQtdItem() {
        return qtdItem;
    }

    public double getVlrUnitario() {
        return vlrUnitario;
    }

    public int getIdFornecedor() {
        return fornecedor.getIdFornecedor();
    }

    public void setIdFornecedor(int idFornecedor) {
        fornecedor.setIdFornecedor(idFornecedor);
    }

    public void setIdProduto(int idProduto) {
        produto.setIdProduto(idProduto);
    }
    
}
