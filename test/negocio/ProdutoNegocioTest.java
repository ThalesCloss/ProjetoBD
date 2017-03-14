/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import vo.ProdutoVO;

/**
 *
 * @author tcloss
 */
public class ProdutoNegocioTest {
    
    public ProdutoNegocioTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of inserirUsuario method, of class ProdutoNegocio.
     */
    @Test
    public void testInserirUsuario(){
        try {
            System.out.println("inserirUsuario");
            ProdutoVO produtoVO = new ProdutoVO();
            ProdutoNegocio instance = new ProdutoNegocio();
            instance.inserirUsuario(produtoVO);
            
            // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
        } catch (NegocioException ex) {
            System.out.println(ex.getLocalizedMessage()+" "+ex.getCause().getMessage());
        }
    }
    
}
