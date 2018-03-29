package com.example.marcelocadiz.app_mineduc.api;

import com.example.marcelocadiz.app_mineduc.model.Colegios;
import com.example.marcelocadiz.app_mineduc.model.Matriculas;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by marcelo cadiz
 */

public interface JsonParser {
    @GET("recursos/obtener_colegios.php")
    Call<List<Colegios>> getAllColegios();

    @GET("recursos//obtener_colegio_id.php")
    Call<Colegios> getDetalleColegio(@Query("RBD") String RBD);

    @GET("recursos/obtener_matricula_id.php")
    Call<List<Matriculas>> getMatricula(@Query("RBD") String RBD);
}










