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
public class ProdutoVO {
    private int idProduto;
    private String descricao,marca;
    private int qtdEstoque;
    private double vlrUnitarioVenda,vlrUnitarioCompra;

    public ProdutoVO() {
        this.descricao="";
        this.marca="";
    }
    

    public ProdutoVO(int idProduto, String descricao, String marca, int qtdEstoque, double vlrUnitarioVenda, double vlrUnitarioCompra) {
        this.idProduto = idProduto;
        this.descricao = descricao;
        this.marca = marca;
        this.qtdEstoque = qtdEstoque;
        this.vlrUnitarioVenda = vlrUnitarioVenda;
        this.vlrUnitarioCompra = vlrUnitarioCompra;
    }
    

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public double getVlrUnitarioVenda() {
        return vlrUnitarioVenda;
    }

    public void setVlrUnitarioVenda(double vlrUnitarioVenda) {
        this.vlrUnitarioVenda = vlrUnitarioVenda;
    }

    public double getVlrUnitarioCompra() {
        return vlrUnitarioCompra;
    }

    public void setVlrUnitarioCompra(double vlrUnitarioCompra) {
        this.vlrUnitarioCompra = vlrUnitarioCompra;
    }
    
}
