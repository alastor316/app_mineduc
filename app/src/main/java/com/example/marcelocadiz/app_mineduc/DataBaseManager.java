package com.example.marcelocadiz.app_mineduc;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Marxelo on 29-10-2017.
 */

public class DataBaseManager {/**
 * Definicion de los parametros utilizados en la creacion de la tabla usuario
 */
public static final String TABLE_NAME = "favorito";
    public static final String USR_ID = "_id";
    public static final String USR_NOM = "nombre";



    public static final String CREATE_TABLE = "create table " +TABLE_NAME+ " ("
            + USR_ID + " text primary key,"
            + USR_NOM + " text not null);";

    private DbHelper helper;
    private SQLiteDatabase db;

    public DataBaseManager(Context context) {

        helper = new DbHelper(context);
        db = helper.getWritableDatabase();
    }

    public ContentValues generarContentValues(String id, String nombre) {
        ContentValues valores = new ContentValues();
        valores.put(USR_ID, id);
        valores.put(USR_NOM, nombre);
        return valores;
    }

    //Primera opcion para insertar valores en la tabla usuario
    public void insertar(String id, String nombre) {
        db.insert(TABLE_NAME, null, generarContentValues(id,nombre));
    }

    //Segunda opcion para insertar valores en la tabla usuario
 /*   public void insertar2(String nombre, String password) {
        db.execSQL("insert into "+TABLE_NAME+" values (null,'"+nombre+"','"+password+"')");
    }

*/





    public void eliminar(String nombre){
        db.delete(TABLE_NAME,USR_NOM+"?",new String[] {nombre});

    }



}
