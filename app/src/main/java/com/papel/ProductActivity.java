package com.papel;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import com.papel.dto.ProductDTO;
import com.papel.model.Product;
import com.papel.presenter.ProductPresenterImpl;
import com.papel.view.IProductView;
import com.papel.view.adapters.ProductListAdapter;


public class ProductActivity extends AppCompatActivity implements IProductView{

    // Declaración de variables
    private EditText etlNombre, etlDescription,etlPrecio,etlCantidad;
    private Button btnAgregar, btnMostrar;
    private ListView listView;
    private ProductListAdapter productListAdapter;
    private ProductPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product);

        // Obtención de referencias a elementos de la interfaz de usuario
        etlNombre = findViewById(R.id.etlNombre);
        etlDescription = findViewById(R.id.etlDescription);
        etlPrecio=findViewById(R.id.etlPrice);
        etlCantidad=findViewById(R.id.etlQuantity);
        btnAgregar = findViewById(R.id.btnAgregar);
        btnMostrar = findViewById(R.id.btnMostrar);
        listView = findViewById(R.id.listViewProducts);

        // Configuración de listener para el botón de agregar
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProduct();
            }
        });
        // Configuración de listener para el botón de mostrar
        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cargar lista de productos al iniciar la actividad
                presenter.showProducts();
                btnMostrar.setVisibility(View.GONE);
            }
        });



        presenter = new ProductPresenterImpl(this, this);


    }

    private void addProduct() {
        String name = etlNombre.getText().toString().trim();
        String description = etlDescription.getText().toString().trim();
        Double price = Double.parseDouble(etlPrecio.getText().toString());
        Integer quantity = Integer.parseInt(etlCantidad.getText().toString());
        // Validación de campos
        if (!name.isEmpty() && !description.isEmpty() && price>0 && quantity>0) {
            ProductDTO dto = new ProductDTO();
            dto.setName(name);
            dto.setDescription(description);
            dto.setPrice(price);
            dto.setQuantity(quantity);
            presenter.addProduct(dto);
            etlNombre.setText("");
            etlDescription.setText("");
            etlPrecio.setText("");
            etlCantidad.setText("");
        }
        presenter.showProducts();
    }

    @Override
    public void showProducts(List<Product> productList) {
        if (productListAdapter == null) {
            productListAdapter = new ProductListAdapter(this, productList);
            listView.setAdapter(productListAdapter);
        } else {
            productListAdapter.updateProducts(productList);
        }

        // Obtención de la lista de usuarios desde la base de datos
///        productListAdapter = new ProductListAdapter(this,productList);
        //     listView.setAdapter(productListAdapter);

        // Configuración de listeners para el adaptador
        //Editar producto
        productListAdapter.setOnEditClickListener(new ProductListAdapter.OnEditClickListener() {
                                                      @Override
                                                      public void onEditClick(int position) {
                                                          // Lógica para editar un usuario
                                                          Product product = productList.get(position);
                                                          etlNombre.setText(product.getName());
                                                          etlDescription.setText(product.getDescription());
                                                          etlPrecio.setText(String.valueOf(product.getPrice()));
                                                          etlCantidad.setText(String.valueOf(product.getQuantity()));

                                                          int idProduct= product.getId();
                                                          btnAgregar.setText("Guardar");
                                                          btnAgregar.setOnClickListener(new View.OnClickListener() {
                                                              @Override
                                                              public void onClick(View v) {
                                                                  actualizarProducto(idProduct);
                                                              }
                                                          });
                                                      }
                                                  }
        );

        productListAdapter.setOnDeleteClickListener(new ProductListAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(int position) {
                Product product = productList.get(position);
                ProductDTO dto = new ProductDTO();
                dto.setId(product.getId());
                dto.setName(product.getName());
                dto.setDescription(product.getDescription());
                presenter.deleteProduct(dto);
            }
        });

    }
    // Método para actualizar la lista

    private void actualizarProducto(int idProduct) {
        String nombre = etlNombre.getText().toString().trim();
        String descripcion = etlDescription.getText().toString().trim();
        Double price = Double.parseDouble(etlPrecio.getText().toString());
        Integer quantity = Integer.parseInt(etlCantidad.getText().toString());
        Log.d("precio", "price: "+price);
        Log.d("quantity", "quantity: "+quantity);
        // Actualización de los datos del usuario en la base de datos
        presenter.updateProduct(new ProductDTO(idProduct,nombre,descripcion,price,quantity));
        etlNombre.setText("");
        etlDescription.setText("");
        etlPrecio.setText("");
        etlCantidad.setText("");
        btnAgregar.setText("Agregar");
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addProduct(new ProductDTO(idProduct,nombre,descripcion,price,quantity));
            }
        });
        presenter.showProducts();

    }

    @Override
    public void showMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }


}