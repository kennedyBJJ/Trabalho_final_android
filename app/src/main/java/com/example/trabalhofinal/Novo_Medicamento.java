package com.example.trabalhofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Novo_Medicamento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_medicamneto);

        androidx.appcompat.widget.AppCompatButton  btnSalvarMedicamento = findViewById(R.id.btnSalvarMedicamento);
        androidx.appcompat.widget.AppCompatButton  btnCancelar = findViewById(R.id.btnCancelarMedicamento);


        btnSalvarMedicamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para abrir Tela 1
                Intent intent = new Intent(Novo_Medicamento.this, Meus_Medicamentos.class);
                startActivity(intent);
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para abrir Tela 1
                Intent intent = new Intent(Novo_Medicamento.this, Meus_Medicamentos.class);
                startActivity(intent);
            }
        });
    }
}