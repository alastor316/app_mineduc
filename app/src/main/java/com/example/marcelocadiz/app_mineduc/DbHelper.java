package com.example.marcelocadiz.app_mineduc;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Marxelo on 29-10-2017.
 */

public class DbHelper  extends SQLiteOpenHelper {

    private static final String DB_NAME = "sistema.sqlite";
    private static final int DB_SCHEME_VERSION = 1;

    private SQLiteDatabase myDatabase;


    //  private final Context myContext;

    // Constructor de la clase DbHelper
    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_SCHEME_VERSION);

        //  this.myContext = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataBaseManager.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists favorito");
        db.execSQL(DataBaseManager.CREATE_TABLE);
    }

    public Cursor fetchAllList() throws SQLException {


        Cursor cursor = getWritableDatabase().rawQuery("SELECT * from favorito", null);

        if(cursor != null)
            cursor.moveToFirst();

        return cursor;
    }
}
