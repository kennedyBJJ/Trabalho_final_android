package com.example.trabalhofinal;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.trabalhofinal.models.Pessoa;


public class Meus_Lembretes extends AppCompatActivity {

    private Button map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_lembretes);

        map = findViewById(R.id.btnFarmacia);

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Meus_Lembretes.this, com.example.trabalhofinal.Localization.LocalizacaoMain.class);
                Intent previous = getIntent();
                Pessoa usuarioLogado = (Pessoa) previous.getSerializableExtra("usuarioLogado");
                intent.putExtra("usuarioLogado", usuarioLogado);
            }
        });


        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) androidx.appcompat.widget.AppCompatButton  btnNovoLembrete = findViewById(R.id.btnNovoLembret);
        androidx.appcompat.widget.AppCompatButton  btnMeusMedicamentos = findViewById(R.id.btnCancelarMedicamento);
        androidx.appcompat.widget.AppCompatButton  btnVerFarmacia = findViewById(R.id.btnFarmacia);
        androidx.appcompat.widget.AppCompatButton  btnMinhaConta = findViewById(R.id.btnMinhaConta);


        btnNovoLembrete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para abrir Tela 1
                Intent intent = new Intent(Meus_Lembretes.this, Novo_Lembrete.class);

                Intent previous = getIntent();
                Pessoa usuarioLogado = (Pessoa) previous.getSerializableExtra("usuarioLogado");
                intent.putExtra("usuarioLogado", usuarioLogado);

                startActivity(intent);
            }
        });

        btnMeusMedicamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para abrir Tela 1
                Intent intent = new Intent(Meus_Lembretes.this, Meus_Medicamentos.class);

                Intent previous = getIntent();
                Pessoa usuarioLogado = (Pessoa) previous.getSerializableExtra("usuarioLogado");
                intent.putExtra("usuarioLogado", usuarioLogado);

                startActivity(intent);
            }
        });
        btnVerFarmacia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para abrir Tela 1
                Intent intent = new Intent(Meus_Lembretes.this,Geolocalizacao.class);

                Intent previous = getIntent();
                Pessoa usuarioLogado = (Pessoa) previous.getSerializableExtra("usuarioLogado");
                intent.putExtra("usuarioLogado", usuarioLogado);

                startActivity(intent);
            }
        });

        btnMinhaConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para abrir Tela 1
                Intent intent = new Intent(Meus_Lembretes.this, Minha_Conta.class);

                Intent previous = getIntent();
                Pessoa usuarioLogado = (Pessoa) previous.getSerializableExtra("usuarioLogado");
                intent.putExtra("usuarioLogado", usuarioLogado);

                startActivity(intent);
            }
        });

    }

}