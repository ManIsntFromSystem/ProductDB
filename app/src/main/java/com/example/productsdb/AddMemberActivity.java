package com.example.productsdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.productsdb.data.HealthyProductsContract.MemberEntry;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddMemberActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);
        ButterKnife.bind(this);

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

        /*spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_subcategory_vegetable, android.R.layout.simple_spinner_item);*/

        spinnerAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(spinnerAdapter);

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = (String) parent.getItemAtPosition(position);
                if(!TextUtils.isEmpty(selectedCategory)){
                    if(selectedCategory.equals("Vegetable")){
                        category = MemberEntry.CATEGORY_VEGETABLE;
                    } else if (selectedCategory.equals("Fruit")){
                        category = MemberEntry.CATEGORY_FRUIT;
                    } else if (selectedCategory.equals("Grains, Beans and Nuts")) {
                        category = MemberEntry.CATEGORY_GRAINS_BEANS_AND_NUTS;
                    } else if (selectedCategory.equals("Fish and Seafood")) {
                        category = MemberEntry.CATEGORY_FISH_AND_SEAFOOD;
                    } else if (selectedCategory.equals("Dairy")) {
                        category = MemberEntry.CATEGORY_DAIRY;
                    } else if (selectedCategory.equals("Meat and Poultry")) {
                        category = MemberEntry.CATEGORY_MEAT_AND_POULTRY;
                    } else {
                        category = MemberEntry.CATEGORY_OTHER;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                category = 6;
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
                return true;
            case R.id.deleteMember:
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
