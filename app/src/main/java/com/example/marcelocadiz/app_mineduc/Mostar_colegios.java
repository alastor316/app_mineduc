package com.example.marcelocadiz.app_mineduc;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.marcelocadiz.app_mineduc.model.Colegios;
import com.example.marcelocadiz.app_mineduc.api.JsonParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by marcelo.cadiz on 31/08/2017.
 */

public class Mostar_colegios extends AppCompatActivity implements Callback<Colegios>, View.OnClickListener {

    ImageButton botonMapa;
    public static final String COLEGIO_RBD = "asdf";
    private static final String BASE_URL = "https://marxeloapp.000webhostapp.com";

    TextView rbd, nombre,direccion, telefono, celular, mail, fecha, zona, estado, rutRepresentante, nombreRepresentante, mailRepresentante, nombreDependencia, nombreComuna, nombreProvincia, nombreDirector ;
    private Double latitud,longitud;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrar_colegios);
        botonMapa = (ImageButton) findViewById(R.id.botonMapa);

        rbd = (TextView) findViewById(R.id.mostrandoRBD);
        nombre = (TextView) findViewById(R.id.mostrandoNombre);
        direccion = (TextView) findViewById(R.id.mostrandoDireccion);
        telefono = (TextView) findViewById(R.id.mostrandoTelefono);
        celular = (TextView) findViewById(R.id.mostrandoCelular);
        mail = (TextView) findViewById(R.id.mostrandoMail);
        fecha = (TextView) findViewById(R.id.fechaIniciacion);
        zona = (TextView) findViewById(R.id.mostrandoZona);
        estado = (TextView) findViewById(R.id.mostrandoEstado);
        rutRepresentante = (TextView) findViewById(R.id.rutRepresentante);
        nombreRepresentante = (TextView) findViewById(R.id.nombreRepresentante);
      //  mailRepresentante = (TextView) findViewById(R.id.mailRepresentante);
        nombreDependencia = (TextView) findViewById(R.id.mostrandoDependencia);
        nombreComuna = (TextView) findViewById(R.id.mostrandoComuna);
        nombreProvincia = (TextView) findViewById(R.id.mostrandoProvincia);
        nombreDirector = (TextView) findViewById(R.id.nombreDirector);

        Intent intent = getIntent();
        if (intent != null) {
            String RBD = intent.getStringExtra(COLEGIO_RBD);
            getCommentsOfPost(RBD);

        }


        botonMapa.setOnClickListener(this);

    }

    public void getCommentsOfPost(String RBD) {
        if (RBD == null)
            return;
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        JsonParser jsonPlaceHolderAPI = retrofit.create(JsonParser.class);
        Call<Colegios> call = jsonPlaceHolderAPI.getDetalleColegio(RBD);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Colegios> call, Response<Colegios> response) {
        try {
            rbd.setText("RBD: "+response.body().getRBD());
            nombre.setText("Nombre: "+response.body().getNombre());
            direccion.setText("Direccion: "+response.body().getDireccion());
            telefono.setText("Telefono: "+response.body().getTelefono());
            celular.setText("Celular: ");
            mail.setText("E-mail Establecimiento: "+response.body().getMail());
            fecha.setText("Fecha de Iniciación: "+response.body().getFecha());
            zona.setText("Zona: "+response.body().getZona());


            estado.setText("Estado: "+response.body().getEstado());
            rutRepresentante.setText("Rut Representante: "+response.body().getRutRepresentante());
            nombreRepresentante.setText("Nombre Representante: "+response.body().getNombreRepresentante());
           // mailRepresentante.setText("E-mail Representante: "+response.body().getMailRepresentante());
            nombreDependencia.setText("Dependencia: "+response.body().getNombreDependencia());
            nombreComuna.setText("Comuna: "+response.body().getNombreComuna());
            nombreProvincia.setText("Provincia: "+response.body().getNombreProvincia());
            nombreDirector.setText("Director: "+response.body().getNombreDirector());

            latitud = (response.body().getLatitud());
            longitud = (response.body().getLongitud());

            if ( response.body().getEstado().equals("FUNCIONANDO")) {
                estado.setTextColor(Color.parseColor("#5BAB62"));
            }

            else if (response.body().getEstado().equals("CERRADO")){
                estado.setTextColor(Color.parseColor("#FF0000"));

            }


        } catch (Exception e) {
            Log.d("onResponse", "error !!!");
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call<Colegios> call, Throwable t) {

    }

    @Override
    public void onClick(View view) {
        //Inicia la acitivy Listar_colegios
        Intent intent = new Intent(this,MapsActivity.class);
        intent.putExtra("DATO",latitud );
        intent.putExtra("DATO2",longitud );
        intent.putExtra("DATO3",nombre.getText().toString() );

        startActivity(intent);
    }
}
