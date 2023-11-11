package com.example.trabalhofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Inicio extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        androidx.appcompat.widget.AppCompatButton  btnMeusLembretes = findViewById(R.id.btnSalvarMedicamento);
        androidx.appcompat.widget.AppCompatButton  btnMeusMedicamentos = findViewById(R.id.btnCancelarMedicamento);
        androidx.appcompat.widget.AppCompatButton  btnMinhaConta = findViewById(R.id.btnMinhaConta);


        btnMeusLembretes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para abrir Tela 1
                Intent intent = new Intent(Inicio.this, Meus_Lembretes.class);
                startActivity(intent);
            }
        });

        btnMeusMedicamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para abrir Tela 1
                Intent intent = new Intent(Inicio.this, Meus_Medicamentos.class);
                startActivity(intent);
            }
        });

        btnMinhaConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para abrir Tela 1
                Intent intent = new Intent(Inicio.this, Minha_Conta.class);
                startActivity(intent);
            }
        });



    }
}