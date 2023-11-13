package com.example.trabalhofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trabalhofinal.DataBase.BancoControle;
import com.example.trabalhofinal.models.Pessoa;

import java.util.Objects;

public class Login extends AppCompatActivity {

    EditText edtUsuario;
    EditText edtSenha;
    String usuario;
    String senha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        androidx.appcompat.widget.AppCompatButton  btnEntrar = findViewById(R.id.btnEntrar);
        androidx.appcompat.widget.AppCompatButton  btnCriarConta = findViewById(R.id.btnCriarConta);

        Intent cadastro = getIntent();
        edtUsuario = findViewById(R.id.edtUsuario);
        usuario = cadastro.getStringExtra("usuario");

        if(usuario != null){
            edtUsuario.setText(usuario);
        }
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Pessoa usuarioLogado = login();

                if(usuarioLogado== null){

                    Toast.makeText(getApplicationContext(), "NÃO FOI POSSÍVEL LOGAR", Toast.LENGTH_LONG).show();
                }else {

                    Intent intent = new Intent(Login.this, Inicio.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("usuarioLogado", usuarioLogado);
                    startActivity(intent);
                }

//                edtUsuario.setText(txt);
            }
        });

        btnCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para abrir Tela 1
                Intent intent = new Intent(Login.this, Form_Cadastro.class);
                startActivity(intent);
            }
        });



    }

    public Pessoa login(){
        edtUsuario = findViewById(R.id.edtUsuario);
        edtSenha = findViewById(R.id.edtSenha);

        usuario = edtUsuario.getText().toString();
        senha = edtSenha.getText().toString();

        BancoControle bd = new BancoControle(this);
        Pessoa usuarioLogado = bd.procurarUsuario(usuario, senha);


        return (usuarioLogado.getSENHA().equals( senha))? usuarioLogado: null;
    }
}