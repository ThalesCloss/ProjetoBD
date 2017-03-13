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
public enum FormaPagamento {
    BOLETO("BL","Boleto"),
    CHEQUE("CH","Cheque"),
    CARTAO_DEBITO("CD","Cartão de débito"),
    CARTAO_CREDITO("CC","Cartão de crédito"),
    DINHEIRO("DH","Dinheiro");
    
    private final String sigla,descricao;
    private FormaPagamento(String sigla, String descricao) {
        this.sigla = sigla;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
    
    
    
    
}
