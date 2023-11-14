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
import android.util.Log;
import android.widget.Toast;

import com.example.trabalhofinal.R;
import com.example.trabalhofinal.Utilitarios.Dialog_Value_Adress_Fixed;
import com.example.trabalhofinal.databinding.ActivityLocalizacaoBinding;
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
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.util.Arrays;
import java.util.List;

//Commit - Configurações iniciais do google play services
//O Google Play Service opera em um nivel mais baixo do android
public class LocalizacaoMain extends AppCompatActivity implements OnMapReadyCallback {

    FusedLocationProviderClient client;
    AddressResultReceiver resultReceiver;
    Dialog_Value_Adress_Fixed fixed;

    private GoogleMap mMap;
    private ActivityLocalizacaoBinding binding;
    private PlacesClient placesClient;
    private AutocompleteSessionToken token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = LocationServices.getFusedLocationProviderClient(this);
        resultReceiver = new AddressResultReceiver(null);
        fixed = new Dialog_Value_Adress_Fixed();

        binding = ActivityLocalizacaoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(com.example.trabalhofinal.R.id.map);
        mapFragment.getMapAsync(this);

        String apiKey = fixed.API_PLACES_KEY;

        //inicializando meu Place
        Places.initialize(getApplicationContext(), apiKey);
        //Criando o cliente do place
        placesClient = Places.createClient(this);
        //Criando uma instancia do token de acesso
        token = AutocompleteSessionToken.newInstance();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Setando as liminatçoes de zoom

        mMap.setMinZoomPreference(6.0f);
        mMap.setMaxZoomPreference(20.0f);

        //verificando as permições
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        //Apresentando o ponto azul da sua localizaão
        mMap.setMyLocationEnabled(true);
        //apresentando o botão de atalho para ativar a localizaçao do celular
        mMap.getUiSettings().setMyLocationButtonEnabled(true);

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
                    //  Toast.makeText(LocalizacaoMain.this, locationText, Toast.LENGTH_SHORT).show();

                    LatLng city = new LatLng(location.getLatitude(), location.getLongitude());

                    MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(LocalizacaoMain.this, R.raw.style_map_nigth);
                    boolean teste = mMap.setMapStyle(style);

                    Log.i("teste",String.valueOf(teste));
                    //mMap.addMarker(new MarkerOptions().position(city).title("Estou aqui"));


                    //Setando a posição no mapa + o zoom que o mapa deve apresentar
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(city, 17.0f));

                    //Configurando o que vai acontecer quadno eu arrastar o mapa
                    mMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
                        @Override
                        public void onCameraIdle() {
                            //Com o Place configurado:
                            FindAutocompletePredictionsRequest predictionsRequest = FindAutocompletePredictionsRequest.builder().setCountries("BR").setTypeFilter(TypeFilter.ESTABLISHMENT).setSessionToken(token).setLocationRestriction(RectangularBounds.newInstance( //Restringindo a busca no mapa para que ele não busque no brasil inteiro
                                    mMap.getProjection().getVisibleRegion().latLngBounds //vai buscar os marcadores somente da região visivel do mapa
                            )).setQuery(fixed.OBJECTIVE_RESEARCH).build();

                            placesClient.findAutocompletePredictions(predictionsRequest).addOnCompleteListener(new OnCompleteListener<FindAutocompletePredictionsResponse>() {
                                @Override
                                public void onComplete(@NonNull Task<FindAutocompletePredictionsResponse> task) {
                                    if (task.isSuccessful()) {
                                        FindAutocompletePredictionsResponse result = task.getResult();
                                        if (result != null) {
                                            List<AutocompletePrediction> predictions = result.getAutocompletePredictions();

                                            for (AutocompletePrediction p : predictions) {

                                                /*List<Place.Type> placeType = p.getPlaceTypes();
                                                for (Place.Type pt : placeType) {
                                                    Toast.makeText(LocalizacaoMain.this, "tipo: " + pt.name(), Toast.LENGTH_SHORT).show();
                                                }
                                                */


                                                //Toast.makeText(LocalizacaoMain.this, p.getFullText(null).toString(), Toast.LENGTH_SHORT).show();
                                                //Toast.makeText(LocalizacaoMain.this, p.getPlaceId(), Toast.LENGTH_SHORT).show();

                                                Log.i("Teste", p.getFullText(null).toString());
                                                Log.i("Teste", p.getPlaceId());


                                                //Setando marcadores
                                                List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG);
                                                FetchPlaceRequest request = FetchPlaceRequest.builder(p.getPlaceId(), fields)
                                                        .setSessionToken(token)
                                                        .build();

                                                placesClient.fetchPlace(request)
                                                        .addOnSuccessListener(new OnSuccessListener<FetchPlaceResponse>() {
                                                            @Override
                                                            public void onSuccess(FetchPlaceResponse response) {
                                                                Place place = response.getPlace();
                                                                LatLng latLng = place.getLatLng();
                                                                mMap.addMarker(new MarkerOptions().position(latLng).title(place.getName()));
                                                            }
                                                        });

                                            }
                                        }
                                    } else {
                                        Toast.makeText(LocalizacaoMain.this, "ERROR", Toast.LENGTH_SHORT);
                                    }
                                }
                            });
                        }
                    });
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

                // Toast.makeText(LocalizacaoMain.this, locationSettingsResponse.getLocationSettingsStates().isNetworkLocationPresent() + "", Toast.LENGTH_SHORT).show();
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
                    // Toast.makeText(LocalizacaoMain.this, location.getLatitude() + "", Toast.LENGTH_SHORT).show();

                    if (!Geocoder.isPresent()) {
                        return;
                    } else {
                        //startIntentService(location);
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
//
    //----------INICIANDO A BUSCA POR DADOS COMPLEXOS-----------------------

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
}