package com.example.productsdb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.productsdb.data.ProductsCursorAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.productsdb.data.HealthyProductsContract.MemberEntry;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    private static final int EDIT_PRODUCT_LOADER = 321;
    ProductsCursorAdapter productsCursorAdapter;

    @BindView(R.id.floatingActionButton)
    FloatingActionButton addFloatBtn;
    @BindView(R.id.listData)
    ListView listViewData;

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
        productsCursorAdapter = new ProductsCursorAdapter(this, null, false);
        listViewData.setAdapter(productsCursorAdapter);

        listViewData.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this, AddMemberActivity.class);

            Uri productCurrentUri = ContentUris.withAppendedId(MemberEntry.CONTENT_URI, id);
            intent.setData(productCurrentUri);
            startActivity(intent);
        });

        getSupportLoaderManager().initLoader(EDIT_PRODUCT_LOADER, null, this);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        String[] projection = {
                MemberEntry._ID,
                MemberEntry.COLUMN_NAME,
                MemberEntry.COLUMN_DESCRIPTION,
                MemberEntry.COLUMN_CATEGORY,
                MemberEntry.COLUMN_CALORIES,
                MemberEntry.COLUMN_PRICE
        };

        CursorLoader cursorLoader = new CursorLoader(this,
                MemberEntry.CONTENT_URI,
                projection,
                null,
                null,
                null
        );

        return cursorLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {

        productsCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

        productsCursorAdapter.swapCursor(null);
    }
}


/* @Override
    protected void onStart() {
        super.onStart();
        displayData();
    }

    private void displayData(){
        String[] projection = {
                MemberEntry._ID,
                MemberEntry.COLUMN_NAME,
                MemberEntry.COLUMN_DESCRIPTION,
                MemberEntry.COLUMN_CATEGORY,
                MemberEntry.COLUMN_CALORIES,
                MemberEntry.COLUMN_PRICE
        };

        Cursor cursor = getContentResolver().query(MemberEntry.CONTENT_URI,
                projection,
                null,
                null,
                null
        );

        ProductsCursorAdapter productsCursorAdapter = new ProductsCursorAdapter(
                this, cursor, true);

        listViewData.setAdapter(productsCursorAdapter);
    }*/




/*
        textData.setText("All products: \n\n");
        textData.setText(MemberEntry._ID + " " +
                MemberEntry.COLUMN_NAME + " " +
                MemberEntry.COLUMN_DESCRIPTION + " " +
                MemberEntry.COLUMN_CATEGORY + " " +
                MemberEntry.COLUMN_CALORIES + " " +
                MemberEntry.COLUMN_PRICE);
*/


/*
        if (cursor != null) {
            int idColumnIndex = cursor.getColumnIndex(MemberEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(MemberEntry.COLUMN_NAME);
            int descColumnIndex = cursor.getColumnIndex(MemberEntry.COLUMN_DESCRIPTION);
            int categoryColumnIndex = cursor.getColumnIndex(MemberEntry.COLUMN_CATEGORY);
            int caloriesColumnIndex = cursor.getColumnIndex(MemberEntry.COLUMN_CALORIES);
            int priceColumnIndex = cursor.getColumnIndex(MemberEntry.COLUMN_PRICE);


            while (cursor.moveToNext()) {
                int currentId = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentDesc = cursor.getString(descColumnIndex);
                int currentCategory = cursor.getInt(categoryColumnIndex);
                double currentCalories = cursor.getDouble(caloriesColumnIndex);
                double currentPrice = cursor.getDouble(priceColumnIndex);

/*
                textData.append("\n" +
                        currentId + " " +
                        currentName + " " +
                        currentDesc + " " +
                        currentCategory + " " +
                        currentCalories + " " +
                        currentPrice);
            }
            cursor.close();
        }*/




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