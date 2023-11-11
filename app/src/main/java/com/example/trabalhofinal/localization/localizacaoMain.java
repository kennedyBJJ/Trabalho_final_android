package com.example.trabalhofinal.localization;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.trabalhofinal.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

//Commit - Configurações iniciais do google play services
//O Google Play Service opera em um nivel mais baixo do android
public class localizacaoMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localizacao_main);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //Verificando se o google play service do usuário está aualizada
        int status = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);

         switch (status){
             case ConnectionResult.SERVICE_MISSING:
             case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED:
             case ConnectionResult.SERVICE_DISABLED:
                 Log.d("teste", "show dialog");
                 break;
             case ConnectionResult.SUCCESS:
                 Log.d("teste", "Google play services está atualizado");
                 break;
         }

    }
}