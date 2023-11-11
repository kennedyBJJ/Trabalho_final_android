package com.example.trabalhofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Novo_Lembrete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_lembrete);
        androidx.appcompat.widget.AppCompatButton  btnSalvar = findViewById(R.id.btnSalvar);
        androidx.appcompat.widget.AppCompatButton  btnCancelar = findViewById(R.id.btnCancelar);


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para abrir Tela 1
                Intent intent = new Intent(Novo_Lembrete.this, Meus_Lembretes.class);
                startActivity(intent);
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para abrir Tela 1
                Intent intent = new Intent(Novo_Lembrete.this,Meus_Lembretes.class);
                startActivity(intent);
            }
        });
    }
}