/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema;

import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.Conexao;
import persistencia.PersistenciaException;

/**
 *
 * @author tcloss
 */
public class Sistema {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Conexao.getInstance();
        } catch (PersistenciaException ex) {
            System.out.println(ex.getMessage()+"\n"+ex.getCause().getMessage());
           
        }
    }
    
}
