package com.example.trabalhofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Meus_Lembretes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_lembretes);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) androidx.appcompat.widget.AppCompatButton  btnNovoLembrete = findViewById(R.id.btnNovoLembret);
        androidx.appcompat.widget.AppCompatButton  btnMeusMedicamentos = findViewById(R.id.btnCancelarMedicamento);
//        androidx.appcompat.widget.AppCompatButton  btnFarmacia = findViewById(R.id.btnFarmacia);
        androidx.appcompat.widget.AppCompatButton  btnMinhaConta = findViewById(R.id.btnMinhaConta);


        btnNovoLembrete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para abrir Tela 1
                Intent intent = new Intent(Meus_Lembretes.this, Novo_Lembrete.class);
                startActivity(intent);
            }
        });

        btnMeusMedicamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para abrir Tela 1
                Intent intent = new Intent(Meus_Lembretes.this, Meus_Medicamentos.class);
                startActivity(intent);
            }
        });
//        btnFarmacia.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Código para abrir Tela 1
//                Intent intent = new Intent(Meus_Lembretes.this, Meus_Medicamentos.class);
//                startActivity(intent);
//            }
//        });

        btnMinhaConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para abrir Tela 1
                Intent intent = new Intent(Meus_Lembretes.this, Minha_Conta.class);
                startActivity(intent);
            }
        });

    }
}