package com.example.trabalhofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.text.Normalizer;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        androidx.appcompat.widget.AppCompatButton buttonEntrar = findViewById(R.id.btnEntrar);
        androidx.appcompat.widget.AppCompatButton buttonCriarConta = findViewById(R.id.btnCriarConta);
        buttonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Criar uma intenção para abrir Inicio
                Intent intent = new Intent(Login.this,Inicio.class);
                startActivity(intent);

            }
        });

        buttonCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Criar uma intenção para abrir Form_Cadastro
                Intent intent = new Intent(Login.this,Form_Cadastro.class);
                startActivity(intent);
            }
        });

    }
}