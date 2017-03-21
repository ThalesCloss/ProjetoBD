/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitarios;

import com.sun.javafx.binding.StringFormatter;

/**
 *
 * @author tcloss
 */
public class Validador {

    private static Validador instacia;
    private final int CPF = 11, CNPJ = 14;

    private Validador() {
    }

    public static Validador getInstance() {
        if (instacia == null) {
            instacia = new Validador();
        }
        return instacia;
    }

    public boolean validarCPF(String cpf) throws ValidadorException {
        cpf=cpf.replace(".", "").replace("-", "");
        if(cpf.length()!=11)
            throw new ValidadorException("CPF Inválido",new ValidadorException("O CPF deve conter 11 dígitos"));
        if(!digitosValidos(cpf, CPF))
            throw new ValidadorException("CPF Inválido", new ValidadorException("O CPF possui uma sequência de digitos inválida"));
        char[] validar=cpf.toCharArray();
        if(digitoCPFeCNPJ(validar, 10, 8)!=Character.getNumericValue(validar[9]) || digitoCPFeCNPJ(validar, 10, 9)!=Character.getNumericValue(validar[10]))
            throw new ValidadorException("CPF Inválido", new ValidadorException("CPF Inválido"));
        return true;
    }

    public boolean validarCNPJ(String cnpj) throws ValidadorException {
        cnpj = cnpj.replace(".", "").replace("-", "").replace("/", "");
        if (cnpj.length() != 14) {
            throw new ValidadorException("CNPJ Inválido", new ValidadorException("O CNPJ deve conter 14 dígitos"));
        }
        if (!digitosValidos(cnpj, CNPJ)) {
            throw new ValidadorException("CNPJ Inválido", new ValidadorException("O CNPJ possui uma sequência de digitos inválida"));
        }
        char[] validar = cnpj.toCharArray();
        if (digitoCPFeCNPJ(validar, 9, 11) != Character.getNumericValue(validar[12]) || digitoCPFeCNPJ(validar, 9, 12) != Character.getNumericValue(validar[13])) {
            throw new ValidadorException("CNPJ Inválido", new ValidadorException("CNPJ inválido"));
        }
        return true;
    }

    private int digitoCPFeCNPJ(char[] validar, int mMult, int mIndice) {
        int soma = 0;
        int mult = 2;
        for (int i = mIndice; i != 0; i--) {
            soma += Character.getNumericValue(validar[i]) * mult;
            mult = mult == mMult ? 2 : mult + 1;
        }
        return soma % 11 <= 1 ? 0 : 11 - (soma % 11);
    }

    private boolean digitosValidos(String documento, int tipoDocumento) {
        for (int n = 0; n < 10; n++) {
            if (documento.matches("[" + n + "]{" + tipoDocumento + "}")) {
                return false;
            }
        }
        return true;
    }

}
