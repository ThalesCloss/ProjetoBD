/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import java.util.Calendar;
import java.util.List;

/**
 *
 * @author tcloss
 */
public class PedidoVO {
    private int idPedido;
    private Calendar dtPedido,dtPrevisaoEntrega;
    private double vlrTotal;
    private FornecedorVO fornecedor;
    private UsuarioVO usuario;
    private List<PagamentoPedidoVO> pagamento;

    public PedidoVO(int idPedido, Calendar dtPedido, Calendar dtPrevisaoEntrega, double vlrTotal) {
        this.idPedido = idPedido;
        this.dtPedido = dtPedido;
        this.dtPrevisaoEntrega = dtPrevisaoEntrega;
        this.vlrTotal = vlrTotal;
    }

    public PedidoVO(int idPedido, Calendar dtPedido, Calendar dtPrevisaoEntrega, double vlrTotal, UsuarioVO usuario, List<PagamentoPedidoVO> pagamento) {
        this(idPedido, dtPedido, dtPrevisaoEntrega, vlrTotal);
        this.pagamento = pagamento;
    }
    

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Calendar getDtPedido() {
        return dtPedido;
    }

    public void setDtPedido(Calendar dtPedido) {
        this.dtPedido = dtPedido;
    }

    public Calendar getDtPrevisaoEntrega() {
        return dtPrevisaoEntrega;
    }

    public void setDtPrevisaoEntrega(Calendar dtPrevisaoEntrega) {
        this.dtPrevisaoEntrega = dtPrevisaoEntrega;
    }

    public double getVlrTotal() {
        return vlrTotal;
    }

    public void setVlrTotal(double vlrTotal) {
        this.vlrTotal = vlrTotal;
    }
    
    
}
