/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import java.util.Calendar;

/**
 *
 * @author tcloss
 */
public class PagamentoPedidoVO {
    private int idPagamento;
    private FormaPagamento formaPagamento;
    private double vlrFatura,vlrPago;
    private Calendar dtVencimento,dtPago;
    private PedidoVO pedido;

    public PagamentoPedidoVO(int idPagamento, FormaPagamento formaPagamento, double vlrFatura, double vlrPago, Calendar dtVencimento, Calendar dtPago) {
        this.idPagamento = idPagamento;
        this.formaPagamento = formaPagamento;
        this.vlrFatura = vlrFatura;
        this.vlrPago = vlrPago;
        this.dtVencimento = dtVencimento;
        this.dtPago = dtPago;
    }
    

    public int getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(int idPagamento) {
        this.idPagamento = idPagamento;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public double getVlrFatura() {
        return vlrFatura;
    }

    public void setVlrFatura(double vlrFatura) {
        this.vlrFatura = vlrFatura;
    }

    public double getVlrPago() {
        return vlrPago;
    }

    public void setVlrPago(double vlrPago) {
        this.vlrPago = vlrPago;
    }

    public Calendar getDtVencimento() {
        return dtVencimento;
    }

    public void setDtVencimento(Calendar dtVencimento) {
        this.dtVencimento = dtVencimento;
    }

    public Calendar getDtPago() {
        return dtPago;
    }

    public void setDtPago(Calendar dtPago) {
        this.dtPago = dtPago;
    }

    public int getIdPedido() {
        return pedido.getIdPedido();
    }

    public void setIdPedido(int idPedido) {
        pedido.setIdPedido(idPedido);
    }
    
}
