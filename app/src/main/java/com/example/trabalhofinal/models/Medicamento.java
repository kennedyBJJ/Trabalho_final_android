package com.example.trabalhofinal.models;

import java.io.Serializable;

public class Medicamento implements Serializable {

    private int ID_MEDIC;
    private String NOME_MEDICAMENTO;
    private int QUANT_MEDICAMENTO;
    private String ID_USER_FK;

    public int getID_MEDIC() {
        return ID_MEDIC;
    }

    public void setID_MEDIC(int ID_MEDIC) {
        this.ID_MEDIC = ID_MEDIC;
    }

    public String getNOME_MEDICAMENTO() {
        return NOME_MEDICAMENTO;
    }

    public void setNOME_MEDICAMENTO(String NOME_MEDICAMENTO) {
        this.NOME_MEDICAMENTO = NOME_MEDICAMENTO;
    }

    public int getQUANT_MEDICAMENTO() {
        return QUANT_MEDICAMENTO;
    }

    public void setQUANT_MEDICAMENTO(int QUANT_MEDICAMENTO) {
        this.QUANT_MEDICAMENTO = QUANT_MEDICAMENTO;
    }

    public String getID_USER_FK() {
        return ID_USER_FK;
    }

    public void setID_USER_FK(String ID_USER_FK) {
        this.ID_USER_FK = ID_USER_FK;
    }
}
