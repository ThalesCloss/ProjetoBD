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
import java.util.logging.Level;
import java.util.logging.Logger;
import vo.Estados;
import vo.FornecedorVO;

/**
 *
 * @author tcloss
 */
public class FornecedorDAO extends DAO {

    public FornecedorDAO(Conexao conexao) {
        super(conexao);
    }

    public int inserirFornecedor(FornecedorVO fornecedorVO) throws PersistenciaException {
        try {
            this.pStatement = this.conexao.getConnection().prepareStatement("INSERT INTO FORNECEDOR (nome_fantasia,cnpj,telefone,email,endereco,cidade,uf) VALUES(?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            this.pStatement.setString(1, fornecedorVO.getNomeFantasia());
            this.pStatement.setString(2, fornecedorVO.getCnpj());
            this.pStatement.setString(3, fornecedorVO.getTelefone());
            this.pStatement.setString(4, fornecedorVO.getEmail());
            this.pStatement.setString(5, fornecedorVO.getEdereco());
            this.pStatement.setString(6, fornecedorVO.getCidade());
            this.pStatement.setString(7, fornecedorVO.getUf().name());
            this.pStatement.execute();
            return this.pStatement.getGeneratedKeys().getInt(1);
        } catch (SQLException ex) {
            throw new PersistenciaException("Erro ao persistir dados do fornecedor", ex);
        }
    }

    public void editarFornecedor(FornecedorVO fornecedorVO) throws PersistenciaException {
        try {
            this.pStatement = this.conexao.getConnection().prepareStatement("UPDATE FORNECEDOR SET nome_fantasia=?,cnpj=?,telefone=?,email=?,endereco=?,cidade=?,uf=? WHERE id_fornecedor=?");
            this.pStatement.setString(1, fornecedorVO.getNomeFantasia());
            this.pStatement.setString(2, fornecedorVO.getCnpj());
            this.pStatement.setString(3, fornecedorVO.getTelefone());
            this.pStatement.setString(4, fornecedorVO.getEmail());
            this.pStatement.setString(5, fornecedorVO.getEdereco());
            this.pStatement.setString(6, fornecedorVO.getCidade());
            this.pStatement.setString(7, fornecedorVO.getUf().name());
            this.pStatement.setInt(8, fornecedorVO.getIdFornecedor());
            this.pStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new PersistenciaException("Erro ao persistir os dados de atualização do fornecedor", ex);
        }
    }

    public void deletarFornecedor(FornecedorVO fornecedorVO) throws PersistenciaException {
        try {
            this.pStatement = this.conexao.getConnection().prepareStatement("DELETE FROM FORNECEDOR WHERE id_fornecedor=?");
            this.pStatement.setInt(1, fornecedorVO.getIdFornecedor());
            this.pStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new PersistenciaException("Erro na camada de persistência ao excluir fornecedor ", ex);
        }

    }

    private List<FornecedorVO> listaFornecedor() throws PersistenciaException {
        try {
            List<FornecedorVO> fornecedores = new ArrayList<>();
            while (this.resultSet.next()) {
                fornecedores.add(
                        new FornecedorVO(
                                this.resultSet.getInt("id_fornecedor"),
                                this.resultSet.getString("nome_fantasia"),
                                this.resultSet.getString("cnpj"),
                                this.resultSet.getString("telefone"),
                                this.resultSet.getString("email"),
                                this.resultSet.getString("endereco"),
                                this.resultSet.getString("cidade"),
                                Estados.valueOf(this.resultSet.getString("uf"))
                        );
            }
            return fornecedores;
        } catch (SQLException ex) {
            throw new PersistenciaException("Erro ao navegar no resultset", ex);
        }

    }

    public List<FornecedorVO> todos() throws PersistenciaException {
        try {
            this.pStatement = this.conexao.getConnection().prepareStatement("SELECT * FROM FORNECEDOR");
            this.resultSet = this.pStatement.executeQuery();
            return this.listaFornecedor();
        } catch (SQLException ex) {
            throw new PersistenciaException("Erro ao selecionar fornecedores", ex);
        }
    }

}
