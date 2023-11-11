package com.example.trabalhofinal.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class BancoControle {

    //permite a conexão com o banco de dados
    private SQLiteDatabase db;
    private CriarBanco banco;


    public BancoControle(Context contexto) {
        banco = new CriarBanco(contexto);
    }

    public boolean inserirDadosUser(String NOME_USER, String DATA_NASCIMENTO, String TELEFONE){

        //permite a escrita no banco
        db = banco.getWritableDatabase();
        ContentValues values = new ContentValues();

        //fornece os valores que serão inseridos nas colunas
        values.put(banco.NOME_USER, NOME_USER);
        values.put(banco.DATA_NASCIMENTO, DATA_NASCIMENTO);
        values.put(banco.TELEFONE, TELEFONE);

        //insere os dados no banco
       if(db.insert(banco.getTabelaUser(),null,values) == -1){
           return false;
       }

       //encerra a conexão com o banco de dados
       db.close();
       return true;

    }

    public boolean inserirDadosMedicamento(int ID_USER_FK, String NOME_MEDICAMENTO, int QUANT_MEDICAMENTO){

        db = banco.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(banco.ID_USER_FK, ID_USER_FK);
        values.put(banco.NOME_MEDICAMENTO, NOME_MEDICAMENTO);
        values.put(banco.QUANT_MEDICAMENTO, QUANT_MEDICAMENTO);

        if(db.insert(banco.getTabelaMedic(),null,values) == -1){
            return false;
        }

        db.close();
        return true;
    }

    public boolean inserirDadosLembrete(int ID_USER_FK, int ID_MEDIC_FK, String HORA_LEMBRETE){

        db = banco.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(banco.ID_USER_FK, ID_USER_FK);
        values.put(banco.ID_MEDIC_FK, ID_MEDIC_FK);
        values.put(banco.HORA_LEMBRETE, HORA_LEMBRETE);

        if(db.insert(banco.getTabelaLembrete(),null,values) == -1){
            return false;
        }

        db.close();
        return true;
    }

    public void alteraDadosUsuario(int id, String NOME, String DATA, String NUMERO){
        db = banco.getWritableDatabase();
        ContentValues values = new ContentValues();

        String where = banco.ID_USER + "=" + id;

        values.put(banco.NOME_USER, NOME);
        values.put(banco.DATA_NASCIMENTO, DATA);
        values.put(banco.TELEFONE, NUMERO);

        db.update(banco.getTabelaUser(),values,where,null);
        db.close();
    }

    public void alteraHoraLembrete(int id, String HORA){
        db = banco.getWritableDatabase();
        ContentValues values = new ContentValues();

        String where = banco.ID_LEMBRETE + "=" + id;

        values.put(banco.HORA_LEMBRETE, HORA);

        db.update(banco.getTabelaLembrete(),values,where,null);
        db.close();
    }

    public void alteraMedicamento(int id, String NOME){
        db = banco.getWritableDatabase();
        ContentValues values = new ContentValues();

        String where = banco.ID_MEDIC + "=" + id;

        values.put(banco.NOME_MEDICAMENTO, NOME);

        db.update(banco.getTabelaMedic(),values,where,null);
        db.close();
    }
}
