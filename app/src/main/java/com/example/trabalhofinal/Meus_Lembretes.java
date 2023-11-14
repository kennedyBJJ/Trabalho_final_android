package com.example.trabalhofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
             new Intent(Meus_Lembretes.this, com.example.trabalhofinal.Localization.LocalizacaoMain.class);
            }
        });
    }

}