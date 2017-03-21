/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

/**
 *
 * @author tcloss
 */
public enum Estados {
    AC("Acre","AC"),
    AL("Alagoas","AL"),
    AM("AMAZONAS","AM"),
    AP("Amapá","AP"),
    BA("Bahia","BA"),
    CE("Ceará","CE"),
    ES("Espirito Santo","ES"),
    GO("Goiás","GO"),
    MA("Maranhão","MA"),
    MG("Minas Gerais","MG"),
    MS("Mato Grosso do SUl","MS"),
    MT("Mato Grosso","MT"),
    PA("Pará","PA"),
    PB("Paraíba","PB"),
    PE("Pernambuco","PE"),
    PI("Piauí","PI"),
    PR("Paraná","PR"),
    RJ("Rio de Janeiro","RJ"),
    RNO("Rio Grande do Norte","RN"),
    RO("Rondônia","RO"),
    RR("Roraima","RR"),
    RS("Rio Grande do Sul","RS"),
    SC("Santa Catarina","SC"),
    SE("Sergipe","SE"),
    SP("São Paulo","SP"),
    TO("Tocantins","TO");
     
    private final String NOME;
    private final String UF;
    private Estados(String NOME, String UF) {
        this.NOME = NOME;
        this.UF = UF;
    }

    @Override
    public String toString() {
        return UF+" - "+NOME;
    }

    public String getNOME() {
        return NOME;
    }

    public String getUF() {
        return UF;
    }
        
    
}
