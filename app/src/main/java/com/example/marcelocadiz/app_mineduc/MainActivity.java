package com.example.marcelocadiz.app_mineduc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton BotonIniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BotonIniciar = (ImageButton) findViewById(R.id.ButtonIniciar);
        BotonIniciar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){

        //Inicia la acitivy Listar_colegios
        Intent intent = new Intent(this,Listar_Colegios.class);
        startActivity(intent);

    }

}
