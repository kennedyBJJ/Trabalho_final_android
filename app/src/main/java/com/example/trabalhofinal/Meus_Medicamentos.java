package com.example.trabalhofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.trabalhofinal.DataBase.BancoControle;
import com.example.trabalhofinal.models.Medicamento;
import com.example.trabalhofinal.models.Pessoa;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Meus_Medicamentos extends AppCompatActivity {

    ListView listaMedicamentos;
    ArrayAdapter<Medicamento> adapterMedicamentos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_medicamentos);


        androidx.appcompat.widget.AppCompatButton  btnNovoMedicamento = findViewById(R.id.btnNovoMedicamento);
        androidx.appcompat.widget.AppCompatButton  btnVoltar = findViewById(R.id.btnVoltarInicio);

        listaMedicamentos = findViewById(R.id.listMedicamentos);


        btnNovoMedicamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para abrir Tela 1
                Intent intent = new Intent(Meus_Medicamentos.this, Novo_Medicamento.class);
//
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
//                Intent intent = new Intent(Meus_Medicamentos.this, Inicio.class);
//                startActivity(intent);
            }
        });

        registerForContextMenu(listaMedicamentos);


    }
//
//
    public void populaLista(){
        BancoControle db = new BancoControle(this);
        Intent previous = getIntent();
        Pessoa usuarioLogado = (Pessoa) previous.getSerializableExtra("usuarioLogado");

        assert usuarioLogado != null;
        ArrayList<Medicamento> todosMedicamentos = db.selectMedicamentos(usuarioLogado.getID_USER());

        if(todosMedicamentos != null){


        adapterMedicamentos = new ArrayAdapter<Medicamento>(
                Meus_Medicamentos.this,
                R.layout.list_medicamento_layout,
                R.id.etNome,
                todosMedicamentos
        ){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){

                View view = super.getView(position, convertView, parent);


                Medicamento medicamento = getItem(position);
                TextView edtMedicamento = view.findViewById(R.id.etNome);
                TextView edtQuantidade= view.findViewById(R.id.etQuantidade);

                if(medicamento !=null){
                    int quantidade = medicamento.getQUANT_MEDICAMENTO();
                    String nomeMedicamento = medicamento.getNOME_MEDICAMENTO();

                    String quant = quantidade+"";

                    edtMedicamento.setText(nomeMedicamento);
                    edtQuantidade.setText(quant);
                }

                return view;
            }
        };

            listaMedicamentos.setAdapter(adapterMedicamentos);

        }



    }
    @Override
    protected void onResume() {
        super.onResume();
        populaLista();
    }
}
