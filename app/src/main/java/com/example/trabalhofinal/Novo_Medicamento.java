package com.example.trabalhofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.trabalhofinal.DataBase.BancoControle;
import com.example.trabalhofinal.models.Pessoa;

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

                if(cadastrouMedicamento()) {


                    Intent intent = new Intent(Novo_Medicamento.this, Meus_Medicamentos.class);

                    Intent previous = getIntent();
                    Pessoa usuarioLogado = (Pessoa) previous.getSerializableExtra("usuarioLogado");
                    intent.putExtra("usuarioLogado", usuarioLogado);

                    startActivity(intent);
                }
            }
        }
        );
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para abrir Tela 1
                Intent intent = new Intent(Novo_Medicamento.this, Meus_Medicamentos.class);

                Intent previous = getIntent();
                Pessoa usuarioLogado = (Pessoa) previous.getSerializableExtra("usuarioLogado");
                intent.putExtra("usuarioLogado", usuarioLogado);

                startActivity(intent);
            }
        });
    }

    public boolean cadastrouMedicamento(){
        Intent previousIntent = getIntent();

        EditText edtMedicamento = findViewById(R.id.edtMedicamento);
        EditText edtQuantidade = findViewById(R.id.edtQuantidade);

        String medicamento = edtMedicamento.getText().toString();
        int quantidade = Integer.parseInt(edtQuantidade.getText().toString());

        Pessoa usuario = (Pessoa) previousIntent.getSerializableExtra("usuarioLogado");
        assert usuario != null;
        int idUsuario = usuario.getID_USER();

        BancoControle bd = new BancoControle(this);


        return bd.inserirDadosMedicamento(idUsuario,medicamento,quantidade);
    }
}