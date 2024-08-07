package com.papel.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="papel";
    private static final int DATABASE_VERSION=1;
    private static final String productsQuery = "CREATE TABLE product(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, description TEXT,price DOUBLE, quantity INTEGER)";
    private static final String CREATE_TABLE_PRODUCTS=productsQuery;

    private static DataBaseHelper instance;

    public DataBaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(CREATE_TABLE_PRODUCTS);
        Log.d("DataBaseHelper", "Tabla 'product' creada correctamente");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS product");
        // Se crea la nueva versión de la tabla
        onCreate(db);

    }
}
