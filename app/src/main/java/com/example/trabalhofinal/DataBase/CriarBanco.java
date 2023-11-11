package com.example.trabalhofinal.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriarBanco extends SQLiteOpenHelper {

    public  static final String BANCO = "banco";
    public  final String TABELA_USER = "user";
    public  final String TABELA_MEDIC = "medicamentos";
    public  final String TABELA_LEMBRETE = "lembretes";
    public  final String NOME_USER = "nome_user";
    public  final String DATA_NASCIMENTO = "data_nascimento";
    public  final String TELEFONE = "telefone";
    public  final String HORA_LEMBRETE = "hora_lembrete";
    public  final String NOME_MEDICAMENTO = "nome_medicamento";
    public  final String QUANT_MEDICAMENTO = "quant_medicamento";
    public  static final int VERSION = 1;
    public  final String ID_USER = "_id";
    public  final String ID_LEMBRETE = "idLembrete";
    public  final String ID_MEDIC = "idMedicamento";
    public  final String ID_USER_FK = "idUser_FK";
    public  final String ID_MEDIC_FK = "idMedic_FK";

    public CriarBanco(Context context) {
        super(context, CriarBanco.BANCO, null, CriarBanco.VERSION);
    }

    public String getTabelaUser(){
        return TABELA_USER;
    }

    public String getTabelaMedic(){
        return TABELA_MEDIC;
    }

    public String getTabelaLembrete(){
        return TABELA_LEMBRETE;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //representação das tabelas
        String sql1 = "CREATE TABLE IF NOT EXISTS " + TABELA_USER +
                      "(\n" +
                       ID_USER + " INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
                       NOME_USER + " TEXT NOT NULL, \n" +
                       TELEFONE + " TEXT NOT NULL, \n" +
                       DATA_NASCIMENTO + " DATE NOT NULL \n" +
                       ");\n";

        String sql2 = "CREATE TABLE IF NOT EXISTS " + TABELA_MEDIC +
                       "(\n" +
                        ID_MEDIC + " INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
                        NOME_MEDICAMENTO + " TEXT NOT NULL, \n" +
                        QUANT_MEDICAMENTO + " INTEGER NOT NULL, \n" +
                        "FOREIGN KEY (" + ID_USER_FK + ") REFERENCES " + TABELA_USER + "(" + ID_USER + ") \n" +
                        ");\n";

        String sql3 = "CREATE TABLE IF NOT EXISTS " + TABELA_LEMBRETE +
                      "(\n" +
                      ID_LEMBRETE + "INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
                      HORA_LEMBRETE + "DATETIME NOT NULL, \n" +
                    "FOREIGN KEY (" + ID_USER_FK + ") REFERENCES " + TABELA_USER + "(" + ID_USER + "),\n" +
                    "FOREIGN KEY (" + ID_MEDIC_FK + ") REFERENCES " + TABELA_MEDIC + "(" + ID_MEDIC + ")\n" +
                    ");\n";

        //criação das tabelas
        sqLiteDatabase.execSQL(sql1);
        sqLiteDatabase.execSQL(sql2);
        sqLiteDatabase.execSQL(sql3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //apagando as tabelas antigas e criando-as novamente
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_MEDIC);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_LEMBRETE);

        //chamando o metodo para criar as tabelas novamente
        onCreate(db);

    }
}
