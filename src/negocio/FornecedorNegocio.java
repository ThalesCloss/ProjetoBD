/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.Conexao;
import persistencia.FornecedorDAO;
import persistencia.PersistenciaException;
import utilitarios.Validador;
import utilitarios.ValidadorException;
import vo.FornecedorVO;

/**
 *
 * @author tcloss
 */
public class FornecedorNegocio {

    private FornecedorDAO fDAO;

    public FornecedorNegocio() throws NegocioException {
        try {
            fDAO = new FornecedorDAO(Conexao.getInstance());
        } catch (PersistenciaException ex) {
            throw new NegocioException("Erro ao iniciar a percistência de fornecedor", ex);
        }
    }

    private void validarFornecedor(FornecedorVO fornecedorVO) throws NegocioException {
        String causa = "";
        Validador v = Validador.getInstance();
        if (fornecedorVO.getNomeFantasia().trim().isEmpty()) {
            causa = "O nome fantasia deve ser preenchido\n";

        }
        try {
            v.validarCNPJ(fornecedorVO.getCnpj().trim());

        } catch (ValidadorException ex) {
            causa += ex.getCause().getMessage() + "\n";
        }
        if (fornecedorVO.getTelefone().trim().isEmpty()) {
            causa += "O telefone deve ser preenchido\n";
        }
        if (fornecedorVO.getEmail().trim().isEmpty()) {
            causa += "O e-mail deve ser preenchido\n";
        }
        if (fornecedorVO.getEdereco().trim().isEmpty()) {
            causa += "O endereço deve ser preenchido\n";
        }
        if (fornecedorVO.getCidade().trim().isEmpty()) {
            causa += "A cidade deve ser preenchida\n";
        }
        if (fornecedorVO.getUf().trim().isEmpty()) {
            causa += "O estado deve ser preenchido\n";
        }
        if (!causa.trim().isEmpty()) {
            throw new NegocioException("Dados inconsistentes", new NegocioException(causa));
        }
    }
    private void prepararDados(FornecedorVO fornecedorVO){
        fornecedorVO.setCnpj(fornecedorVO.getCnpj().replace(".", "").replace("-", "").replace("/", ""));
    }

}
