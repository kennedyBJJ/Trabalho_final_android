package com.example.trabalhofinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class BancoControle {

    private SQLiteDatabase db;
    private criarBanco banco;


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

        db.insert(banco.getTabelaUser())

    }
}
