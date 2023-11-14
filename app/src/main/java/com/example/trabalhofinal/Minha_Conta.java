package com.example.trabalhofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Minha_Conta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minha_conta);

        androidx.appcompat.widget.AppCompatButton  btnAlterarDados = findViewById(R.id.btnSalvarModificacao);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) androidx.appcompat.widget.AppCompatButton  btnVoltar = findViewById(R.id.btnVoltarInicio);


        btnAlterarDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para abrir Tela 1
                Intent intent = new Intent(Minha_Conta.this, Alterar_Dados.class);
                startActivity(intent);
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para abrir Tela 1
                Intent intent = new Intent(Minha_Conta.this, Inicio.class);
                startActivity(intent);
            }
        });
//

    }
}