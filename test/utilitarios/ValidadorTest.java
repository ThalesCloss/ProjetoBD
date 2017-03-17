/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitarios;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tcloss
 */
public class ValidadorTest {
    
    public ValidadorTest() {
    }


    /**
     * Test of validarCPF method, of class Validador.
     */
    @Test
    public void testValidarCPF() throws Exception {
        System.out.println("validarCPF");
        String cpf = "084.229.419-88";
        cpf="999.999.999-99";
        Validador instance = Validador.getInstance();
        boolean expResult = true;
        boolean result = instance.validarCPF(cpf);
        assertEquals(expResult, result);
    }

    /**
     * Test of validarCNPJ method, of class Validador.
     */
    @Test
    public void testValidarCNPJ() throws Exception {
        System.out.println("validarCNPJ");
        String cnpj = "06.135.840/0001-48";
        Validador instance = Validador.getInstance();
        boolean expResult = true;
        boolean result = instance.validarCNPJ(cnpj);
        assertEquals(expResult, result);
    }
    
}
