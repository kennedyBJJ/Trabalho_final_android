package com.example.trabalhofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.trabalhofinal.Localization.LocalizacaoMain;

public class MainActivity extends AppCompatActivity {
    //Timer
    private static int SPLASH_TIME_OUT = 4000;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //mofifiquei para testes
                Intent i = new Intent(MainActivity.this, Login.class);
                startActivity(i);

                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}