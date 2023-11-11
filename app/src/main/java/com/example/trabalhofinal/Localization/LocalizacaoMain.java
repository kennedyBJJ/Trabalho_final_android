package com.example.trabalhofinal.Localization;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.example.trabalhofinal.R;
import com.example.trabalhofinal.Utilitarios.CustonDialog;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

//Commit - Configurações iniciais do google play services
//O Google Play Service opera em um nivel mais baixo do android
public class LocalizacaoMain extends AppCompatActivity {

    FusedLocationProviderClient client;
    CustonDialog dialogCuston;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localizacao_main);

        client = LocationServices.getFusedLocationProviderClient(this);
        dialogCuston = new CustonDialog();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Verificando se o google play service do usuário está aualizada
        int status = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);

        switch (status) {
            case ConnectionResult.SERVICE_MISSING:
            case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED:
            case ConnectionResult.SERVICE_DISABLED:
                //Se por algum motivo o usuario estiver com o google play services desatualizado ou desabilitado, o GoogleApiAvailability.getInstanve().getErrorDialog()
                //irá exigir que o usuário corrija o problema

                GoogleApiAvailability.getInstance().getErrorDialog(this, status, 0, new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        finish();
                    }
                }).show();
                break;

            case ConnectionResult.SUCCESS:

                Toast.makeText(this, dialogCuston.SUCCESS_CHECK_PERMISSION, Toast.LENGTH_SHORT).show();
                break;
        }

        //Se a permissão for negada, este if mata a aplicacao;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Toast.makeText(LocalizacaoMain.this, dialogCuston.ERROR_CHECK_PERMISSION,Toast.LENGTH_SHORT).show();
            return;
        }
        client.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if(location != null){
                    String locationText = location.getLatitude()+" "+location.getLongitude();

                    Toast.makeText(LocalizacaoMain.this,locationText,Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LocalizacaoMain.this, dialogCuston.ERROR_OBTER_LOCATION ,Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }


}