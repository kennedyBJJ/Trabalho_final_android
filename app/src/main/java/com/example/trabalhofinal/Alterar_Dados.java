package com.example.trabalhofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Alterar_Dados extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_dados);

     androidx.appcompat.widget.AppCompatButton  btnSalvarModificacoes = findViewById(R.id.btnSalvarModificacao);
        androidx.appcompat.widget.AppCompatButton  btnCancelar = findViewById(R.id.btnCancelar);


        btnSalvarModificacoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para abrir Tela 1
                Intent intent = new Intent(Alterar_Dados.this, Minha_Conta.class);
                startActivity(intent);
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para abrir Tela 1
                Intent intent = new Intent(Alterar_Dados.this, Minha_Conta.class);
                startActivity(intent);
            }
        });
    }
}