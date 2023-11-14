package com.example.trabalhofinal.Localization;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.trabalhofinal.Utilitarios.Dialog_Value_Adress_Fixed;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FetchAddressService extends IntentService {
    Dialog_Value_Adress_Fixed fixed = new Dialog_Value_Adress_Fixed();
    protected ResultReceiver receiver;

    public FetchAddressService() {
        super("fetchAddressService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent == null) {
            return;
        }
        ;
//iniciando a comunicação com a api do google, passando como parâmetro, o contexto e o formato de apresentação do endereço
        //no caso, este Locale.getDefault(), vai passar por parametro o locale do dispositivo do cliente;
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        Location location = intent.getParcelableExtra(fixed.LOCATION_DATA_EXTRA);
        receiver = intent.getParcelableExtra(fixed.RECEIVER);
        List<Address> addresses = null;

        try {
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
        } catch (IOException e) {
            Toast.makeText(FetchAddressService.this, fixed.ERROR_OBTER_LOCATION, Toast.LENGTH_SHORT).show();
        } catch (IllegalArgumentException e) {
            Toast.makeText(FetchAddressService.this, fixed.ERROR_OBTER_LOCATION, Toast.LENGTH_SHORT).show();
        }

        if (addresses == null || addresses.isEmpty()) {
            Toast.makeText(FetchAddressService.this, fixed.VALUES_NULL, Toast.LENGTH_SHORT).show();
            deliverResultToReceiver(fixed.FAILURE_RESULT, fixed.VALUES_NULL);
        } else {
            //--------------trabalhando com o caso de sucesso

            //Como eu limitei 1 resultado na busca, pego apenas o primerio endereço do sistema
            Address address = addresses.get(0);
            List<String> addressF = new ArrayList<>();

            //Como geralmente 1 endereço vem com: CEP, Logradouro, Bairro e municipio, preciso de um Array list pra adicionar tudo isso
            for (int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
                addressF.add(address.getAddressLine(i));
            }
            //Uso o TextUtils para concatenar o addressF
            deliverResultToReceiver(fixed.SUCCESS_RESULT, TextUtils.join("|", addressF));
        }
    }
    private void deliverResultToReceiver(int resultCode, String message) {
        Bundle bundle = new Bundle();
        bundle.putString(fixed.RESULT_DATA_KEY, message);
        receiver.send(resultCode, bundle);
    }
}
