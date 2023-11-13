package com.example.trabalhofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.trabalhofinal.models.Pessoa;

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
                Intent intent = new Intent(Inicio.this, Meus_Medicamentos.class);

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
                Intent intent = new Intent(Inicio.this, Minha_Conta.class);


                Intent previous = getIntent();
                Pessoa usuarioLogado = (Pessoa) previous.getSerializableExtra("usuarioLogado");
                intent.putExtra("usuarioLogado", usuarioLogado);
                startActivity(intent);
            }
        });



    }
}