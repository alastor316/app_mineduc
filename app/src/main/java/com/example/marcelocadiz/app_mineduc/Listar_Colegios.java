package com.example.marcelocadiz.app_mineduc;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.marcelocadiz.app_mineduc.adapter.ColegiosAdapter;
import com.example.marcelocadiz.app_mineduc.model.Colegios;
import com.example.marcelocadiz.app_mineduc.api.JsonParser;
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
 * Created by marcelo.cadiz on 31/08/2017.
 */

public class Listar_Colegios extends AppCompatActivity implements  ColegiosAdapter.click, Callback<List<Colegios>> {

    private static final String TAG = MainActivity.class.getName();
    private static final String BASE_URL = "https://marxeloapp.000webhostapp.com";
    private ColegiosAdapter adapter;
    private ArrayList<Colegios> lista_Colegios;
    private RecyclerView mRecyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_colegios);

        lista_Colegios = new ArrayList<Colegios>();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerviews);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new ColegiosAdapter(lista_Colegios, this);
        mRecyclerView.setAdapter(adapter);

        cargarColegios();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.favoritos:
                Intent intent = new Intent(this, ListaFavoritos.class);
                startActivity(intent);
                break;

            case R.id.salir:
                AlertDialog.Builder a_builder = new AlertDialog.Builder(Listar_Colegios.this);
                a_builder.setMessage("Quieres salir de esta Aplicacion !!!")
                        .setCancelable(false)
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = a_builder.create();
                alert.setTitle("Alert !!!");
                alert.show();
                break;


        }
        return true;
    }







    public void cargarColegios() {
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        JsonParser jsonPlaceHolderAPI = retrofit.create(JsonParser.class);
        Call<List<Colegios>> call = jsonPlaceHolderAPI.getAllColegios();
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<List<Colegios>> call, Response<List<Colegios>> response) {
        if (response.isSuccessful()) {
            List<Colegios> colegiosList = response.body();
            for (Colegios colegios : colegiosList) {
                lista_Colegios.add(colegios);
            }
            adapter.notifyDataSetChanged();
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<List<Colegios>> call, Throwable t) {
        t.printStackTrace();
    }


    private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (adapter != null) adapter.getFilter().filter(newText);
                return true;
            }
        });
    }

    @Override
    public void clickNombre(Colegios colegio) {
        Intent intent = new Intent(this, Mostar_colegios.class);
        intent.putExtra(Mostar_colegios.COLEGIO_RBD, colegio.getRBD());
        startActivity(intent);
    }

    @Override
    public void clickMatricula(Colegios colegios) {
        Intent intent = new Intent(this, Mostrar_Matriculas.class);
        intent.putExtra(Mostrar_Matriculas.Mat_ID, colegios.getRBD());
        startActivity(intent);
    }
}




