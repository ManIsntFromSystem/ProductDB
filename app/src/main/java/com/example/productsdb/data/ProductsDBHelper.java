package com.example.productsdb.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.productsdb.data.HealthyProductsContract.MemberEntry;

public class ProductsDBHelper extends SQLiteOpenHelper {
    public ProductsDBHelper(Context context) {
        super(context, HealthyProductsContract.DATABASE_NAME, null, HealthyProductsContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + MemberEntry.TABLE_NAME + "(" +
               MemberEntry._ID + " INTEGER PRIMARY KEY, " +
               MemberEntry.COLUMN_NAME + " TEXT, " +
               MemberEntry.COLUMN_DESCRIPTION + " TEXT, " +
               MemberEntry.COLUMN_CATEGORY + " INTEGER NOT NULL, " +
               MemberEntry.COLUMN_CALORIES + " DOUBLE NOT NULL, " +
               MemberEntry.COLUMN_PRICE + " DOUBLE NOT NULL" + ")";

        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MemberEntry.TABLE_NAME);
        onCreate(db);
    }
}
