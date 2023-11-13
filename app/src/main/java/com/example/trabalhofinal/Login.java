package com.example.trabalhofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    EditText edtUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        androidx.appcompat.widget.AppCompatButton  btnEntrar = findViewById(R.id.btnEntrar);
        androidx.appcompat.widget.AppCompatButton  btnCriarConta = findViewById(R.id.btnCriarConta);

        Intent cadastro = getIntent();
        edtUsuario = findViewById(R.id.edtUsuario);
        String usuario = cadastro.getStringExtra("usuario");

        if(usuario != null){
            edtUsuario.setText(usuario);
        }
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para abrir Tela 1
                Intent intent = new Intent(Login.this, Inicio.class);
                startActivity(intent);
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
}