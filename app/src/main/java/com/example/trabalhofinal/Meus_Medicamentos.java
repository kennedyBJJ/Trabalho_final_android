package com.example.trabalhofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.trabalhofinal.models.Pessoa;

public class Meus_Medicamentos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_medicamentos);


        androidx.appcompat.widget.AppCompatButton  btnNovoMedicamento = findViewById(R.id.btnNovoMedicamento);
        androidx.appcompat.widget.AppCompatButton  btnVoltar = findViewById(R.id.btnVoltarInicio);


        btnNovoMedicamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para abrir Tela 1
                Intent intent = new Intent(Meus_Medicamentos.this, Novo_Medicamento.class);

                Intent previous = getIntent();
                Pessoa usuarioLogado = (Pessoa) previous.getSerializableExtra("usuarioLogado");
                intent.putExtra("usuarioLogado", usuarioLogado);
                startActivity(intent);
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para abrir Tela 1
                Intent intent = new Intent(Meus_Medicamentos.this, Inicio.class);
                startActivity(intent);
            }
        });
//

    }
}