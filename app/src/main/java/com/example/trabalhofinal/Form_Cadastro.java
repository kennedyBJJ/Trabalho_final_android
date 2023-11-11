package com.example.trabalhofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Form_Cadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);
        androidx.appcompat.widget.AppCompatButton  btnCriarConta = findViewById(R.id.btnCadastrarConta);
        androidx.appcompat.widget.AppCompatButton  btnVoltar = findViewById(R.id.btnVoltarInicio);


        btnCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para abrir Tela 1
                Intent intent = new Intent(Form_Cadastro.this, Login.class);
                startActivity(intent);
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para abrir Tela 1
                Intent intent = new Intent(Form_Cadastro.this, Login.class);
                startActivity(intent);
            }
        });
    }
}