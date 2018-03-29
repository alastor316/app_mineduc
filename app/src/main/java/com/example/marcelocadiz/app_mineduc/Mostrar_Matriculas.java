package com.example.marcelocadiz.app_mineduc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.marcelocadiz.app_mineduc.adapter.ColegiosAdapter;
import com.example.marcelocadiz.app_mineduc.adapter.MatriculasAdapter;
import com.example.marcelocadiz.app_mineduc.api.JsonParser;
import com.example.marcelocadiz.app_mineduc.model.Colegios;
import com.example.marcelocadiz.app_mineduc.model.Matriculas;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by marcelo.cadiz on 10/10/2017.
 */

public class Mostrar_Matriculas extends AppCompatActivity implements Callback<List<Matriculas>> {




    public static final String Mat_ID = "post-id";
    private static final String BASE_URL = "https://marxeloapp.000webhostapp.com";
    private MatriculasAdapter adapter;
    private ArrayList<Matriculas> lista_Matriculas;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matricula);

        lista_Matriculas = new ArrayList<Matriculas>();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerviews);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new MatriculasAdapter(lista_Matriculas, this);
        mRecyclerView.setAdapter(adapter);

        Intent intent = getIntent();
        if (intent != null) {
            String postId = intent.getStringExtra(Mat_ID);
            getCommentsOfPost(postId);
        }



    }

    public void getCommentsOfPost(String RBD) {
        if (RBD == null)
            return;
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        JsonParser parser = retrofit.create(JsonParser.class);
        Call<List<Matriculas>> call = parser.getMatricula(RBD);
        call.enqueue(this);
    }








    @Override
    public void onResponse(Call<List<Matriculas>> call, Response<List<Matriculas>> response) {
        if (response.isSuccessful()) {
            List<Matriculas> matriculaList = response.body();
            for (Matriculas matriculas : matriculaList) {
                lista_Matriculas.add(matriculas);
            }
            adapter.notifyDataSetChanged();
        } else {
            System.out.println(response.errorBody());
        }
    }


    @Override
    public void onFailure(Call<List<Matriculas>> call, Throwable t) {

    }


}
