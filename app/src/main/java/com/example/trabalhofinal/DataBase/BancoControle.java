package com.example.trabalhofinal.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import com.example.trabalhofinal.models.Medicamento;
import com.example.trabalhofinal.models.Pessoa;

import java.util.ArrayList;

public class BancoControle {

    //permite a conexão com o banco de dados
    private SQLiteDatabase db;
    //acessa o banco criado
    private CriarBanco banco;


    public BancoControle(Context contexto) {
        banco = new CriarBanco(contexto);
    }

    public boolean inserirDadosUser(String NOME_USER, String DATA_NASCIMENTO, String TELEFONE, String SENHA){

        //permite a escrita no banco
        db = banco.getWritableDatabase();
        ContentValues values = new ContentValues();

        //fornece os valores que serão inseridos nas colunas
        values.put(banco.NOME_USER, NOME_USER);
        values.put(banco.DATA_NASCIMENTO, DATA_NASCIMENTO);
        values.put(banco.TELEFONE, TELEFONE);
        values.put(banco.SENHA, SENHA);

        //insere os dados no banco
        int resultado = (int) db.insert(banco.getTabelaUser(),null,values);
       if(resultado == -1){

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

    public void alteraDadosUsuario(int id, String NOME, String DATA, String NUMERO, String SENHA){
        db = banco.getWritableDatabase();
        ContentValues values = new ContentValues();

        String where = banco.ID_USER + "=" + id;

        values.put(banco.NOME_USER, NOME);
        values.put(banco.DATA_NASCIMENTO, DATA);
        values.put(banco.TELEFONE, NUMERO);
        values.put(banco.SENHA, SENHA);

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

    public Pessoa procurarUsuario(String usuario, String senha){
        db = banco.getWritableDatabase();
        String where = String.format(
                " %s = '%s' AND %s = '%s' ;", banco.NOME_USER, usuario, banco.SENHA, senha
        );


        Cursor cursor = db.query(banco.getTabelaUser(), null, where, null, null, null, null);

        cursor.moveToFirst();

        Pessoa resultado = new Pessoa();

        resultado.setID_USER(cursor.getInt(0));
        resultado.setNOME_USER(cursor.getString(1));
        resultado.setTELEFONE(cursor.getString(2));
        resultado.setDATA_NASCIMENTO(cursor.getString(3));
        resultado.setSENHA(cursor.getString(4));

        cursor.close();
        return resultado;

    }

    public int count(){

        db = banco.getWritableDatabase();

        Cursor cursor = db.query(banco.getTabelaMedic(), null, null, null, null, null, null);
        int resultado = cursor.getCount();
        cursor.close();

        return resultado;
    }

    public ArrayList<Medicamento> selectMedicamentos(int id_user){
        db = banco.getWritableDatabase();

        String where = String.format(
          " %s = %d", banco.ID_USER_FK, id_user
        );

        ArrayList<Medicamento> resultado= new ArrayList<Medicamento>();

        Cursor cursor = db.query(banco.getTabelaMedic(), null, where, null, null, null, null);

        cursor.moveToFirst();

        while(cursor.moveToNext()){

            Medicamento medicamento = new Medicamento();

            medicamento.setID_MEDIC(cursor.getInt(0));
            medicamento.setNOME_MEDICAMENTO(cursor.getString(1));
            medicamento.setQUANT_MEDICAMENTO(cursor.getInt(2));
            medicamento.setID_USER_FK(cursor.getInt(3));

            resultado.add(medicamento);

        }
        return resultado;

    }
}
