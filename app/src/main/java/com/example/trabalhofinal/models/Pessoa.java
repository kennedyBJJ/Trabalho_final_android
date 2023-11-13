package com.example.trabalhofinal.models;

import java.io.Serializable;

public class Pessoa implements Serializable {

    private int ID_USER;
    private String NOME_USER;
    private String DATA_NASCIMENTO;
    private String TELEFONE;
    private String SENHA;

    public String getNOME_USER() {
        return NOME_USER;
    }

    public void setNOME_USER(String NOME_USER) {
        this.NOME_USER = NOME_USER;
    }

    public String getDATA_NASCIMENTO() {
        return DATA_NASCIMENTO;
    }

    public void setDATA_NASCIMENTO(String DATA_NASCIMENTO) {
        this.DATA_NASCIMENTO = DATA_NASCIMENTO;
    }

    public String getTELEFONE() {
        return TELEFONE;
    }

    public void setTELEFONE(String TELEFONE) {
        this.TELEFONE = TELEFONE;
    }

    public String getSENHA() {
        return SENHA;
    }

    public void setSENHA(String SENHA) {
        this.SENHA = SENHA;
    }

    public int getID_USER() {
        return ID_USER;
    }

    public void setID_USER(int ID_USER) {
        this.ID_USER = ID_USER;
    }
}
