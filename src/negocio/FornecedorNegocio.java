/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import persistencia.Conexao;
import persistencia.FornecedorDAO;
import persistencia.PersistenciaException;
import utilitarios.Alertas;
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
    
    public void inserirFornecedor(FornecedorVO fornecedorVO) throws NegocioException{
        validarFornecedor(fornecedorVO);
        prepararDados(fornecedorVO);
        try {
            fDAO.inserirFornecedor(fornecedorVO);
        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage(), ex.getCause());
        }
    }
    public void editarFornecedor(FornecedorVO fornecedorVO) throws NegocioException{
        validarFornecedor(fornecedorVO);
        prepararDados(fornecedorVO);
        try {
            fDAO.editarFornecedor(fornecedorVO);
        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage(), ex.getCause());
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
        if (fornecedorVO.getUf()==null) {
            causa += "O estado deve ser preenchido\n";
        }
        if (!causa.trim().isEmpty()) {
            throw new NegocioException("Dados inconsistentes", new NegocioException(causa));
        }
    }
    private void prepararDados(FornecedorVO fornecedorVO){
        fornecedorVO.setCnpj(fornecedorVO.getCnpj().replace(".", "").replace("-", "").replace("/", ""));
    }
    
    public List<FornecedorVO> todos() throws NegocioException{
        try {
            return fDAO.todos();
        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage(), ex.getCause());
        }
    }

}
