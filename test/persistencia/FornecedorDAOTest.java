/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import vo.FornecedorVO;

/**
 *
 * @author tcloss
 */
public class FornecedorDAOTest {
    
    public FornecedorDAOTest() {
    }

    @Test
    public void testTodos() throws Exception {
        System.out.println("todos");
        FornecedorDAO instance = new FornecedorDAO(Conexao.getInstance());
        List<FornecedorVO> expResult = null;
        List<FornecedorVO> result = instance.todos();
        result.forEach(action->System.out.print(action));
        //assertEquals(expResult, result);
    }
    
}
