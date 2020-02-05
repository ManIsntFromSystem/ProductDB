package com.example.productsdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import Data.DataBaseHandler;
import Model.Product;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.floatingActionButton)
    FloatingActionButton addFloatBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        addFloatBtn.setOnClickListener((v) ->{
            Intent intent = new Intent(MainActivity.this,
                    AddMemberActivity.class);
            startActivity(intent);
        });
    }


}


//DataBaseHandler dataBaseHandler = new DataBaseHandler(this);
//Log.d("DB_LIST", "Products count: " + String.valueOf(dataBaseHandler.getProductCount()));
/*
        dataBaseHandler.addProduct(new Product("Broccoli", "200 руб."));
        dataBaseHandler.addProduct(new Product("Cucumber", "160 руб."));
        dataBaseHandler.addProduct(new Product("Avocado", "350 руб."));
*/
//List<Product> productList = dataBaseHandler.getAllProducts();

//Product deletingProduct = dataBaseHandler.getProduct(3);
//dataBaseHandler.deleteProduct(deletingProduct);

        /*for (Product product : productList){
            Log.d("DB_LIST", "ID: " + product.getId() + ", name: " + product.getName() +
                    ", price: " + product.getPrice());
        }*/



        /*
        Log.d("DB_LIST", "ID: " + product.getId() + ", name: " + product.getName() +
                ", price: " + product.getPrice());

        product.setName("Tomato");
        product.setPrice("220 руб.");

        int updatedProdId = dataBaseHandler.updateProduct(product);

        Log.d("DB_LIST", "ID: " + product.getId() + ", name: " + product.getName() +
                ", price: " + product.getPrice() + "ProdID: " + updatedProdId);*/