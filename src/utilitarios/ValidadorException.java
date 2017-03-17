/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitarios;

/**
 *
 * @author tcloss
 */
public class ValidadorException extends Exception{

    public ValidadorException(String message) {
        super(message);
    }

    public ValidadorException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
