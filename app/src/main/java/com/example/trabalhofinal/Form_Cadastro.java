package com.example.trabalhofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trabalhofinal.DataBase.BancoControle;

import java.util.Calendar;
import java.util.Objects;

public class Form_Cadastro extends AppCompatActivity {

    String nome;
    String dataNascimento;
    String numeroCelular;
    String usuario;
    String senha;
    String repeteSenha;
    boolean aceitouTermos;
    EditText edtNome;
    EditText edtDataNascimento;
    EditText edtNumeroCelular;
    EditText edtUsuario;
    EditText edtSenha;
    EditText edtRepeteSenha;
    CheckBox aceitacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);

        androidx.appcompat.widget.AppCompatButton  btnCriarConta = findViewById(R.id.btnCadastrarConta);
        androidx.appcompat.widget.AppCompatButton  btnVoltar = findViewById(R.id.btnVoltarInicio);


        definirFormaDeEscolherData();
        btnCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean cadastrou = cadastrar();
                if(cadastrou) {
                    Intent intent = new Intent(Form_Cadastro.this, Login.class);
                    intent.putExtra("usuario", usuario);

                    startActivity(intent);

                }


            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para abrir Tela 1
                edtUsuario = findViewById(R.id.edtCadUsuario);
                usuario = edtUsuario.getText().toString();

                Intent intent = new Intent(Form_Cadastro.this, Login.class);

                startActivity(intent);
            }
        });
    }

    private boolean cadastrar(){
        edtNome = findViewById(R.id.edtNome);
        edtDataNascimento = findViewById(R.id.edtDataNasc);
        edtNumeroCelular = findViewById(R.id.edtCelular);
        edtUsuario = findViewById(R.id.edtCadUsuario);
        edtSenha = findViewById(R.id.edtCadSenha);
        edtRepeteSenha = findViewById(R.id.edtRepeteSenha);
        aceitacao = findViewById(R.id.checkAceitacao);
//
        nome = edtNome.getText().toString();
        dataNascimento = edtDataNascimento.getText().toString();

        numeroCelular = edtNumeroCelular.getText().toString();
        usuario = edtUsuario.getText().toString();
        senha = edtSenha.getText().toString();
        repeteSenha = edtRepeteSenha.getText().toString();
        aceitouTermos = aceitacao.isChecked();

        if(!aceitouTermos){
            String txt = "É PRECISO ACEITAR OS TERMOS DE USUÁRIO!!";
            edtUsuario.setText(txt);
            Toast.makeText(this, "É PRECISO ACEITAR OS TERMOS DE USUÁRIO!!", Toast.LENGTH_LONG).show();
            return false;
        }
        BancoControle db = new BancoControle(this);

        if( ! senha.equals(repeteSenha)){
            String txt = "AS SENHAS SÃO DIFERENTES!!";
            edtUsuario.setText(txt);
            Toast.makeText(this, "AS SENHAS SÃO DIFERENTES!!", Toast.LENGTH_LONG).show();
            return false;
        }

        boolean resultado;

        resultado = db.inserirDadosUser(usuario, dataNascimento, numeroCelular, senha);

//
        if(!resultado){
            String txt = "NÃO FOI POSSÍVEL CADASTRAR USUÁRIO!! TEM CERTEZA QUE NÃO POSSUI CONTA?";
            edtUsuario.setText(txt);
            Toast.makeText(this, "NÃO FOI POSSÍVEL CADASTRAR USUÁRIO!! TEM CERTEZA QUE NÃO POSSUI CONTA?", Toast.LENGTH_LONG).show();
            return false;
        }


        return true;
    }

    private void definirFormaDeEscolherData(){
        edtDataNascimento = findViewById(R.id.edtDataNasc);

        edtDataNascimento.setOnClickListener((View v) -> {
            DatePickerDialog.OnDateSetListener listener =
                    (DatePicker view, int year, int month, int dayOfMonth) ->
                    {
                        String data = dayOfMonth + "/" + month + "/" + year;
                        edtDataNascimento.setText(data);
                    };
            Calendar cd = Calendar.getInstance();

            DatePickerDialog datePicker = new DatePickerDialog(this, listener,
                    cd.get(Calendar.YEAR), cd.get(Calendar.MONTH) + 1, cd.get(Calendar.DAY_OF_MONTH));
            datePicker.show();

        }
        );
    }
}
