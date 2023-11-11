package com.example.trabalhofinal.DataBase;

import static com.example.trabalhofinal.DataBase.criarBanco.TABELA_LEMBRETE;
import static com.example.trabalhofinal.DataBase.criarBanco.getTabelaLembrete;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


public class BancoControle {

    private SQLiteDatabase db;
    private CriarBanco banco;


    public BancoControle(Context contexto) {
        banco = new criarBanco(contexto);
    }

    public void inserirDadosUser(String NOME_USER, String DATA_NASCIMENTO, String TELEFONE){
        db = banco.getWritableDatabase();
        ContentValues values = new ContentValues();

        //fornece os valores que serão inseridos nas colunas
        values.put(banco.NOME_USER, NOME_USER);
        values.put(banco.DATA_NASCIMENTO, DATA_NASCIMENTO);
        values.put(banco.TELEFONE, TELEFONE);

        db.insert(banco.getTabelaUser());

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
