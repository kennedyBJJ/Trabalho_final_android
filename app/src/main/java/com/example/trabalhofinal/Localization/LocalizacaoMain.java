package com.example.trabalhofinal.Localization;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.widget.Toast;

import com.example.trabalhofinal.R;
import com.example.trabalhofinal.Utilitarios.Dialog_Value_Adress_Fixed;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

//Commit - Configurações iniciais do google play services
//O Google Play Service opera em um nivel mais baixo do android
public class LocalizacaoMain extends AppCompatActivity {

    FusedLocationProviderClient client;
    AddressResultReceiver resultReceiver;
    Dialog_Value_Adress_Fixed fixed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localizacao_main);

        client = LocationServices.getFusedLocationProviderClient(this);
        resultReceiver = new AddressResultReceiver(null);
        fixed = new Dialog_Value_Adress_Fixed();
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

                Toast.makeText(this, fixed.SUCCESS_CHECK_PERMISSION, Toast.LENGTH_SHORT).show();
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
            Toast.makeText(LocalizacaoMain.this, fixed.ERROR_CHECK_PERMISSION, Toast.LENGTH_SHORT).show();
            return;
        }
        client.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if (location != null) {
                    String locationText = location.getLatitude() + " " + location.getLongitude();

                    Toast.makeText(LocalizacaoMain.this, locationText, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LocalizacaoMain.this, fixed.ERROR_OBTER_LOCATION, Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

        //Definindo parâmetros de localização
        //Eu sei que ta depreciado mas não encontrei uma forma mais atual de fazer isso

        LocationRequest locationRequest = LocationRequest.create();
        //Define Qual o intervalo de vezes que o sistema consulta a posição do usuario atraves da minha aplicação
        locationRequest.setInterval(15 * 1000);
        //define QUal o intervalo de vezes que o sistema consulta a posição do usuario atraves de aplicativos trerceiros
        locationRequest.setFastestInterval(5 * 1000);
        //Define a precisão da localização
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        //Busca as configuração no dispositivo do cliente
        SettingsClient settingsClient = LocationServices.getSettingsClient(this);
        //Checa se esta tudo certo com as configurações do cliente com meu builder
        settingsClient.checkLocationSettings(builder.build()).addOnSuccessListener(new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {

                Toast.makeText(LocalizacaoMain.this, locationSettingsResponse.getLocationSettingsStates().isNetworkLocationPresent() + "", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e instanceof ResolvableApiException) {
                    ResolvableApiException resolvable = (ResolvableApiException) e;
                    try {
                        resolvable.startResolutionForResult(LocalizacaoMain.this, 10);
                    } catch (IntentSender.SendIntentException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        LocationCallback locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                if (locationResult == null) {
                    Toast.makeText(LocalizacaoMain.this, "location is null", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (Location location : locationResult.getLocations()) {
                    Toast.makeText(LocalizacaoMain.this, location.getLatitude() + "", Toast.LENGTH_SHORT).show();

                    if (!Geocoder.isPresent()) {
                        return;
                    } else {
                        startIntentService(location);
                    }
                }

            }

            @Override
            public void onLocationAvailability(@NonNull LocationAvailability locationAvailability) {
                Toast.makeText(LocalizacaoMain.this, locationAvailability.isLocationAvailable() + "", Toast.LENGTH_SHORT).show();
            }
        };
        //Atualiza a localização do usuário
        client.requestLocationUpdates(locationRequest, locationCallback, null);

        //-----------USANDO O GEOCODE PRA BUSCAR INFORMAÇÕES COMPLEXAS DE LOCALIZAÇÃO EM BACKGROUND

    }

    public void startIntentService(Location location) {
Intent intent = new Intent(this, FetchAddressService.class);
intent.putExtra(fixed.RECEIVER, resultReceiver);
intent.putExtra(fixed.LOCATION_DATA_EXTRA, location);
startService(intent);
    }

    private class AddressResultReceiver extends ResultReceiver {

        public AddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            //Recebe os dados do FetchAddress
            if (resultData == null) {
                return;
            }

            final String addressOutPut = resultData.getString(fixed.RESULT_DATA_KEY);

            if (addressOutPut != null) {
                //Estou rodando a aplicação em background (como foi declarado no manifest), então não posso chamar view diretamente ja que estou em outra tarefa

                runOnUiThread(new Runnable() {
                    //este metodo pertence a todas as activity, então me possibilita a comunicação
                    @Override
                    public void run() {
                        Toast.makeText(LocalizacaoMain.this, addressOutPut, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }
    }
    //----------FINALIZANDO A BUSCA POR DADOS COMPLEXOS-----------------------
}