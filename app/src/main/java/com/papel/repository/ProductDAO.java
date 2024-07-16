package com.papel.repository;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

import com.papel.model.Product;
import com.papel.utils.DataBaseHelper;


public class ProductDAO {

    private SQLiteDatabase db;// Objeto para interactuar con la base de datos
    private DataBaseHelper dbHelper;// Instancia de DatabaseHelper para crear y actualizar la base de datos

    // Constructor que recibe el contexto de la aplicación y crea una instancia de DatabaseHelper
    public ProductDAO(Context context) {
        dbHelper = new DataBaseHelper(context);
    }

    // Método para abrir la conexión con la base de datos en modo escritura
    public void open() {

        db = dbHelper.getWritableDatabase();
    }
    // Método para cerrar la conexión con la base de datos
    public void close() {
        dbHelper.close();
    }


    // Método para insertar un nuevo producto en la tabla 'products'
    public long insertProduct(String name,String description,Double price, Integer quantity){
        long insertedId = -1; // Valor por defecto si ocurre un error

        try {
            if (db == null) {
                this.open(); // Asegurar que la base de datos esté abierta
            }
            ContentValues values = new ContentValues();
            values.put("name", name);
            values.put("description", description);
            values.put("price", price);
            values.put("quantity", quantity);

            insertedId = db.insertOrThrow("product", null, values); // insertOrThrow lanza una excepción si falla
        } catch (SQLException e) {
            Log.e("SQLite", "Error al insertar producto: " + e.getMessage());

        }
        return insertedId;
    }
    // Método para obtener todos los productos de la tabla 'products'
    public List<Product> getAllProducts(){ //pdt meter try catch
        List<Product> products = new ArrayList<>();// Lista para almacenar los productos obtenidos
        try {
            if (db == null) {
                this.open();
            }
            Cursor cursor = db.rawQuery("SELECT * FROM product", null);
            // Iteración sobre los resultados del cursor para obtener los datos de cada producto
            if (cursor.moveToFirst()) {
                do {
                    Product product = new Product();
                    product.setId(cursor.getInt(0));
                    product.setName(cursor.getString(1));
                    product.setDescription(cursor.getString(2));
                    product.setPrice(cursor.getDouble(3));
                    product.setQuantity(cursor.getInt(4));
                    products.add(product);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }catch (SQLException e){
            Log.e("SQLite", "Error al obtener todos los productos: " + e.getMessage());
        }
        return products;
    }
    // Método para actualizar los datos de un producto en la tabla 'products'
    public int updateProduct(Integer id, String name, String description,Double price, Integer quantity) {
        int rowsAffected = -1; // Valor por defecto
        try {
            if (db == null) {
                this.open();
            }
            ContentValues values = new ContentValues();
            values.put("name", name);
            values.put("description", description);
            values.put("price", price);
            values.put("quantity", quantity);
            rowsAffected = db.update("product", values, "id = ?", new String[]{String.valueOf(id)});
        } catch (SQLException e) {
            Log.e("SQLite", "Error al actualizar producto: " + e.getMessage());
        }
        return rowsAffected;
    }
    // Método para eliminar un producto de la tabla 'products' mediante su ID
    public int deleteProduct(int id) {
        int rowsDeleted = -1; // Valor por defecto
        try {
            if (db == null) {
                this.open();
            }
            // Eliminación del producto con el ID proporcionado
            rowsDeleted = db.delete("product", "id = ?", new String[]{String.valueOf(id)});
        }catch (SQLException e){
            Log.e("SQLite", "Error al eliminar producto: " + e.getMessage());
        }
        return rowsDeleted;
    }

    public Product findById(int id) {
        Product product = null;

        try {
            if (db == null) {
                this.open();
            }
            Cursor cursor = db.rawQuery("SELECT * FROM product WHERE id=?", new String[]{String.valueOf(id)});
            if (cursor != null && cursor.moveToFirst()) {
                product = new Product();
                product.setId(cursor.getInt(0));
                product.setName(cursor.getString(1));
                product.setDescription(cursor.getString(2));
                product.setPrice(cursor.getDouble(3));
                product.setQuantity(cursor.getInt(4));

                cursor.close();
            }

        } catch (SQLException e) {
            Log.e("SQLite", "Error al buscar producto por ID: " + e.getMessage());

        }
        return product;

    }
    public boolean existById(int id){
        boolean exists = false;
        try {
            if (db == null) {
                this.open();
            }
            Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM product WHERE id=?", new String[]{String.valueOf(id)});
            // Si el cursor tiene datos, obtener el conteo
            if (cursor != null && cursor.moveToFirst()) {
                int count = cursor.getInt(0);
                exists = count > 0;
            }
            cursor.close();
        }catch (SQLException e){
            Log.e("SQLite", "Error al buscar producto por ID: " + e.getMessage());

        }
        return exists;
    }

}
