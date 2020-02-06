package com.example.productsdb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.productsdb.data.HealthyProductsContract.MemberEntry;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddMemberActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor>{

    @BindView(R.id.editTextName)
    EditText editTextName;
    @BindView(R.id.editTextDesc)
    EditText editTextDesc;
    @BindView(R.id.spinnerCategory)
    Spinner spinnerCategory;
    @BindView(R.id.editTextСalories)
    EditText editTextСalories;
    @BindView(R.id.editTextPrice)
    EditText editTextPrice;

    private int category = 0;

    private ArrayAdapter spinnerAdapter;
    private ArrayList spinnerArrayList;

    private static final int PRODUCT_LOADER = 432;

    Uri currentProductUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        currentProductUri = intent.getData();

        if(currentProductUri == null){
            setTitle("Add new Product");
        } else {
            setTitle("Edit the Member");
        }

        getSupportLoaderManager().initLoader(PRODUCT_LOADER, null, this);

        addSpinnerArrayCategory();
        /*spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_subcategory_vegetable, android.R.layout.simple_spinner_item);*/

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = (String) parent.getItemAtPosition(position);
                if(!TextUtils.isEmpty(selectedCategory)){
                    switch (selectedCategory) {
                        case "Vegetable":
                            category = MemberEntry.CATEGORY_VEGETABLE;
                            break;
                        case "Fruit":
                            category = MemberEntry.CATEGORY_FRUIT;
                            break;
                        case "Grains, Beans and Nuts":
                            category = MemberEntry.CATEGORY_GRAINS_BEANS_AND_NUTS;
                            break;
                        case "Fish and Seafood":
                            category = MemberEntry.CATEGORY_FISH_AND_SEAFOOD;
                            break;
                        case "Dairy":
                            category = MemberEntry.CATEGORY_DAIRY;
                            break;
                        case "Meat and Poultry":
                            category = MemberEntry.CATEGORY_MEAT_AND_POULTRY;
                            break;
                        default:
                            category = MemberEntry.CATEGORY_OTHER;
                            break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                category = MemberEntry.CATEGORY_OTHER;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_member_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.saveMember:
                saveProducts();
                return true;
            case R.id.deleteMember:
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveProducts(){
        //trim() - cut all probels in front and back
        String nameProd = editTextName.getText().toString().trim();
        String descriptionProd = editTextDesc.getText().toString().trim();
        double caloriesProd = Double.valueOf(editTextСalories.getText().toString());
        double priceProd = Double.valueOf(editTextPrice.getText().toString());

        ContentValues contentValues = new ContentValues();
        contentValues.put(MemberEntry.COLUMN_NAME, nameProd);
        contentValues.put(MemberEntry.COLUMN_DESCRIPTION, descriptionProd);
        contentValues.put(MemberEntry.COLUMN_CATEGORY, category);
        contentValues.put(MemberEntry.COLUMN_CALORIES, caloriesProd);
        contentValues.put(MemberEntry.COLUMN_PRICE, priceProd);

        ContentResolver contentResolver = getContentResolver();
        Log.d("ContentResolver", "URI: " + MemberEntry.CONTENT_URI);
        Log.i("ContentResolver", "URI: " + contentValues);
        Uri uri = contentResolver.insert(MemberEntry.CONTENT_URI, contentValues);



        if(uri == null){
            Toast.makeText(this, "Insertion of data in the table failed", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Insertion of data is successful", Toast.LENGTH_LONG).show();
        }

    }

    private void addSpinnerArrayCategory(){
        spinnerArrayList = new ArrayList();
        spinnerArrayList.add("Vegetable");
        spinnerArrayList.add("Fruit");
        spinnerArrayList.add("Grains, Beans and Nuts");
        spinnerArrayList.add("Fish and Seafood");
        spinnerArrayList.add("Dairy");
        spinnerArrayList.add("Meat and Poultry");
        spinnerArrayList.add("Other");

        spinnerAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, spinnerArrayList);

        spinnerAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(spinnerAdapter);
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

        return new CursorLoader(this,
                currentProductUri,
                projection,
                null,
                null,
                null
        );
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        if (data.moveToFirst()){
            int nameColumnIndex = data.getColumnIndex(
                    MemberEntry.COLUMN_NAME);
            int discriptionColumnIndex = data.getColumnIndex(
                    MemberEntry.COLUMN_DESCRIPTION);
            int categoryColumnIndex = data.getColumnIndex(
                    MemberEntry.COLUMN_CATEGORY);
            int caloriesColumnIndex = data.getColumnIndex(
                    MemberEntry.COLUMN_CALORIES);
            int priceColumnIndex = data.getColumnIndex(
                    MemberEntry.COLUMN_PRICE);

            String nameProd = data.getString(nameColumnIndex);
            String descriptionProd = data.getString(discriptionColumnIndex);
            int categoryProd = data.getInt(categoryColumnIndex);
            double caloriesProd = data.getDouble(caloriesColumnIndex);
            double priceProd = data.getDouble(priceColumnIndex);

            editTextName.setText(nameProd);
            editTextDesc.setText(descriptionProd);

            switch (categoryProd){
                case MemberEntry.CATEGORY_VEGETABLE:
                    spinnerCategory.setSelection(0);
                    break;
                case MemberEntry.CATEGORY_FRUIT:
                    spinnerCategory.setSelection(1);
                    break;
                case MemberEntry.CATEGORY_GRAINS_BEANS_AND_NUTS:
                    spinnerCategory.setSelection(2);
                    break;
                case MemberEntry.CATEGORY_FISH_AND_SEAFOOD:
                    spinnerCategory.setSelection(3);
                    break;
                case MemberEntry.CATEGORY_DAIRY:
                    spinnerCategory.setSelection(4);
                    break;
                case MemberEntry.CATEGORY_MEAT_AND_POULTRY:
                    spinnerCategory.setSelection(5);
                    break;
                case MemberEntry.CATEGORY_OTHER:
                    spinnerCategory.setSelection(6);
                    break;
            }
            editTextСalories.setText(String.valueOf(caloriesProd));
            editTextPrice.setText(String.valueOf(priceProd));
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}
